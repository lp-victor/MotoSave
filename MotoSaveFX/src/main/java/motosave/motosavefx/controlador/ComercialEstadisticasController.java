package motosave.motosavefx.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ComercialEstadisticasController implements Initializable {
    @FXML
    private ComboBox CB_tiempo;
    @FXML
    private Button BTN_ventas;
    @FXML
    private Label L_bienvenido;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_estadisticas;
    @FXML
    private LineChart LC_barra_ventas;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private BarChart BC_barra_liquido;
    @FXML
    private Pane P_comercialEstadisticas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}