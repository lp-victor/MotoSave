package motosave.motosavefx.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminStockController implements Initializable {

    @FXML
    private ComboBox CmB_concesionario;
    @FXML
    private TableView T_motocicletas;
    @FXML
    private Button BTN_agregar;
    @FXML
    private Button BTN_comerciales;
    @FXML
    private Button BTN_eliminar;
    @FXML
    private Button BTN_modificar;
    @FXML
    private Button BTN_stock;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_dashboard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}