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
import javafx.stage.Stage;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.Modelos.Cliente;
import motosave.Modelos.Comercial;
import motosave.Modelos.Concesionario;
import motosave.Modelos.Motocicleta;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminComercialesController implements Initializable {

    private Comercial comercial;
    private ImpComercialDAO comDAO;
    private ImpConcesionarioDAO concDAO;
    ObservableList<Comercial> comercialesList;

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
    private Button BTN_agregar;
    @FXML
    private Button BTN_eliminar;
    @FXML
    private Button BTN_salir;
    @FXML
    private TableView<Comercial> TV_comerciales;
    @FXML
    private Button BTN_dashboard;
    @FXML
    private Button BTN_stock;
    @FXML
    private Label L_indentificacion_comercial;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comDAO = new ImpComercialDAO();
        concDAO = new ImpConcesionarioDAO();
        comercialesList = FXCollections.observableArrayList();

        cargarDatos ();
    }

    private void cargarDatos (){
        llenarComboBoxConcesionarios(CmB_concesionario);

        C_nombre.setCellValueFactory(new PropertyValueFactory<Comercial, String>("nombre"));
        C_concesionario.setCellValueFactory(new PropertyValueFactory<Comercial, Concesionario>("concesionario"));
        C_apellidos.setCellValueFactory(new PropertyValueFactory<Comercial, String>("apellido"));
        C_NIF.setCellValueFactory(new PropertyValueFactory<Comercial, String>("NIF"));
        C_usuario.setCellValueFactory(new PropertyValueFactory<Comercial, String>("usuario"));

        cargarComerciales();
    }

    @FXML
    public void agregarComercial(ActionEvent actionEvent) {
        L_error_concesionario.setVisible(false);
        L_error_campos.setVisible(false);
        L_error_contrasenas.setVisible(false);

        Concesionario concesionario = CmB_concesionario.getValue();
        if (concesionario == null) {
            L_error_concesionario.setVisible(true);
        } else if (TF_usuario.getText().isBlank() || PF_pass1.getText().isBlank() ||TF_NIF.getText().isBlank() ||TF_nombre.getText().isBlank() ||TF_apellidos.getText().isBlank() ||PF_pass2.getText().isBlank()){
            L_error_campos.setVisible(true);
        }else if (!PF_pass1.getText().equals(PF_pass2.getText())){
            L_error_contrasenas.setVisible(true);
        } else {
            L_error_concesionario.setVisible(false);
            L_error_campos.setVisible(false);
            L_error_contrasenas.setVisible(false);

            comercial = new Comercial(concesionario, TF_usuario.getText(), PF_pass1.getText(), TF_NIF.getText(), TF_nombre.getText(), TF_apellidos.getText());
            comDAO.anadirComercial(miEntityManager.getEntityManager(), comercial);

            limpiarCampos();

            cargarComerciales();
        }


    }

    private void limpiarCampos() {
        TF_apellidos.setText("");
        TF_nombre.setText("");
        TF_NIF.setText("");
        TF_usuario.setText("");
        PF_pass2.setText("");
        PF_pass1.setText("");
    }


    private void llenarComboBoxConcesionarios(ComboBox<Concesionario> comboBox) {
        List<Concesionario> concesionarios = concDAO.listarConcesionarios(miEntityManager.getEntityManager());
        comboBox.getItems().addAll(concesionarios);
    }

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

    @FXML
    public void eliminar_comercial(ActionEvent actionEvent) {
        Comercial comercial = TV_comerciales.getSelectionModel().getSelectedItem();

        if (comercial != null) {
            comDAO.eliminarComercial(miEntityManager.getEntityManager(), comercial);
            cargarComerciales();
        }
    }

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

            Stage myStage = (Stage) this.BTN_salir.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

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

            Stage myStage = (Stage) this.BTN_dashboard.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

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

            Stage myStage = (Stage) this.BTN_stock.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

}