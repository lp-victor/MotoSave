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
import motosave.DATA.MotocicletaCantidad;
import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.ImplementacionesDAO.ImpMotocicletaDAO;
import motosave.Modelos.Cliente;
import motosave.Modelos.Comercial;
import motosave.Modelos.Concesionario;
import motosave.Modelos.Motocicleta;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ComercialVentasController implements Initializable {

    ImpConcesionarioDAO concDAO;
    ImpMotocicletaDAO motoDAO;
    String concesionarioSeleccionado = "";

    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_estadisticas;
    @FXML
    private TableView<MotocicletaCantidad> T_tablaExistencias;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private Label L_sede_comercial;
    @FXML
    private Pane P_comercialVentas;
    @FXML
    private ComboBox CmB_concesionarios;
    @FXML
    private TextField TF_nombreCliente;
    @FXML
    private TextField TF_direccion;
    @FXML
    private TextField TF_apellidosCliente;
    @FXML
    private Label L_control_telefono;
    @FXML
    private Button BTN_vender;
    @FXML
    private TextField TF_telefonoCliente;
    @FXML
    private TextField TF_correoCliente;
    @FXML
    private Label L_control_vacios;
    @FXML
    private TableColumn<MotocicletaCantidad, String> colMarca;
    @FXML
    private TableColumn<MotocicletaCantidad, Integer> colUnds;
    @FXML
    private TableColumn<MotocicletaCantidad, String> colModelo;
    @FXML
    private TableColumn<MotocicletaCantidad, String> colColor;
    @FXML
    private TableColumn<MotocicletaCantidad, Integer> colCilindrada;
    @FXML
    private TableColumn<MotocicletaCantidad, Double> colPrecio;
    @FXML
    private TableColumn<MotocicletaCantidad, String> colUbicacion;
    @FXML
    private Button BTN_limpiar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        concDAO = new ImpConcesionarioDAO();
        motoDAO = new ImpMotocicletaDAO();

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

        if (comprobarDatosCliente()) {
            Cliente cliente = new Cliente(TF_nombreCliente.getText(), TF_apellidosCliente.getText(), TF_correoCliente.getText(), Integer.getInteger(TF_telefonoCliente.getText()), TF_direccion.getText());
            Comercial comercial = ComercialLoggeado.getComercialLoggeado();

            // Fecha de compra
            long miliseconds = System.currentTimeMillis();
            Date fecha_compra = new Date(miliseconds);
            // Moto seleccionada
            // double precioFinal = moto.getPrecio_compra() *
        }

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

        colUbicacion.setCellValueFactory(new PropertyValueFactory<MotocicletaCantidad, String>("concesionario"));
        colMarca.setCellValueFactory(new PropertyValueFactory<MotocicletaCantidad, String>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<MotocicletaCantidad, String>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<MotocicletaCantidad, String>("color"));
        colCilindrada.setCellValueFactory(new PropertyValueFactory<MotocicletaCantidad, Integer>("cc"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<MotocicletaCantidad, Double>("precio_final"));
        colUnds.setCellValueFactory(new PropertyValueFactory<MotocicletaCantidad, Integer>("cantidad"));

        cargarMotocicletasGeneral();
    }

    private void cargarMotocicletasSegunConcesionarioSeleccionado() {
        Concesionario concesionarioSeleccionado = (Concesionario) CmB_concesionarios.getValue();
        List<Motocicleta> motocicletas = motoDAO.listarMotosConcesionario(concesionarioSeleccionado.getId_concesionario(), miEntityManager.getEntityManager());

        // Inicializar el mapa para realizar el seguimiento de las motocicletas y sus cantidades
        Map<Motocicleta, Integer> motocicletasConCantidad = new HashMap<>();

        // Contar motocicletas iguales
        for (Motocicleta motocicleta : motocicletas) {
            boolean encontrada = false;
            for (Motocicleta key : motocicletasConCantidad.keySet()) {
                if (sonMotocicletasIguales(motocicleta, key)) {
                    motocicletasConCantidad.put(key, motocicletasConCantidad.get(key) + 1);
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                motocicletasConCantidad.put(motocicleta, 1);
            }
        }

        T_tablaExistencias.getItems().clear();
        ObservableList<MotocicletaCantidad> motocicletasCantList = FXCollections.observableArrayList();

        // Convertir el mapa a una lista observable para la tabla
        for (Map.Entry<Motocicleta, Integer> entry : motocicletasConCantidad.entrySet()) {
            MotocicletaCantidad motocicletaCantidad = new MotocicletaCantidad(entry.getKey(), entry.getValue()-1);
            motocicletasCantList.add(motocicletaCantidad);
        }

        T_tablaExistencias.setItems(motocicletasCantList);
        T_tablaExistencias.refresh();
    }

    private void cargarMotocicletasGeneral() {
        List<Motocicleta> motocicletas = motoDAO.listarMotos(miEntityManager.getEntityManager());

        // Inicializar el mapa para realizar el seguimiento de las motocicletas y sus cantidades
        Map<Motocicleta, Integer> motocicletasConCantidad = new HashMap<>();

        // Contar motocicletas iguales
        for (Motocicleta motocicleta : motocicletas) {
            boolean encontrada = false;
            for (Motocicleta key : motocicletasConCantidad.keySet()) {
                if (sonMotocicletasIguales(motocicleta, key)) {
                    motocicletasConCantidad.put(key, motocicletasConCantidad.get(key) + 1);
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                motocicletasConCantidad.put(motocicleta, 1);
            }
        }

        T_tablaExistencias.getItems().clear();
        ObservableList<MotocicletaCantidad> motocicletasCantList = FXCollections.observableArrayList();

        // Convertir el mapa a una lista observable para la tabla
        for (Map.Entry<Motocicleta, Integer> entry : motocicletasConCantidad.entrySet()) {
            MotocicletaCantidad motocicletaCantidad = new MotocicletaCantidad(entry.getKey(), entry.getValue()-1);
            motocicletasCantList.add(motocicletaCantidad);
        }

        T_tablaExistencias.setItems(motocicletasCantList);
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

}