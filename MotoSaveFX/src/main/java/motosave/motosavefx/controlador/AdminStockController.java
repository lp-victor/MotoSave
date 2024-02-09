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
import motosave.DATA.MotocicletaCantidad;
import motosave.EnumeradosMoto.*;
import motosave.Factory.FactoryMoto;
import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.ImplementacionesDAO.ImpMotocicletaDAO;
import motosave.Modelos.Concesionario;
import motosave.Modelos.Motocicleta;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.UnaryOperator;

public class AdminStockController implements Initializable {


    ImpConcesionarioDAO concDAO;
    ImpMotocicletaDAO motoDAO;
    MotocicletaCantidad motocicletaCantidad;
    FactoryMoto factoriaMoto;
    ObservableList<MotocicletaCantidad> motocicletasConCantidadList;

    @FXML
    private Button BTN_comerciales;
    @FXML
    private Button BTN_eliminar;
    @FXML
    private Button BTN_modificar;
    @FXML
    private Button BTN_salir;
    @FXML
    private Button BTN_dashboard;
    @FXML
    private ComboBox CmB_concesionarios_selecion;
    @FXML
    private TableView T_tablaExistencias;
    @FXML
    private TextField TF_cantidad;
    @FXML
    private Button BTN_comprar;
    @FXML
    private ComboBox CmB_concesionarios_anadir;
    @FXML
    private ComboBox CmB_marca;
    @FXML
    private TextField TF_precio_total;
    @FXML
    private TextField TF_precio_unidad;
    @FXML
    private ComboBox CmB_modelo;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private TextField TF_cilindrada;
    @FXML
    private ComboBox CmB_color;
    @FXML
    private Pane P_comercialVentas;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colModelo;
    @FXML
    private TableColumn colColor;
    @FXML
    private TableColumn colUnidades;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private TableColumn colCilindrada;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        concDAO = new ImpConcesionarioDAO();
        motoDAO = new ImpMotocicletaDAO();
        factoriaMoto = new FactoryMoto();
        motocicletasConCantidadList = FXCollections.observableArrayList();

        // Tabla y combobox superior
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colCilindrada.setCellValueFactory(new PropertyValueFactory<>("cc"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio_compra"));
        colUnidades.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        cargarConcesionariosEnComboBox();
        CmB_concesionarios_selecion.setOnAction(event -> cargarMotocicletasSegunConcesionarioSeleccionado());

        // Combobox inferiores
        llenarComboBoxMarcas();
        llenarComboBoxColores();
        llenarComboBoxConcesionarios(CmB_concesionarios_anadir);
        /*Esta es la expresión lambda que define el comportamiento que se ejecutará cuando cambie la selección en el ComboBox. Toma tres parámetros:
        observable: La propiedad observada (en este caso, la propiedad selectedItemProperty() del ComboBox).
        oldValue: El valor anterior seleccionado en el ComboBox.
        newValue: El nuevo valor seleccionado en el ComboBox.*/
        CmB_marca.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            llenarComboBoxModelo((Marcas) newValue);
        });
        aplicarValidacionCantidad();

    }

    @FXML
    public void abrirDashboard(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Admin_Dashboard.fxml"));

            Parent root = loader.load();

            AdminDashboardController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_dashboard.getScene().getWindow();
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

    @FXML
    public void cargarCilindradaPrecio(ActionEvent actionEvent) {
        rellenarCamposCilindradaYPrecio();
    }

    @FXML
    public void modificarModeloMoto(ActionEvent actionEvent) {

    }

    @FXML
    public void comprarModeloMoto(ActionEvent actionEvent) {

    }

    @FXML
    public void eliminarModeloMoto(ActionEvent actionEvent) {

    }

    // Tabla y comobox superior

    /**
     * Método para cargar concesionarios en el ComboBox.
     */
    private void cargarConcesionariosEnComboBox() {
        List<Concesionario> concesionarios = concDAO.listarConcesionarios(miEntityManager.getEntityManager());
        CmB_concesionarios_selecion.getItems().addAll(concesionarios);
    }

    /**
     * Método para cargar motocicletas según el concesionario seleccionado
     */
    private void cargarMotocicletasSegunConcesionarioSeleccionado() {

        Concesionario concesionarioSeleccionado = (Concesionario) CmB_concesionarios_selecion.getValue();    //=============Cambiar nombre del combobox
        System.out.println(concesionarioSeleccionado.getId_concesionario());
        List<Motocicleta> motocicletas = motoDAO.listarMotosConcesionario(concesionarioSeleccionado.getId_concesionario(), miEntityManager.getEntityManager()); //===========DAO CONCESIONARIO POR ID O POR LO QUE ESTE en EL COMBOBOX

        // Usamos un Map para realizar un seguimiento de las motocicletas y sus cantidades
        Map<Motocicleta, Integer> motocicletasConCantidad = new HashMap<>();

        for (Motocicleta motocicleta : motocicletas) {
            int cantidad = calcularCantidad(motocicleta, motocicletas);
            motocicletasConCantidad.put(motocicleta, cantidad);
        }

        T_tablaExistencias.getItems().clear();

        List<MotocicletaCantidad> motocicletasCantList = new ArrayList<>();
        for (Map.Entry<Motocicleta, Integer> entry : motocicletasConCantidad.entrySet()) {
            Motocicleta motocicleta = entry.getKey();
            int cantidad = entry.getValue();
            MotocicletaCantidad motocicletaCantidad = new MotocicletaCantidad(motocicleta, cantidad);
            motocicletasCantList.add(motocicletaCantidad);
        }

        motocicletasConCantidadList.addAll(motocicletasCantList);
        T_tablaExistencias.setItems(motocicletasConCantidadList);
    }

    /**
     * Calculo la cantidad con el metodo de son motocicletasIguales, si es True aumenta el cantidad para averiguarla.
     *
     * @param motocicleta
     * @param motocicletas
     * @return Int Cantidad de Motos Iguales
     */
    private int calcularCantidad(Motocicleta motocicleta, List<Motocicleta> motocicletas) {
        int cantidad = 0;
        for (Motocicleta otraMotocicleta : motocicletas) {
            if (motocicleta.equals(otraMotocicleta)) {
                continue;
            }
            if (sonMotocicletasIguales(motocicleta, otraMotocicleta)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    /**
     * Comprobar Motocicletas iguales. (Introduciendo el arraylist de las motos de un concesionarioy cada moto individual)
     *
     * @param motocicleta1
     * @param motocicleta2
     * @return Boolean si son iguales excluyendo el id.
     */
    private boolean sonMotocicletasIguales(Motocicleta motocicleta1, Motocicleta motocicleta2) {
        // Implementa la lógica de comparación para tus atributos relevantes (excluyendo el ID)
        // Retorna true si son iguales, false en caso contrario
        return motocicleta1.getMarca().equals(motocicleta2.getMarca()) &&
                motocicleta1.getModelo().equals(motocicleta2.getModelo()) &&
                motocicleta1.getColor().equals(motocicleta2.getColor()) &&
                motocicleta1.getCc() == motocicleta2.getCc() &&
                motocicleta1.getPrecio_compra() == motocicleta2.getPrecio_compra();
    }

    // Combobox y textfields inferiores.
    public void llenarComboBoxMarcas() {
        ObservableList<Marcas> marcasList = FXCollections.observableArrayList(Marcas.values());
        CmB_marca.setItems(marcasList);
    }

    public void llenarComboBoxColores() {
        ObservableList<Colores> coloresList = FXCollections.observableArrayList(Colores.values());
        CmB_color.setItems(coloresList);
    }

    /**
     * Llena el ComboBox con los nombres de los concesionarios disponibles.
     *
     * @param comboBox El ComboBox donde se van a mostrar los nombres de los concesionarios.
     */
    public void llenarComboBoxConcesionarios(ComboBox<String> comboBox) {
        List<String> nombresConcesionarios = concDAO.listarNombreConcesionarios(miEntityManager.getEntityManager());
        if (nombresConcesionarios != null) {
            comboBox.getItems().clear();
            comboBox.getItems().addAll(nombresConcesionarios);
        }
    }

    /**
     * Llena el ComboBox de modelos basado en la marca seleccionada.
     *
     * @param marcaSeleccionada La marca seleccionada.
     */
    public void llenarComboBoxModelo(Marcas marcaSeleccionada) {
        if (marcaSeleccionada != null) {
            switch (marcaSeleccionada) {
                case BMW:
                    ObservableList<String> modelosBMWList = FXCollections.observableArrayList(Arrays.toString(ModelosBMW.values()));
                    CmB_modelo.setItems(modelosBMWList);
                    break;
                case DAISVI:
                    ObservableList<ModelosDAISVI> modelosDaisviList = FXCollections.observableArrayList(Arrays.asList(ModelosDAISVI.values()));
                    CmB_modelo.setItems(modelosDaisviList);
                    break;
                case DUCATI:
                    ObservableList<ModelosDUCATI> modelosDucatiList = FXCollections.observableArrayList(Arrays.asList(ModelosDUCATI.values()));
                    CmB_modelo.setItems(modelosDucatiList);
                    break;
                case HONDA:
                    ObservableList<ModelosHONDA> modelosHondaList = FXCollections.observableArrayList(Arrays.asList(ModelosHONDA.values()));
                    CmB_modelo.setItems(modelosHondaList);
                    break;
                case KAWASAKI:
                    ObservableList<ModelosKAWASAKI> modelosKawasakiList = FXCollections.observableArrayList(Arrays.asList(ModelosKAWASAKI.values()));
                    CmB_modelo.setItems(modelosKawasakiList);
                    break;
                case KTM:
                    ObservableList<ModelosKTM> modelosKtmList = FXCollections.observableArrayList(Arrays.asList(ModelosKTM.values()));
                    CmB_modelo.setItems(modelosKtmList);
                    break;
                case SUZUKI:
                    ObservableList<ModelosSUZUKI> modelosSuzukiList = FXCollections.observableArrayList(Arrays.asList(ModelosSUZUKI.values()));
                    CmB_modelo.setItems(modelosSuzukiList);
                    break;
                case YAMAHA:
                    ObservableList<ModelosYAMAHA> modelosYamahaList = FXCollections.observableArrayList(Arrays.asList(ModelosYAMAHA.values()));
                    CmB_modelo.setItems(modelosYamahaList);
                    break;
                default:
                    // Si la marca seleccionada no tiene modelos definidos, se limpia el ComboBox de modelos
                    CmB_modelo.getItems().clear();
                    break;
            }
        } else {
            // Si no se ha seleccionado ninguna marca, se limpia el ComboBox de modelos
            CmB_modelo.getItems().clear();
        }
    }

    /**
     * Aplica la validación de cantidad al TextField, permitiendo solo dígitos y mostrando un borde rojo en caso de error.
     */
    private void aplicarValidacionCantidad() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        // Crear un TextFormatter con el filtro
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        // Establecer el TextFormatter en el TextField
        TF_cantidad.setTextFormatter(textFormatter);
        // Establecer el estilo del borde cuando se introduce un valor no válido
        TF_cantidad.textProperty().addListener((observable, oldValue, newValue) -> {
            comprobarCantidad(newValue);
        });
    }

    /**
     * Comprueba la cantidad introducida y establece el estilo del borde del TextField en rojo si no es válida.
     *
     * @param newValue La nueva cantidad introducida en el TextField.
     */
    private void comprobarCantidad(String newValue) {
        if (!newValue.matches("\\d*")) {
            TF_cantidad.setStyle("-fx-border-color: red;");
        } else {
            TF_cantidad.setStyle("-fx-border-color: inherit;");
        }
    }

    /**
     * Rellena los campos de cilindrada y precio por unidad basándose en la marca y el modelo seleccionados.
     */
    public void rellenarCamposCilindradaYPrecio() {
        Marcas marcaSeleccionada = (Marcas) CmB_marca.getValue();
        String modeloSeleccionado = String.valueOf(CmB_modelo.getValue());
        Colores colorSelecionado = (Colores) CmB_color.getValue();

        if (marcaSeleccionada != null && modeloSeleccionado != null) {
            Motocicleta motocicleta = obtenerMotocicletaPorMarcaYModelo(marcaSeleccionada, modeloSeleccionado, colorSelecionado);
            if (motocicleta != null) {
                TF_cilindrada.setText(String.valueOf(motocicleta.getCc()));
                TF_precio_unidad.setText(String.valueOf(motocicleta.getPrecio_compra()));
            } else {
                TF_cilindrada.clear();
                TF_precio_unidad.clear();
            }
        } else {
            TF_cilindrada.clear();
            TF_precio_unidad.clear();
        }
    }

    /**
     * Obtiene la motocicleta correspondiente a la marca y el modelo seleccionados.
     *
     * @param marca  La marca seleccionada.
     * @param modelo El modelo seleccionado.
     * @return La motocicleta correspondiente o null si no se encuentra.
     */
    private Motocicleta obtenerMotocicletaPorMarcaYModelo(Marcas marca, String modelo, Colores color) {
        return factoriaMoto.fabricarMotos(marca, modelo, color, 1).getFirst();
    }


}