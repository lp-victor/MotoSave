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

    @FXML
    public void seleccionar_concesionario(ActionEvent actionEvent) {
        concesionarioSeleccionado = CmB_concesionarios.getValue().toString();
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

        }
    }

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

    @FXML
    public void limpiarFiltro(ActionEvent actionEvent) {
        CmB_concesionarios.getItems().clear();
        llenarComboBoxConcesionarios(CmB_concesionarios);
        cargarMotocicletasGeneral();
        L_precio_total.setVisible(false);
        L_error_motocicleta.setVisible(false);
        L_error_cliente.setVisible(false);
    }

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
                    clientesList.add(cliente);
                }
                scene.setCursor(Cursor.DEFAULT);
            }
            T_tabla_clientes.setItems(clientesList);
        });
        pause.play();

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

    private void llenarComboBoxConcesionarios(ComboBox<Concesionario> comboBox) {
        List<Concesionario> concesionarios = concDAO.listarConcesionarios(miEntityManager.getEntityManager());
        comboBox.getItems().addAll(concesionarios);
    }

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

    private void cargarMotocicletasGeneral() {
        List<Motocicleta> motocicletas = motoDAO.listarMotos(miEntityManager.getEntityManager());

        T_tablaExistencias.getItems().clear();

        for (Motocicleta moto : motocicletas) {
            motocicletasList.add(moto);
        }

        T_tablaExistencias.setItems(motocicletasList);
    }

    private double cambiarPrecioMoto(double precio) {
        precio = precio * LOAD.beneficio;
        if (precio % 1 != 0) {
            precio = Double.parseDouble(String.valueOf((int) precio));
        }
        return precio;
    }

    private void cargarClientes() {
        List<Cliente> clientes = clienteDAO.listarClientes(miEntityManager.getEntityManager());

        T_tabla_clientes.getItems().clear();

        for (Cliente cliente : clientes) {
            clientesList.add(cliente);
        }

        T_tabla_clientes.setItems(clientesList);
    }

}