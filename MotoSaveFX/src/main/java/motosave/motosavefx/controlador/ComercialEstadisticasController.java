package motosave.motosavefx.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
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
    private LineChart<?, ?> LC_barra_ventas;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private BarChart<?, ?> BC_barra_liquido;
    @FXML
    private Pane P_comercialEstadisticas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rellenar_liquido();
        rellenar_ventas();
    }

    @FXML
    public void salir_ventana(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Login.fxml"));

            Parent root = loader.load();

            ComercialVentasController controller = loader.getController();

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
    public void rellenar_ventas() {

        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("Enero", 32));
        set1.getData().add(new XYChart.Data("Febrero", 23));
        set1.getData().add(new XYChart.Data("Marzo", 13));
        set1.getData().add(new XYChart.Data("Abril", 31));
        set1.getData().add(new XYChart.Data("Mayo", 42));
        set1.getData().add(new XYChart.Data("Junio", 29));

        BC_barra_liquido.getData().addAll(set1);
    }

    @FXML
    public void rellenar_liquido() {

        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("1", 3500));
        set1.getData().add(new XYChart.Data("2", 2030));
        set1.getData().add(new XYChart.Data("3", 1300));
        set1.getData().add(new XYChart.Data("4", 3100));
        set1.getData().add(new XYChart.Data("5", 4200));
        set1.getData().add(new XYChart.Data("6", 2900));

        LC_barra_ventas.getData().addAll(set1);
    }
}