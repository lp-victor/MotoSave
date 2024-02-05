package motosave.motosavefx.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ComercialVentasController implements Initializable {


    @FXML
    private Button BTN_ventas;
    @FXML
    private TextField TF_apellidosCliente;
    @FXML
    private ComboBox CmB_concesionarios;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_vender;
    @FXML
    private TextField TF_telefonoCliente;
    @FXML
    private Button BTN_estadisticas;
    @FXML
    private TextField TF_correoCliente;
    @FXML
    private TableView T_tablaExistencias;
    @FXML
    private TextField TF_nombreCliente;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private TextField TF_direccion;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Pane P_comercialVentas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static void closeWindow(){

    }


}