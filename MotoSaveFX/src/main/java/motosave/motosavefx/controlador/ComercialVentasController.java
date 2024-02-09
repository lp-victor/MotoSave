package motosave.motosavefx.controlador;

import com.dlsc.formsfx.model.validators.RegexValidator;
import jakarta.persistence.EntityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import motosave.DATA.ComercialLoggeado;
import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.Modelos.Cliente;
import motosave.Modelos.Comercial;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ComercialVentasController implements Initializable {

    ImpConcesionarioDAO concDAO;
    String concesionarioSeleccionado = "";

    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_estadisticas;
    @FXML
    private TableView T_tablaExistencias;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Pane P_comercialVentas;
    @FXML
    private ComboBox CmB_concesionarios;
    @FXML
    private TextField TF_nombreCliente;
    @FXML
    private TextField TF_direccion;
    @FXML
    private TextField TF_apellidosCliente;
    @FXML
    private Label L_control_telefono;
    @FXML
    private Button BTN_vender;
    @FXML
    private TextField TF_telefonoCliente;
    @FXML
    private TextField TF_correoCliente;
    @FXML
    private Label L_control_vacios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        concDAO = new ImpConcesionarioDAO();
        llenarComboBoxConcesionarios(CmB_concesionarios);
    }


    @Deprecated
    public void logOut(ActionEvent actionEvent) {
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

        }
    }

    @FXML
    public void abrir_estadisticas(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Comercial_Estadisticas.fxml"));

            Parent root = loader.load();

            ComercialEstadisticasController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_estadisticas.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {

        }
    }

    @FXML
    public void seleccionar_concesionario(ActionEvent actionEvent) {
        concesionarioSeleccionado = CmB_concesionarios.getValue().toString();
    }

    public void llenarComboBoxConcesionarios(ComboBox<String> comboBox) {
        List<String> nombresConcesionarios = concDAO.listarNombreConcesionarios(miEntityManager.getEntityManager());
        if (nombresConcesionarios != null) {
            comboBox.getItems().clear(); // Limpia el ComboBox antes de rellenarlo
            comboBox.getItems().addAll(nombresConcesionarios);
        }
    }

    @FXML
    public void realizar_venta(ActionEvent actionEvent) {

        if (comprobarDatosCliente()){
            Cliente cliente = new Cliente(TF_nombreCliente.getText(), TF_apellidosCliente.getText(), TF_correoCliente.getText(), Integer.getInteger(TF_telefonoCliente.getText()), TF_direccion.getText());
            Comercial comercial = ComercialLoggeado.getComercialLoggeado();

            // Fecha de compra
            long miliseconds = System.currentTimeMillis();
            Date fecha_compra = new Date(miliseconds);
            // Moto seleccionada
            // double precioFinal = moto.getPrecio_compra() *
        }

    }

    private boolean comprobarDatosCliente (){
        L_control_vacios.setVisible(false);
        L_control_telefono.setVisible(false);

            if (TF_nombreCliente.getText().isEmpty() || TF_apellidosCliente.getText().isEmpty() || TF_correoCliente.getText().isEmpty() || TF_telefonoCliente.getText().isEmpty() || TF_direccion.getText().isEmpty()) {
                L_control_vacios.setVisible(true);
                return false;
            } else if (! TF_telefonoCliente.getText().matches("^\\d+$")) {
                L_control_telefono.setVisible(true);
                return false;
            } else {
                return true;
            }
    }

    @FXML
    public void cerrar_sesion(ActionEvent actionEvent) {
    }
}