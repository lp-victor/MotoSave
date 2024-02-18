package motosave.motosavefx.controlador;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.Modelos.Comercial;
import motosave.Modelos.Concesionario;
import motosave.Modelos.Venta;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    ImpComercialDAO comDAO;

    ObservableList<Comercial> comercialesList ;
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
    private Label L_mas_liquido;
    @FXML
    private Label L_mas_ventas;
    @FXML
    private TableColumn C_nombre;
    @FXML
    private TableColumn C_concesionario;
    @FXML
    private TableColumn C_apellidos;
    @FXML
    private TableColumn C_NIF;
    @FXML
    private TableColumn C_usuario;
    @FXML
    private TableView TV_comerciales;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comDAO = new ImpComercialDAO();
        comercialesList = FXCollections.observableArrayList();

        cargarDatos ();
    }


    private void cargarDatos (){
        //llenarComboBoxAnios();

        // Tabla existencias
        C_nombre.setCellValueFactory(new PropertyValueFactory<Comercial, String>("nombre"));
        C_concesionario.setCellValueFactory(new PropertyValueFactory<Comercial, Concesionario>("concesionario"));
        C_apellidos.setCellValueFactory(new PropertyValueFactory<Comercial, String>("apellido"));
        C_NIF.setCellValueFactory(new PropertyValueFactory<Comercial, String>("NIF"));
        C_usuario.setCellValueFactory(new PropertyValueFactory<Comercial, String>("usuario"));

        cargarComerciales();
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

    // ============================ POR TERMINAR ====================================
//    private void llenarComboBoxAnios(){
//        CB_tiempo.getItems().addAll("2024", "2023", "2022");
//        CB_tiempo.setOnAction(event -> rellenarGraficosDatos());
//    }
//    private List<Venta> obtenerVentasPorAnio(String anio) {
//        List<Venta> ventasPorAnio = new ArrayList<>();
//
//        LocalDate fechaInicio = LocalDate.parse(anio + "-01-01", FORMATTER);
//        LocalDate fechaFin = LocalDate.parse(anio + "-12-31", FORMATTER);
//
//        List<Venta> todasLasVentas = ventadao.listarVentasComercial(miEntity, comercial.getId_comercial());
//
//        for (Venta venta : todasLasVentas) {
//            LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta());
//            if (!fechaVenta.isBefore(fechaInicio) && !fechaVenta.isAfter(fechaFin)) {
//                ventasPorAnio.add(venta);
//            }
//        }
//
//        return ventasPorAnio;
//    }
//
//    private void rellenarVentasAnio() {
//        List<Venta> ventasAnio = obtenerVentasPorAnio(CB_tiempo.getValue());
//        rellenarVentasPorAnioBarChart(ventasAnio);
//        rellenarVentasPorAnoLineChart(ventasAnio);
//    }
//
//    private void rellenarVentasPorAnioBarChart(List<Venta> ventas) {
//        int[] contadorMeses = new int[12];
//
//        for (Venta venta : ventas) {
//            LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta());
//            contadorMeses[fechaVenta.getMonthValue() - 1]++;
//        }
//
//        XYChart.Series<Number, String> set = new XYChart.Series<>();
//        for (int i = 0; i < contadorMeses.length; i++) {
//            set.getData().add(new XYChart.Data<>(contadorMeses[i], NOMBRES_MESES[i]));
//        }
//
//        Platform.runLater(() -> {
//            BC_barra_ventas.getData().clear();
//            BC_barra_ventas.getData().add(set);
//        });
//    }
//
//    private void rellenarVentasPorAnoLineChart(List<Venta> ventas) {
//        XYChart.Series<String, Number> series = new XYChart.Series<>();
//        series.setName("Ingresos Mensuales"); // Cambiamos "Ventas Mensuales" por "Ingresos Mensuales"
//
//        double[] ingresosMeses = new double[12];
//        for (Venta venta : ventas) {
//            LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta());
//            int mes = fechaVenta.getMonthValue() - 1;
//            ingresosMeses[mes] += venta.getPrecio_final(); // Sumamos el precio final de cada venta al mes correspondiente
//        }
//
//        // Asume que NOMBRES_MESES es un array que contiene los nombres de los meses.
//        for (int i = 0; i < ingresosMeses.length; i++) {
//            series.getData().add(new XYChart.Data<>(NOMBRES_MESES[i], ingresosMeses[i]));
//        }
//
//        // Asegura que la actualización de la UI se haga en el hilo de la aplicación de JavaFX
//        Platform.runLater(() -> {
//            LC_barra_liquido.getData().clear(); // Limpia datos anteriores
//            LC_barra_liquido.getData().add(series); // Añade la nueva serie de datos
//        });
//    }

// ====================================================================
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

