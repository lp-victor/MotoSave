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
    private Label L_indentificacion_comercial;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Label L_control_telefono;
    @FXML
    private Label L_control_vacios;
    @FXML
    private ComboBox CmB_concesionarios;
    @FXML
    private TextField TF_nombreCliente;
    @FXML
    private TextField TF_direccion;
    @FXML
    private TextField TF_apellidosCliente;
    @FXML
    private TextField TF_telefonoCliente;
    @FXML
    private TextField TF_correoCliente;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        concDAO = new ImpConcesionarioDAO();
        motoDAO = new ImpMotocicletaDAO();
        ventaDAO = new ImpVentaDAO();
        clienteDAO = new ImpClienteDAO();
        motocicletasList = FXCollections.observableArrayList();

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
        Cliente cliente = clienteDAO.buscarCliente(miEntityManager.getEntityManager(), 1);

        Motocicleta motoAVender = T_tablaExistencias.getSelectionModel().getSelectedItem();

        //if (comprobarDatosCliente()) {

            if (motoAVender != null) {
                Comercial comercial = ComercialLoggeado.getComercialLoggeado();

                // Fecha de compra
                long miliseconds = System.currentTimeMillis();
                Date fecha_compra = new Date(miliseconds);

                Venta ventaRealizada = new Venta(fecha_compra, comercial, motoAVender, motoAVender.getPrecio_compra(), cliente);

                ventaDAO.realizarVenta(miEntityManager.getEntityManager(), ventaRealizada);
            } else {

            }
        //} else {

        //}

    }

    @FXML
    public void limpiarFiltro(ActionEvent actionEvent) {
        cargarMotocicletasGeneral();
        CmB_concesionarios.getItems().clear();
        llenarComboBoxConcesionarios(CmB_concesionarios);
    }

    private void llenarComboBoxConcesionarios(ComboBox<Concesionario> comboBox) {
        List<Concesionario> concesionarios = concDAO.listarConcesionarios(miEntityManager.getEntityManager());
        comboBox.getItems().addAll(concesionarios);
    }

    private boolean comprobarDatosCliente() {
        L_control_vacios.setVisible(false);
        L_control_telefono.setVisible(false);

        if (TF_nombreCliente.getText().isEmpty() || TF_apellidosCliente.getText().isEmpty() || TF_correoCliente.getText().isEmpty() || TF_telefonoCliente.getText().isEmpty() || TF_direccion.getText().isEmpty()) {
            L_control_vacios.setVisible(true);
            return false;
        } else if (!TF_telefonoCliente.getText().matches("^\\d+$")) {
            L_control_telefono.setVisible(true);
            return false;
        } else {
            L_control_vacios.setVisible(false);
            L_control_telefono.setVisible(false);
            return true;
        }
    }

    private void cargarDatos (){
        llenarComboBoxConcesionarios(CmB_concesionarios);

        CmB_concesionarios.setOnAction(event -> cargarMotocicletasSegunConcesionarioSeleccionado());

        colUbicacion.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("concesionario"));
        colMarca.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("color"));
        colCilindrada.setCellValueFactory(new PropertyValueFactory<Motocicleta, Integer>("cc"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Motocicleta, Double>("precio_compra"));

        cargarMotocicletasGeneral();
    }

    private void cargarMotocicletasSegunConcesionarioSeleccionado() {
        Concesionario concesionarioSeleccionado = (Concesionario) CmB_concesionarios.getValue();
        List<Motocicleta> motocicletas = motoDAO.listarMotosConcesionario(
                concesionarioSeleccionado.getId_concesionario(), miEntityManager.getEntityManager()
        );

        T_tablaExistencias.getItems().clear();

        for (Motocicleta moto : motocicletas) {
            // Cambiamos el precio de compra al precio de venta
            double precioVenta = cambiarPrecioMoto(moto.getPrecio_compra());
            moto.setPrecio_compra(precioVenta);
            motocicletasList.add(moto);
        }

        T_tablaExistencias.setItems(motocicletasList);
    }

    private void cargarMotocicletasGeneral() {
        Concesionario concesionarioSeleccionado = (Concesionario) CmB_concesionarios.getValue();
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

    private boolean sonMotocicletasIguales(Motocicleta motocicleta1, Motocicleta motocicleta2) {
        // Implementa la lógica de comparación para tus atributos relevantes (excluyendo el ID)
        // Retorna true si son iguales, false en caso contrario
        return motocicleta1.getMarca().equals(motocicleta2.getMarca()) &&
                motocicleta1.getModelo().equals(motocicleta2.getModelo()) &&
                motocicleta1.getColor().equals(motocicleta2.getColor()) &&
                motocicleta1.getCc() == motocicleta2.getCc() &&
                motocicleta1.getPrecio_compra() == motocicleta2.getPrecio_compra();
    }

    private double cambiarPrecioMoto (double precio) {
        precio = precio * LOAD.beneficio;
        if(precio%1 != 0){
            precio= Double.parseDouble(String.valueOf((int) precio));
        }
        return precio;
    }

    private void comprobarCliente (Cliente cliente) {
        // Falta
    }
}