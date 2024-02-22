package motosave.motosavefx.controlador;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.ImplementacionesDAO.ImpVentaDAO;
import motosave.Modelos.Comercial;
import motosave.Modelos.Concesionario;
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
public class AdminDashboardController implements Initializable {

    /**
     * Formato utilizado para mostrar las fechas en el panel de control.
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * Nombres de los meses para mostrar en las gráficas.
     */
    private static final String[] NOMBRES_MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    ImpComercialDAO comDAO;
    ImpVentaDAO ventaDAO;
    ObservableList<Comercial> comercialesList;
    Comercial comercialSeleccionado;
    @FXML
    private ComboBox<String> CB_tiempo;
    @FXML
    private Button BTN_comerciales;
    @FXML
    private Button BTN_stock;
    @FXML
    private Button BTN_salir;
    @FXML
    private Label L_mas_liquido;
    @FXML
    private Label L_mas_ventas;
    @FXML
    private TableColumn<Comercial, String> C_nombre;
    @FXML
    private TableColumn<Comercial, Concesionario> C_concesionario;
    @FXML
    private TableColumn<Comercial, String> C_apellidos;
    @FXML
    private TableColumn<Comercial, String> C_NIF;
    @FXML
    private TableColumn<Comercial, String> C_usuario;
    @FXML
    private TableView<Comercial> TV_comerciales;
    @FXML
    private BarChart<Number, String> BC_ventas;
    @FXML
    private LineChart<String, Number> LC_liquido;
    @FXML
    private Label L_error_anio;

    /**
     * Inicializa el controlador.
     * Configura las variables y llama al método cargarDatos() para cargar los datos iniciales en la interfaz de usuario.
     *
     * @param url            La URL del archivo FXML.
     * @param resourceBundle El ResourceBundle utilizado para localizar objetos de la interfaz de usuario.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comDAO = new ImpComercialDAO();
        ventaDAO = new ImpVentaDAO();
        comercialesList = FXCollections.observableArrayList();
        comercialSeleccionado = null;
        cargarDatos();

        TV_comerciales.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                comercialSeleccionado = TV_comerciales.getSelectionModel().getSelectedItem();
                rellenarVentasAnio(comercialSeleccionado);
            }
        });

        CB_tiempo.setOnAction(event -> rellenarVentasAnio(comercialSeleccionado));
    }

    /**
     * Método de inicialización para restablecer la selección del comercial.
     *
     * @param actionEvent El evento de acción que desencadenó el método.
     */
    @FXML
    public void restablecerSeleccionComercial(ActionEvent actionEvent) {
        comercialSeleccionado = null;
        rellenarVentasAnio(comercialSeleccionado);
    }

    /**
     * Carga los datos iniciales en la interfaz de usuario.
     * Llena el ComboBox con años disponibles y configura la tabla para mostrar los comerciales.
     */
    private void cargarDatos() {
        llenarComboBoxAnios();

        // Tabla existencias
        C_nombre.setCellValueFactory(new PropertyValueFactory<Comercial, String>("nombre"));
        C_concesionario.setCellValueFactory(new PropertyValueFactory<Comercial, Concesionario>("concesionario"));
        C_apellidos.setCellValueFactory(new PropertyValueFactory<Comercial, String>("apellido"));
        C_NIF.setCellValueFactory(new PropertyValueFactory<Comercial, String>("NIF"));
        C_usuario.setCellValueFactory(new PropertyValueFactory<Comercial, String>("usuario"));

        cargarComerciales();
    }

    /**
     * Carga la lista de comerciales desde la base de datos y la muestra en la tabla.
     */
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

    /**
     * Llena el ComboBox con los años disponibles.
     */
    private void llenarComboBoxAnios() {
        CB_tiempo.getItems().addAll("2024", "2023", "2022");
    }

    /**
     * Llena el ComboBox con los años disponibles.
     */
    private void rellenarVentasAnio(Comercial comercialSeleccionado) {
        String anioSeleccionado = CB_tiempo.getValue();
        List<Venta> ventasAnio;

        if (comercialSeleccionado != null) {
            // Si hay un comercial seleccionado, obtener ventas solo para ese comercial
            ventasAnio = obtenerVentasPorAnioComercial(anioSeleccionado, comercialSeleccionado);
        } else {
            // Si no hay selección, obtener ventas de todos los comerciales
            ventasAnio = obtenerVentasPorAnioTodosComerciales(anioSeleccionado);
        }

        actualizarLabelsComercialesDestacados();
        rellenarVentasPorAnioBarChart(ventasAnio, comercialSeleccionado);
        rellenarVentasPorAnoLineChart(ventasAnio, comercialSeleccionado);
    }

    /**
     * Obtiene las ventas por año para todos los comerciales.
     *
     * @param anio El año seleccionado.
     * @return Una lista de ventas para el año seleccionado.
     */
    private List<Venta> obtenerVentasPorAnioTodosComerciales(String anio) {
        // Lista para almacenar las ventas por año
        List<Venta> ventasPorAnio = new ArrayList<>();
        // Map para almacenar el conteo de ventas por comercial


        LocalDate fechaInicio = LocalDate.parse(anio + "-01-01", FORMATTER);
        LocalDate fechaFin = LocalDate.parse(anio + "-12-31", FORMATTER);

        List<Venta> todasLasVentas = ventaDAO.listarVentasGeneral(miEntityManager.getEntityManager());

        for (Venta venta : todasLasVentas) {
            LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta());
            if (!fechaVenta.isBefore(fechaInicio) && !fechaVenta.isAfter(fechaFin)) {
                ventasPorAnio.add(venta);
            }
        }

        return ventasPorAnio;
    }

    /**
     * Obtiene las ventas por año para un comercial específico.
     *
     * @param anio      El año seleccionado.
     * @param comercial El comercial seleccionado.
     * @return Una lista de ventas para el año seleccionado y el comercial especificado.
     */
    private List<Venta> obtenerVentasPorAnioComercial(String anio, Comercial comercial) {
        List<Venta> todasLasVentasComercial = null;
        List<Venta> ventasPorAnio = new ArrayList<>();
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;

        if (anio != null) {
            L_error_anio.setVisible(false);
            fechaInicio = LocalDate.parse(anio + "-01-01", FORMATTER);
            fechaFin = LocalDate.parse(anio + "-12-31", FORMATTER);
        } else {
            L_error_anio.setVisible(true);
        }

        if (comercial != null) {
            todasLasVentasComercial = ventaDAO.listarVentasComercial(miEntityManager.getEntityManager(), comercial.getId_comercial());
        }
        if (todasLasVentasComercial != null) {
            for (Venta venta : todasLasVentasComercial) {
                LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta());
                if (!fechaVenta.isBefore(fechaInicio) && !fechaVenta.isAfter(fechaFin)) {
                    ventasPorAnio.add(venta);
                }
            }
        }
        return ventasPorAnio;
    }

    /**
     * Rellena la gráfica de barras con las ventas por mes.
     *
     * @param ventas               La lista de ventas para el año seleccionado.
     * @param comercialSeleccionado El comercial seleccionado (puede ser null si no hay selección).
     */
    private void rellenarVentasPorAnioBarChart(List<Venta> ventas, Comercial comercialSeleccionado) {
        Map<Comercial, int[]> ventasPorComercialYMes = new HashMap<>(); // Hashmap con los comerciales y sus ventas por mes

        for (Venta venta : ventas) {
            Comercial comercial = venta.getComercial();
            LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta()); // Transformamos la fecha al formato yy-MM-dd
            int[] contadorMeses = ventasPorComercialYMes.getOrDefault(comercial, new int[12]); // Recoge o crea un array de ventas
            // para cada comercial (default int[12])

            contadorMeses[fechaVenta.getMonthValue() - 1]++; // Rellena el numero ventas por mes (resta 1 para que los valores
            //  coincidan con los correspondientes en el Array)

            ventasPorComercialYMes.put(comercial, contadorMeses); // Completa el HashMap
        }


        Platform.runLater(() -> { // Controla que la tabla solo se actualize en el hilo de JavaFX
            BC_ventas.getData().clear(); // Limpia los datos anteriores si hubiere
            if (comercialSeleccionado == null) {
                ventasPorComercialYMes.forEach((comercial, ventasMes) -> {
                    XYChart.Series<Number, String> set = new XYChart.Series<>();

                    if (comercial != null) {
                        set.setName(comercial.getNombre()); // Establece la leyenda (comercial)
                    }

                    for (int i = 0; i < ventasMes.length; i++) {
                        set.getData().add(new XYChart.Data<>(ventasMes[i], NOMBRES_MESES[i]));
                    }

                    BC_ventas.getData().add(set); // Añade los datos formateados a la tabla

                });
            } else {

                XYChart.Series<Number, String> set = new XYChart.Series<>();
                set.setName(comercialSeleccionado.getNombre()); // Usa el nombre del comercial seleccionado

                int[] contadorMeses = ventasPorComercialYMes.get(comercialSeleccionado);
                for (int i = 0; i < contadorMeses.length; i++) {
                    XYChart.Data<Number, String> data = new XYChart.Data<>(contadorMeses[i], NOMBRES_MESES[i]);
                    set.getData().add(data);
                }
                BC_ventas.getData().add(set);
            }
        });
    }

    /**
     * Rellena la gráfica de líneas con los ingresos por mes.
     *
     * @param ventas               La lista de ventas para el año seleccionado.
     * @param comercialSeleccionado El comercial seleccionado (puede ser null si no hay selección).
     */
    private void rellenarVentasPorAnoLineChart(List<Venta> ventas, Comercial comercialSeleccionado) {
        Map<Comercial, double[]> ingresosPorComercialYMes = new HashMap<>();

        // Agrupar ingresos por comercial y mes
        for (Venta venta : ventas) {
            Comercial comercial = venta.getComercial(); // Obtén el comercial de cada venta
            if (comercialSeleccionado == null || comercial.equals(comercialSeleccionado)) {
                LocalDate fechaVenta = convertirALocalDate(venta.getFecha_venta());
                double[] ingresosMeses = ingresosPorComercialYMes.computeIfAbsent(comercial, k -> new double[12]);
                ingresosMeses[fechaVenta.getMonthValue() - 1] += venta.getPrecio_final(); // Suma los ingresos
            }
        }

        Platform.runLater(() -> {
            LC_liquido.getData().clear();
            if (comercialSeleccionado == null) {
                // Caso para todos los comerciales: agregue una serie por comercial
                ingresosPorComercialYMes.forEach((comercial, ingresosMeses) -> {
                    XYChart.Series<String, Number> seriesComercial = new XYChart.Series<>();
                    seriesComercial.setName(comercial.getNombre());
                    for (int i = 0; i < ingresosMeses.length; i++) {
                        seriesComercial.getData().add(new XYChart.Data<>(NOMBRES_MESES[i], ingresosMeses[i]));
                    }
                    LC_liquido.getData().add(seriesComercial);
                });
            } else {
                // Caso para un comercial específico
                double[] ingresosComercialSeleccionado = ingresosPorComercialYMes.getOrDefault(comercialSeleccionado, new double[12]);
                XYChart.Series<String, Number> seriesComercial = new XYChart.Series<>();
                seriesComercial.setName(comercialSeleccionado.getNombre());
                for (int i = 0; i < ingresosComercialSeleccionado.length; i++) {
                    seriesComercial.getData().add(new XYChart.Data<>(NOMBRES_MESES[i], ingresosComercialSeleccionado[i]));
                }
                LC_liquido.getData().add(seriesComercial);
            }
        });
    }

    /**
     * Actualiza las etiquetas de los comerciales con más ventas y más ingresos.
     */
    private void actualizarLabelsComercialesDestacados() {
        String anioSeleccionado = CB_tiempo.getValue();
        if (anioSeleccionado == null || anioSeleccionado.isEmpty()) {
            return; // No hacer nada si no se ha seleccionado un año
        }

        // Inicializa variables para almacenar el comercial con más ventas y más ingresos
        Comercial comercialMasVentas = null;
        Comercial comercialMasLiquido = null;
        int maxVentas = 0;
        double maxLiquido = 0.0;

        // Obtiene todas las ventas del año seleccionado
        List<Venta> ventasAnio = obtenerVentasPorAnioTodosComerciales(anioSeleccionado);

        // Crea un mapa para contar las ventas e ingresos por comercial
        Map<Comercial, Integer> contadorVentas = new HashMap<>();
        Map<Comercial, Double> contadorLiquido = new HashMap<>();

        for (Venta venta : ventasAnio) {
            Comercial comercial = venta.getComercial();
            contadorVentas.put(comercial, contadorVentas.getOrDefault(comercial, 0) + 1);
            contadorLiquido.put(comercial, contadorLiquido.getOrDefault(comercial, 0.0) + venta.getPrecio_final());

            // Verifica si este comercial ha vendido más unidades
            if (contadorVentas.get(comercial) > maxVentas) {
                maxVentas = contadorVentas.get(comercial);
                comercialMasVentas = comercial;
            }

            // Verifica si este comercial ha generado más ingresos
            if (contadorLiquido.get(comercial) > maxLiquido) {
                maxLiquido = contadorLiquido.get(comercial);
                comercialMasLiquido = comercial;
            }
        }

        // Actualiza las etiquetas con los nombres de los comerciales
        if (comercialMasVentas != null) {
            L_mas_ventas.setText(comercialMasVentas.getNombre());
        } else {
            L_mas_ventas.setText("N/D");
        }

        if (comercialMasLiquido != null) {
            L_mas_liquido.setText(comercialMasLiquido.getNombre());
        } else {
            L_mas_liquido.setText("N/D");
        }
    }

    /**
     * Convierte un objeto Date a LocalDate.
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

    // ====================================================================
    /**
     * Abre la vista del inventario de motocicletas.
     *
     * @param actionEvent El evento de acción que desencadenó el método.
     */
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

    /**
     * Cierra la sesión actual y abre la pantalla de inicio de sesión.
     *
     * @param actionEvent El evento de acción que desencadenó el método.
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

            Stage myStage = (Stage) this.BTN_salir.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

    /**
     * Abre la vista de gestión de comerciales.
     *
     * @param actionEvent El evento de acción que desencadenó el método.
     */
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

