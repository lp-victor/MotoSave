package motosave.motosavefx.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import motosave.DATA.ComercialLoggeado;
import motosave.DATA.LOAD;
import motosave.ImplementacionesDAO.ImpClienteDAO;
import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.ImplementacionesDAO.ImpMotocicletaDAO;
import motosave.ImplementacionesDAO.ImpVentaDAO;
import motosave.Modelos.*;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ComercialVentasController implements Initializable {

    ImpConcesionarioDAO concDAO;
    ImpMotocicletaDAO motoDAO;
    ImpVentaDAO ventaDAO;
    ImpClienteDAO clienteDAO;
    String concesionarioSeleccionado = "";
    ObservableList<Motocicleta> motocicletasList;
    ObservableList<Cliente> clientesList;
    private Comercial comercial;

    @FXML
    private Pane P_comercialVentas;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_estadisticas;
    @FXML
    private Button BTN_limpiar;
    @FXML
    private Button BTN_vender;
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
    private Label L_error_motocicleta;
    @FXML
    private Label L_error_cliente;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private Label L_sede_comercial;

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
        L_sede_comercial.setText(String.valueOf(comercial.getConcesionario()));

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
        L_error_motocicleta.setVisible(false);

        Motocicleta motoAVender = T_tablaExistencias.getSelectionModel().getSelectedItem();
        if (motoAVender == null) {
            L_error_motocicleta.setVisible(true);
        } else {
            L_error_motocicleta.setVisible(false);
            L_error_motocicleta.setVisible(false);

            Cliente cliente = T_tabla_clientes.getSelectionModel().getSelectedItem();

            if (cliente == null){
                L_error_cliente.setVisible(true);
            }  else {
                L_error_motocicleta.setVisible(false);
                L_error_motocicleta.setVisible(false);

                Comercial comercial = ComercialLoggeado.getComercialLoggeado();

                // Fecha de compra
                long miliseconds = System.currentTimeMillis();
                Date fecha_compra = new Date(miliseconds);
                // Cambiamos el precio de la moto al precio por le que se realiza la venta.
                motoAVender.setPrecio_compra(cambiarPrecioMoto(motoAVender.getPrecio_compra()));
                motoDAO.actualizarMoto(motoAVender, miEntityManager.getEntityManager());

                Venta ventaRealizada = new Venta(fecha_compra, comercial, motoAVender, motoAVender.getPrecio_compra(), cliente);

                ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaRealizada);
            }
        }
    }

    @FXML
    public void limpiarFiltro(ActionEvent actionEvent) {
        CmB_concesionarios.getItems().clear();
        llenarComboBoxConcesionarios(CmB_concesionarios);
        cargarMotocicletasGeneral();
    }

    private void llenarComboBoxConcesionarios(ComboBox<Concesionario> comboBox) {
        List<Concesionario> concesionarios = concDAO.listarConcesionarios(miEntityManager.getEntityManager());
        comboBox.getItems().addAll(concesionarios);
    }

    private void cargarDatos (){
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

        if ( motocicletas != null) {
            for (Motocicleta moto : motocicletas) {
                // Cambiamos el precio de compra al precio de venta
                double precioVenta = cambiarPrecioMoto(moto.getPrecio_compra());
                moto.setPrecio_compra(precioVenta);
                motocicletasList.add(moto);
            }
        }
        T_tablaExistencias.setItems(motocicletasList);
    }

    private void cargarMotocicletasGeneral() {
        List<Motocicleta> motocicletas = motoDAO.listarMotos(miEntityManager.getEntityManager());

        T_tablaExistencias.getItems().clear();

        for (Motocicleta moto : motocicletas) {
            // Cambiamos el precio de compra al precio de venta
            double precioVenta = cambiarPrecioMoto(moto.getPrecio_compra());
            moto.setPrecio_compra(precioVenta);
            motocicletasList.add(moto);
        }

        T_tablaExistencias.setItems(motocicletasList);
    }

    private double cambiarPrecioMoto (double precio) {
        precio = precio * LOAD.beneficio;
        if(precio%1 != 0){
            precio= Double.parseDouble(String.valueOf((int) precio));
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