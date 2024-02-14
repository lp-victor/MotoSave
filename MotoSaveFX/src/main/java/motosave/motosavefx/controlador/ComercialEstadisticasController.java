package motosave.motosavefx.controlador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ComercialEstadisticasController implements Initializable {
    @FXML
    private ComboBox<Date> CB_tiempo;
    @FXML
    private Button BTN_salir;
    @FXML
    private BarChart<String, Number> BC_barra_liquido;
    @FXML
    private Pane P_comercialEstadisticas;
    @FXML
    private Button BTN_ventas;
    @FXML
    private Label L_bienvenido;
    @FXML
    private LineChart<String, Number> LC_barra_ventas;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Label L_indentificacion_comercial;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //rellenar_liquido();
        //rellenar_ventas();
    }

    @Deprecated
    public void rellenarDatosDesdeBD(String tiempoSeleccionado) {

    }

    private void llenarGraficosConDatos(List<miEntityManager> datos) {

    }

    /*
    @FXML
    public void rellenar_ventas() {

        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("Enero", 32));
        set1.getData().add(new XYChart.Data("Febrero", 23));
        set1.getData().add(new XYChart.Data("Marzo", 13));
        set1.getData().add(new XYChart.Data("Abril", 31));
        set1.getData().add(new XYChart.Data("Mayo", 102));
        set1.getData().add(new XYChart.Data("Junio", 29));
        set1.getData().add(new XYChart.Data("Julio", 43));
        set1.getData().add(new XYChart.Data("Agosto", 1));
        set1.getData().add(new XYChart.Data("Septiembre", 65));
        set1.getData().add(new XYChart.Data("Octubre", 24));

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
    }*/

    @FXML
    public void abrir_ventas(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Comercial_Ventas.fxml"));

            Parent root = loader.load();

            ComercialVentasController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_ventas.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            ex.printStackTrace();
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

    // Falta hacer el DAO de ventas para esto
//    public void actualizarLineChartVentasPorSeleccion(ComboBox<String> comboBox) {
//        String seleccion = comboBox.getSelectionModel().getSelectedItem();
//        EntityManager em = miEntityManager.getEntityManager();
//
//        try {
//            // Preparar la consulta base
//            String jpql = "SELECT v.fechaVenta, COUNT(v) FROM Venta v WHERE v.fechaVenta >= :fechaInicio GROUP BY v.fechaVenta ORDER BY v.fechaVenta";
//
//            // Calcular fecha de inicio basada en la selección
//            LocalDate fechaInicio = LocalDate.now(); // Valor por defecto para evitar warning
//            switch (seleccion) {
//                case "Última Semana":
//                    fechaInicio = LocalDate.now().minusWeeks(1);
//                    break;
//                case "Último Mes":
//                    fechaInicio = LocalDate.now().minusMonths(1);
//                    break;
//                case "Último Año":
//                    fechaInicio = LocalDate.now().minusYears(1);
//                    break;
//            }
//
//            // Ejecutar consulta
//            List<Object[]> resultados = em.createQuery(jpql, Object[].class)
//                    .setParameter("fechaInicio", fechaInicio)
//                    .getResultList();
//
//            // Preparar la serie de datos para la gráfica
//            XYChart.Series<String, Number> serieVentas = new XYChart.Series<>();
//            serieVentas.setName("Ventas"); // Este nombre aparecerá como leyenda en la gráfica
//
//            // Llenar la serie con los resultados de la consulta
//            for (Object[] resultado : resultados) {
//                LocalDate fecha = (LocalDate) resultado[0];
//                Long cantidad = (Long) resultado[1];
//                serieVentas.getData().add(new XYChart.Data<>(fecha.toString(), cantidad));
//            }
//
//            // Actualizar la LineChart
//            LC_barra_ventas.getData().clear();
//            LC_barra_ventas.getData().add(serieVentas);
//        } finally {
//            em.close();
//        }
//    }

    // Cerrar la aplicación al presionar el botón salir
    @Deprecated
    public void cerrarAplicacion(ActionEvent actionEvent) {
        Stage stage = (Stage) BTN_salir.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void rellenar_ventas(Event event) {
    }

    @FXML
    public void rellenar_liquido(Event event) {
    }
}