package motosave.motosavefx.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.Modelos.Comercial;
import motosave.Modelos.Concesionario;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
/**
 * @author MotoSave
 */
public class AdminComercialesController implements Initializable {

    ObservableList<Comercial> comercialesList;
    private Comercial comercial;
    private ImpComercialDAO comDAO;
    private ImpConcesionarioDAO concDAO;
    @FXML
    private ComboBox<Concesionario> CmB_concesionario;
    @FXML
    private TextField TF_NIF;
    @FXML
    private TextField TF_nombre;
    @FXML
    private TextField TF_usuario;
    @FXML
    private TextField TF_apellidos;
    @FXML
    private PasswordField PF_pass1;
    @FXML
    private PasswordField PF_pass2;
    @FXML
    private Button BTN_salir;
    @FXML
    private TableView<Comercial> TV_comerciales;
    @FXML
    private Button BTN_dashboard;
    @FXML
    private Button BTN_stock;
    @FXML
    private TableColumn<Comercial, String> C_nombre;
    @FXML
    private TableColumn<Comercial, Concesionario> C_concesionario;
    @FXML
    private TableColumn<Comercial, String> C_apellidos;
    @FXML
    private TableColumn<Comercial, String> C_NIF;
    @FXML
    private TableColumn<Comercial, String> C_usuario;
    @FXML
    private Label L_error_concesionario;
    @FXML
    private Label L_error_campos;
    @FXML
    private Label L_error_contrasenas;
    @FXML
    private Label L_error_usuario;
    @FXML
    private Label L_error_NIF;

    /**
     * Inicializa el controlador.
     * Configura las variables y llama al método cargarDatos() para cargar los datos iniciales en la interfaz de usuario.
     *
     * @param url            La URL del archivo FXML.
     * @param resourceBundle El ResourceBundle utilizado para localizar objetos de la interfaz de usuario.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comDAO = new ImpComercialDAO();
        concDAO = new ImpConcesionarioDAO();
        comercialesList = FXCollections.observableArrayList();

        cargarDatos();
    }

    /**
     * Carga los datos iniciales en la interfaz de usuario.
     * Llena un ComboBox con concesionarios disponibles y configura la tabla para mostrar los comerciales.
     */
    private void cargarDatos() {
        llenarComboBoxConcesionarios(CmB_concesionario);

        C_nombre.setCellValueFactory(new PropertyValueFactory<Comercial, String>("nombre"));
        C_concesionario.setCellValueFactory(new PropertyValueFactory<Comercial, Concesionario>("concesionario"));
        C_apellidos.setCellValueFactory(new PropertyValueFactory<Comercial, String>("apellido"));
        C_NIF.setCellValueFactory(new PropertyValueFactory<Comercial, String>("NIF"));
        C_usuario.setCellValueFactory(new PropertyValueFactory<Comercial, String>("usuario"));

        cargarComerciales();
    }

    /**
     * Agrega un nuevo comercial al sistema.
     * Valida los campos de entrada y realiza la inserción en la base de datos si los datos son válidos.
     *
     * @param actionEvent El evento de acción que desencadenó el método.
     */
    @FXML
    public void agregarComercial(ActionEvent actionEvent) {
        L_error_concesionario.setVisible(false);
        L_error_campos.setVisible(false);
        L_error_contrasenas.setVisible(false);
        L_error_NIF.setVisible(false);
        L_error_usuario.setVisible(false);

        Concesionario concesionario = CmB_concesionario.getValue();
        if (concesionario == null) {
            L_error_concesionario.setVisible(true);
        } else if (TF_usuario.getText().isBlank() || PF_pass1.getText().isBlank() || TF_NIF.getText().isBlank() || TF_nombre.getText().isBlank() || TF_apellidos.getText().isBlank() || PF_pass2.getText().isBlank()) {
            L_error_campos.setVisible(true);
        } else if (!PF_pass1.getText().equals(PF_pass2.getText())) {
            L_error_contrasenas.setVisible(true);
        } else if (comDAO.buscarUsuarioComercial(miEntityManager.getEntityManager(), TF_usuario.getText())) {
            L_error_usuario.setVisible(true);
        } else if (comDAO.buscarNIFComercial(miEntityManager.getEntityManager(), TF_NIF.getText())) {
            L_error_NIF.setVisible(true);
        } else {
            L_error_concesionario.setVisible(false);
            L_error_campos.setVisible(false);
            L_error_contrasenas.setVisible(false);
            L_error_NIF.setVisible(false);
            L_error_usuario.setVisible(false);

            comercial = new Comercial(concesionario, TF_usuario.getText(), PF_pass1.getText(), TF_NIF.getText(), TF_nombre.getText(), TF_apellidos.getText());
            comDAO.anadirComercial(miEntityManager.getEntityManager(), comercial);

            limpiarCampos();

            cargarComerciales();
        }


    }

    /**
     * Limpia los campos de entrada en la interfaz de usuario.
     */
    private void limpiarCampos() {
        TF_apellidos.setText("");
        TF_nombre.setText("");
        TF_NIF.setText("");
        TF_usuario.setText("");
        PF_pass2.setText("");
        PF_pass1.setText("");
    }

    /**
     * Llena el ComboBox de concesionarios con los datos obtenidos de la base de datos.
     *
     * @param comboBox El ComboBox que se va a llenar con los concesionarios disponibles.
     */
    private void llenarComboBoxConcesionarios(ComboBox<Concesionario> comboBox) {
        List<Concesionario> concesionarios = concDAO.listarConcesionarios(miEntityManager.getEntityManager());
        comboBox.getItems().addAll(concesionarios);
    }

    /**
     * Carga la lista de comerciales desde la base de datos y la muestra en la tabla.
     */
    private void cargarComerciales() {
        List<Comercial> comerciales = comDAO.listarComerciales(miEntityManager.getEntityManager());

        TV_comerciales.getItems().clear();

        if (comerciales != null) {
            for (Comercial comercial : comerciales) {
                comercialesList.add(comercial);
            }
        }

        TV_comerciales.setItems(comercialesList);
    }

    /**
     * Elimina un comercial seleccionado de la base de datos y actualiza la vista.
     *
     * @param actionEvent El evento de acción que desencadenó el método.
     */
    @FXML
    public void eliminar_comercial(ActionEvent actionEvent) {
        Comercial comercial = TV_comerciales.getSelectionModel().getSelectedItem();

        if (comercial != null) {
            comDAO.eliminarComercial(miEntityManager.getEntityManager(), comercial);
            cargarComerciales();
        }
    }

    /**
     * Cierra la sesión actual y abre la pantalla de inicio de sesión.
     *
     * @param actionEvent El evento de acción que desencadenó el método.
     */
    @FXML
    public void cerrar_sesion(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Login.fxml"));

            Parent root = loader.load();

            LoginController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            String path = "/images/fotor-ai-2024020418187-removebg-preview.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("MotoSave");

            Stage myStage = (Stage) this.BTN_salir.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

    /**
     * Abre el panel de control de administrador (dashboard).
     *
     * @param actionEvent El evento de acción que desencadenó el método.
     */
    @FXML
    public void abrirDashboard(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Admin_Dashboard.fxml"));

            Parent root = loader.load();

            AdminDashboardController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            String path = "/images/fotor-ai-2024020418187-removebg-preview.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("MotoSave");

            Stage myStage = (Stage) this.BTN_dashboard.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

    /**
     * Abre la vista del inventario de motocicletas.
     *
     * @param actionEvent El evento de acción que desencadenó el método.
     */
    @FXML
    public void abrirStock(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Admin_Stock.fxml"));

            Parent root = loader.load();

            AdminStockController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            String path = "/images/fotor-ai-2024020418187-removebg-preview.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("MotoSave");

            Stage myStage = (Stage) this.BTN_stock.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

}