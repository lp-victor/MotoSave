package motosave.motosavefx.controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private LineChart LC_ventas;
    @FXML
    private ComboBox CB_tiempo;
    @FXML
    private Button BTN_comerciales;
    @FXML
    private Button BTN_stock;
    @FXML
    private Button BTN_salir;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private Pane P_comercialEstadisticas;
    @FXML
    private BarChart BC_barra_liquido;
    @FXML
    private TableView TV_comercialesVentas;
    @FXML
    private BarChart BC_barra_gastos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void rellenar_ventas(Event event) {
    }

    @FXML
    public void rellenar_liquido(Event event) {
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
    public void abrirComerciales(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Admin_Comerciales.fxml"));

            Parent root = loader.load();

            AdminComercialesController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_comerciales.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }
}

