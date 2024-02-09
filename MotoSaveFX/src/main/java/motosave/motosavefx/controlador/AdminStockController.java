package motosave.motosavefx.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminStockController implements Initializable {

    @FXML
    private Button BTN_comerciales;
    @FXML
    private Button BTN_eliminar;
    @FXML
    private Button BTN_modificar;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_dashboard;
    @FXML
    private ComboBox CmB_concesionarios_selecion;
    @FXML
    private TableView T_tablaExistencias;
    @FXML
    private TextField TF_cantidad;
    @FXML
    private Button BTN_comprar;
    @FXML
    private ComboBox CmB_concesionarios_anadir;
    @FXML
    private ComboBox CmB_marca;
    @FXML
    private TextField TF_precio_total;
    @FXML
    private TextField TF_precio_unidad;
    @FXML
    private ComboBox CmB_modelo;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private TextField TF_cilindrada;
    @FXML
    private ComboBox CmB_color;
    @FXML
    private Pane P_comercialVentas;
    @FXML
    private Label L_sede_comercial;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    public void modificarModeloMoto(ActionEvent actionEvent) {
    }

    @FXML
    public void comprarModeloMoto(ActionEvent actionEvent) {
    }

    @FXML
    public void eliminarModeloMoto(ActionEvent actionEvent) {
    }
}