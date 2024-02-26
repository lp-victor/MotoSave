package motosave.motosavefx.controlador;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import motosave.DATA.ComercialLoggeado;
import motosave.DATA.LOAD;
import motosave.ImplementacionesDAO.ImpClienteDAO;
import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.ImplementacionesDAO.ImpMotocicletaDAO;
import motosave.ImplementacionesDAO.ImpVentaDAO;
import motosave.Modelos.*;
import motosave.Persistencia.ClienteXMLReader;
import motosave.Persistencia.ClienteXMLWriter;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
/**
 * @author MotoSave
 */
public class ComercialVentasController implements Initializable {

    private ImpConcesionarioDAO concDAO;
    private ImpMotocicletaDAO motoDAO;
    private ImpVentaDAO ventaDAO;
    private ImpClienteDAO clienteDAO;
    private String concesionarioSeleccionado = "";
    private ObservableList<Motocicleta> motocicletasList;
    private ObservableList<Cliente> clientesList;
    private Comercial comercial;
    private ClienteXMLWriter clienteXMLWriter;
    private ClienteXMLReader clienteXMLReader;
    private final String rutaXML = "clientesXML/clientesXML.xml";

    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_estadisticas;
    @FXML
    private ComboBox CmB_concesionarios;
    @FXML
    private TableView<Motocicleta> T_tablaExistencias;
    @FXML
    private TableColumn<Motocicleta, String> colMarca;
    @FXML
    private TableColumn<Motocicleta, String> colModelo;
    @FXML
    private TableColumn<Motocicleta, String> colColor;
    @FXML
    private TableColumn<Motocicleta, Integer> colCilindrada;
    @FXML
    private TableColumn<Motocicleta, Double> colPrecio;
    @FXML
    private TableColumn<Motocicleta, String> colUbicacion;
    @FXML
    private TableView<Cliente> T_tabla_clientes;
    @FXML
    private TableColumn<Cliente, String> TC_cliente_nombre;
    @FXML
    private TableColumn<Cliente, Integer> TC_cliente_telefono;
    @FXML
    private TableColumn<Cliente, String> TC_cliente_direccion;
    @FXML
    private TableColumn<Cliente, String> TC_cliente_correo;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Label L_error_motocicleta;
    @FXML
    private Label L_error_cliente;
    @FXML
    private Label L_precio_total;
    @FXML
    private Label L_backup_ok;
    @FXML
    private Button BTN_limpiar;
    @FXML
    private Button BTN_vender;

    /**
     * Inicializa el controlador.
     *
     * @param url            La URL de inicialización.
     * @param resourceBundle El ResourceBundle utilizado para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        concDAO = new ImpConcesionarioDAO();
        motoDAO = new ImpMotocicletaDAO();
        ventaDAO = new ImpVentaDAO();
        clienteDAO = new ImpClienteDAO();
        motocicletasList = FXCollections.observableArrayList();
        clientesList = FXCollections.observableArrayList();
        comercial = ComercialLoggeado.getComercialLoggeado();
        L_indentificacion_comercial.setText(comercial.getNombre());
        L_sede_comercial.setText(String.valueOf(comercial.getConcesionario().getUbicacion()));
        clienteXMLWriter = new ClienteXMLWriter();
        clienteXMLReader = new ClienteXMLReader();

        cargarDatos();
    }

    /**
     * Abre la ventana de estadísticas.
     *
     * @param actionEvent El evento de acción.
     */
    @FXML
    public void abrir_estadisticas(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Comercial_Estadisticas.fxml"));

            Parent root = loader.load();

            ComercialEstadisticasController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_estadisticas.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {

        }
    }

    /**
     * Maneja la selección de un concesionario en el ComboBox.
     *
     * @param actionEvent El evento de acción.
     */
    @FXML
    public void seleccionar_concesionario(ActionEvent actionEvent) {
        concesionarioSeleccionado = CmB_concesionarios.getValue().toString();
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

            Stage myStage = (Stage) this.BTN_salir.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {

        }
    }

    /**
     * Realiza la venta de una motocicleta a un cliente.
     *
     * @param actionEvent El evento de acción.
     */
    @FXML
    public void realizar_venta(ActionEvent actionEvent) {
        L_error_motocicleta.setVisible(false);
        L_error_cliente.setVisible(false);

        Motocicleta motoAVender = T_tablaExistencias.getSelectionModel().getSelectedItem();
        if (motoAVender == null) {
            L_error_motocicleta.setVisible(true);

        } else {
            L_error_motocicleta.setVisible(false);
            L_error_cliente.setVisible(false);

            Cliente cliente = T_tabla_clientes.getSelectionModel().getSelectedItem();

            if (cliente == null) {
                L_error_cliente.setVisible(true);
            } else {
                L_error_motocicleta.setVisible(false);
                L_error_cliente.setVisible(false);

                Comercial comercial = ComercialLoggeado.getComercialLoggeado();

                // Fecha de compra
                long miliseconds = System.currentTimeMillis();
                Date fecha_compra = new Date(miliseconds);

                motoAVender.setPrecio_compra(cambiarPrecioMoto(motoAVender.getPrecio_compra()));
                Venta ventaRealizada = new Venta(fecha_compra, comercial, motoAVender, motoAVender.getPrecio_compra(), cliente);

                ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaRealizada);

                cargarMotocicletasGeneral();
                L_precio_total.setText("Vendida!");
            }
        }
    }

    /**
     * Limpia el filtro de búsqueda y muestra todas las motocicletas.
     *
     * @param actionEvent El evento de acción.
     */
    @FXML
    public void limpiarFiltro(ActionEvent actionEvent) {
        CmB_concesionarios.getItems().clear();
        llenarComboBoxConcesionarios(CmB_concesionarios);
        cargarMotocicletasGeneral();
        L_precio_total.setVisible(false);
        L_error_motocicleta.setVisible(false);
        L_error_cliente.setVisible(false);
    }

    /**
     * Calcula el precio total de la motocicleta seleccionada.
     */
    @FXML
    private void calcularPrecioTotal() {
        Motocicleta motocicleta = T_tablaExistencias.getSelectionModel().getSelectedItem();
        if (motocicleta != null) {
            L_precio_total.setVisible(true);
            L_precio_total.setText("Precio total: " + cambiarPrecioMoto(motocicleta.getPrecio_compra()));
            L_error_motocicleta.setVisible(false);
            L_error_cliente.setVisible(false);
        }
    }

    /**
     * Recupera los datos de respaldo de los clientes desde un archivo XML.
     *
     * @param actionEvent El evento de acción.
     */
    @FXML
    public void recuperarBackUp(ActionEvent actionEvent) {
        T_tabla_clientes.getItems().clear();
        // Utilizamos PauseTransition para no bloquear el hilo de la interfaz
        // de esta manera logramos un efecto de carga tras recuperar los datos del xml.
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        Scene scene = BTN_estadisticas.getScene();
        scene.setCursor(Cursor.WAIT);
        pause.setOnFinished(event -> {
            List<Cliente> clientes = clienteXMLReader.leerXML(rutaXML);
            if (clientes != null) {
                for (Cliente cliente : clientes) {
                    clientesList.add(clienteDAO.buscarCliente(miEntityManager.getEntityManager(), cliente.getId_cliente()));
                }
                scene.setCursor(Cursor.DEFAULT);
            }
            T_tabla_clientes.setItems(clientesList);
        });
        pause.play();


        cargarMotocicletasGeneral();
        CmB_concesionarios.getSelectionModel().clearSelection();
        T_tablaExistencias.refresh();

        // Opcion 1
        // Colisiona con el hilo de la interfaz y da InterruptedException
//         try {
//             wait(5000);
//         } catch (InterruptedException e) {
//             throw new RuntimeException(e);
//         }
//
//         for (Cliente cliente : clienteXMLReader.leerXML(rutaXML)) {
//             clientesList.add(cliente);
//         }
//
//         T_tabla_clientes.setItems(clientesList);
    }

    /**
     * Realiza un respaldo de los datos de los clientes en un archivo XML.
     *
     * @param actionEvent El evento de acción.
     */
    @FXML
    public void hacerBackUp(ActionEvent actionEvent) {
        clienteXMLWriter.escribirXML(clientesList, rutaXML);
        // Utilizamos de nuevo PauseTransition para dar un feedback al usuario
        // y que sepa que se ha realizado correctamente el back up.
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        L_backup_ok.setVisible(true);
        pause.setOnFinished(event -> {
            L_backup_ok.setVisible(false);
        });
        pause.play();
    }

    /**
     * Llena el ComboBox de concesionarios con los disponibles en la base de datos.
     *
     * @param comboBox El ComboBox a llenar.
     */
    private void llenarComboBoxConcesionarios(ComboBox<Concesionario> comboBox) {
        List<Concesionario> concesionarios = concDAO.listarConcesionarios(miEntityManager.getEntityManager());
        comboBox.getItems().addAll(concesionarios);
    }

    /**
     * Carga los datos iniciales en las tablas de motocicletas y clientes.
     */
    private void cargarDatos() {
        llenarComboBoxConcesionarios(CmB_concesionarios);

        CmB_concesionarios.setOnAction(event -> cargarMotocicletasSegunConcesionarioSeleccionado());

        // Tabla existencias
        colUbicacion.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("concesionario"));
        colMarca.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("color"));
        colCilindrada.setCellValueFactory(new PropertyValueFactory<Motocicleta, Integer>("cc"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Motocicleta, Double>("precio_compra"));

        cargarMotocicletasGeneral();

        // Tabla clientes
        TC_cliente_nombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        TC_cliente_correo.setCellValueFactory(new PropertyValueFactory<Cliente, String>("correo"));
        TC_cliente_telefono.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("telefono"));
        TC_cliente_direccion.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));

        cargarClientes();
    }

    /**
     * Carga las motocicletas según el concesionario seleccionado en el ComboBox.
     */
    private void cargarMotocicletasSegunConcesionarioSeleccionado() {
        Concesionario concesionarioSeleccionado = (Concesionario) CmB_concesionarios.getValue();
        List<Motocicleta> motocicletas = null;

        if (concesionarioSeleccionado != null) {
            motocicletas = motoDAO.listarMotosConcesionario(concesionarioSeleccionado.getId_concesionario(),
                    miEntityManager.getEntityManager());
        }
        T_tablaExistencias.getItems().clear();

        if (motocicletas != null) {
            for (Motocicleta moto : motocicletas) {
                motocicletasList.add(moto);
            }
        }
        T_tablaExistencias.setItems(motocicletasList);
    }

    /**
     * Carga todas las motocicletas disponibles en la tabla general.
     */
    private void cargarMotocicletasGeneral() {
        List<Motocicleta> motocicletas = motoDAO.listarMotos(miEntityManager.getEntityManager());

        T_tablaExistencias.getItems().clear();

        if(motocicletas != null ) {
            for (Motocicleta moto : motocicletas) {
                motocicletasList.add(moto);
            }
        }

        T_tablaExistencias.setItems(motocicletasList);
    }

    /**
     * Calcula el nuevo precio de una motocicleta aplicando el beneficio de la empresa.
     *
     * @param precio El precio original de la motocicleta.
     * @return El nuevo precio con el beneficio aplicado.
     */
    private double cambiarPrecioMoto(double precio) {
        precio = precio * LOAD.beneficio;
        if (precio % 1 != 0) {
            precio = Double.parseDouble(String.valueOf((int) precio));
        }
        return precio;
    }

    /**
     * Carga la lista de clientes desde la base de datos y la muestra en la tabla correspondiente.
     */
    private void cargarClientes() {
        List<Cliente> clientes = clienteDAO.listarClientes(miEntityManager.getEntityManager());

        T_tabla_clientes.getItems().clear();

        for (Cliente cliente : clientes) {
            clientesList.add(cliente);
        }

        T_tabla_clientes.setItems(clientesList);
    }

}