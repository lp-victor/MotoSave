package motosave.motosavefx.controlador;

import jakarta.persistence.EntityManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import motosave.DATA.ComercialLoggeado;
import motosave.ImplementacionesDAO.ImpVentaDAO;
import motosave.Modelos.Comercial;
import motosave.Modelos.Venta;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * @author MotoSave
 */
public class ComercialEstadisticasController implements Initializable {
    /**
     * Formato utilizado para mostrar las fechas en el panel de control.
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * Nombres de los meses para mostrar en las gráficas.
     */
    private static final String[] NOMBRES_MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    @FXML
    private ComboBox<String> CB_tiempo;
    @FXML
    private BarChart<Number, String> BC_barra_ventas;
    private ImpVentaDAO ventadao;
    private Comercial comercial;
    private EntityManager miEntity;
    @FXML
    private Button BTN_ventas;
    @FXML
    private Label L_bienvenido;
    @FXML
    private Button BTN_salir;
    @FXML
    private LineChart LC_barra_liquido;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Label L_indentificacion_comercial;

    /**
     * Inicializa el controlador.
     *
     * @param url            La URL de inicialización.
     * @param resourceBundle El ResourceBundle utilizado para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ventadao = new ImpVentaDAO();
        comercial = ComercialLoggeado.getComercialLoggeado();
        miEntity = miEntityManager.getEntityManager();

        L_indentificacion_comercial.setText(comercial.getNombre());
        L_sede_comercial.setText(String.valueOf(comercial.getConcesionario()));
        L_bienvenido.setText("Bienvenido " + comercial.getNombre());

        cargarCB();
    }

    /**
     * Carga los años disponibles en el ComboBox.
     */
    private void cargarCB() {
        CB_tiempo.getItems().addAll("2024", "2023", "2022");
        CB_tiempo.setOnAction(event -> rellenarVentasAnio());
    }

    /**
     * Obtiene las ventas del comercial para el año seleccionado.
     *
     * @param anio El año seleccionado.
     * @return La lista de ventas del año.
     */
    private List<Venta> obtenerVentasPorAnio(String anio) {
        List<Venta> ventasPorAnio = new ArrayList<>();

        LocalDate fechaInicio = LocalDate.parse(anio + "-01-01", FORMATTER);
        LocalDate fechaFin = LocalDate.parse(anio + "-12-31", FORMATTER);

        List<Venta> todasLasVentas = ventadao.listarVentasComercial(miEntity, comercial.getId_comercial());

        for (Venta venta : todasLasVentas) {
            LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta());
            if (!fechaVenta.isBefore(fechaInicio) && !fechaVenta.isAfter(fechaFin)) {
                ventasPorAnio.add(venta);
            }
        }

        return ventasPorAnio;
    }

    /**
     * Rellena los gráficos con las ventas del año seleccionado.
     */
    private void rellenarVentasAnio() {
        List<Venta> ventasAnio = obtenerVentasPorAnio(CB_tiempo.getValue());
        rellenarVentasPorAnioBarChart(ventasAnio);
        rellenarVentasPorAnoLineChart(ventasAnio);
    }

    /**
     * Rellena el gráfico de barras con las ventas mensuales del año seleccionado.
     *
     * @param ventas La lista de ventas del año.
     */
    private void rellenarVentasPorAnioBarChart(List<Venta> ventas) {
        int[] contadorMeses = new int[12];

        for (Venta venta : ventas) {
            LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta());
            contadorMeses[fechaVenta.getMonthValue() - 1]++;
        }

        XYChart.Series<Number, String> set = new XYChart.Series<>();
        for (int i = 0; i < contadorMeses.length; i++) {
            set.getData().add(new XYChart.Data<>(contadorMeses[i], NOMBRES_MESES[i]));
        }

        Platform.runLater(() -> {
            BC_barra_ventas.getData().clear();
            BC_barra_ventas.getData().add(set);
        });
    }

    /**
     * Rellena el gráfico de líneas con los ingresos mensuales del año seleccionado.
     *
     * @param ventas La lista de ventas del año.
     */
    private void rellenarVentasPorAnoLineChart(List<Venta> ventas) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ingresos Mensuales"); // Cambiamos "Ventas Mensuales" por "Ingresos Mensuales"

        double[] ingresosMeses = new double[12];
        for (Venta venta : ventas) {
            LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta());
            int mes = fechaVenta.getMonthValue() - 1;
            ingresosMeses[mes] += venta.getPrecio_final(); // Sumamos el precio final de cada venta al mes correspondiente
        }

        // Asume que NOMBRES_MESES es un array que contiene los nombres de los meses.
        for (int i = 0; i < ingresosMeses.length; i++) {
            series.getData().add(new XYChart.Data<>(NOMBRES_MESES[i], ingresosMeses[i]));
        }

        // Asegura que la actualización de la UI se haga en el hilo de la aplicación de JavaFX
        Platform.runLater(() -> {
            LC_barra_liquido.getData().clear(); // Limpia datos anteriores
            LC_barra_liquido.getData().add(series); // Añade la nueva serie de datos
        });
    }

    //===============================================================================================================
    /**
     * Abre la ventana de ventas.
     *
     * @param actionEvent El evento de acción.
     */
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

            String path = "/images/fotor-ai-2024020418187-removebg-preview.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("MotoSave");

            Stage myStage = (Stage) this.BTN_ventas.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Cierra la sesión actual y abre la ventana de inicio de sesión.
     *
     * @param actionEvent El evento de acción.
     */
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

            String path = "/images/fotor-ai-2024020418187-removebg-preview.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("MotoSave");

            Stage myStage = (Stage) this.BTN_salir.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }

    }

    /**
     * Convierte un objeto Date a un objeto LocalDate.
     *
     * @param fecha La fecha a convertir.
     * @return La fecha convertida a LocalDate.
     */
    private LocalDate convertirALocalDate(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int year = cal.get(Calendar.YEAR);
        // En Calendar, el mes comienza desde 0, por lo tanto se añade 1.
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return LocalDate.of(year, month, day);
    }
}