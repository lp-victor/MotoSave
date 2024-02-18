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
    FactoryMoto factoriaMoto;
    ObservableList<Motocicleta> motocicletasList;

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
    private ComboBox<Concesionario> CmB_concesionarios_selecion;
    @FXML
    private TableView<Motocicleta> T_tablaExistencias;
    @FXML
    private TextField TF_cantidad;
    @FXML
    private Button BTN_comprar;
    @FXML
    private ComboBox<Concesionario> CmB_concesionarios_anadir;
    @FXML
    private ComboBox<Marcas> CmB_marca;
    @FXML
    private TextField TF_precio_total;
    @FXML
    private TextField TF_precio_unidad;
    @FXML
    private ComboBox<String> CmB_modelo;
    @FXML
    private Label L_indentificacion_comercial;
    @FXML
    private TextField TF_cilindrada;
    @FXML
    private ComboBox<Colores> CmB_color;
    @FXML
    private Pane P_comercialVentas;
    @FXML
    private TableColumn<Motocicleta, String> colMarca;
    @FXML
    private TableColumn<Motocicleta, String> colModelo;
    @FXML
    private TableColumn<Motocicleta, String> colColor;
    @FXML
    private TableColumn<Motocicleta, Double> colPrecio;
    @FXML
    private TableColumn<Motocicleta, Integer> colCilindrada;
    @FXML
    private Button BTN_comprar1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        concDAO = new ImpConcesionarioDAO();
        motoDAO = new ImpMotocicletaDAO();
        factoriaMoto = new FactoryMoto();
        motocicletasList = FXCollections.observableArrayList();

        colMarca.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<Motocicleta, String>("color"));
        colCilindrada.setCellValueFactory(new PropertyValueFactory<Motocicleta, Integer>("cc"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Motocicleta, Double>("precio_compra"));


        cargarConcesionariosEnComboBox();
        CmB_concesionarios_selecion.setOnAction(event -> cargarMotocicletasSegunConcesionarioSeleccionado());

        // Combobox inferiores
        llenarComboBoxMarcas();
        llenarComboBoxColores();
        llenarComboBoxConcesionarios(CmB_concesionarios_anadir);
        /*Esta es la expresión lambda que define el comportamiento que se ejecutará cuando cambie la selección
        en el ComboBox. Toma tres parámetros:
        observable: La propiedad observada (en este caso, la propiedad selectedItemProperty() del ComboBox).
        oldValue: El valor anterior seleccionado en el ComboBox.
        newValue: El nuevo valor seleccionado en el ComboBox.*/
        CmB_marca.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            llenarComboBoxModelo(newValue);
        });
        aplicarValidacionCantidad();

        cargarSeleccionTabla();

        mostrarPrecioFinalAutomatico();

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
        Motocicleta motoModificada = T_tablaExistencias.getSelectionModel().getSelectedItem();
        motoModificada.setColor(String.valueOf(CmB_color.getValue()));
        motoModificada.setConcesionario((Concesionario) CmB_concesionarios_anadir.getValue());
        motoDAO.actualizarMoto(motoModificada, miEntityManager.getEntityManager());

        //cargarMotocicletasSegunConcesionarioSeleccionado();
        resetStock();

    }

    @FXML
    public void comprarModeloMoto(ActionEvent actionEvent) {

        TF_cilindrada.setDisable(true);
        TF_precio_unidad.setDisable(true);
        TF_precio_total.setDisable(true);

        Concesionario seleccionado = CmB_concesionarios_anadir.getValue();
        Marcas marca = CmB_marca.getValue();
        String modelo = CmB_modelo.getValue();
        Colores color = CmB_color.getValue();
        int cantidad = Integer.parseInt(TF_cantidad.getText());

        ArrayList<Motocicleta> motos = factoriaMoto.fabricarMotos(marca, modelo, color, cantidad);
        for (Motocicleta moto : motos) {
            moto.setConcesionario(seleccionado);
            motoDAO.guardarMoto(moto, miEntityManager.getEntityManager());
            TF_cilindrada.setText(String.valueOf(moto.getCc()));
            TF_precio_unidad.setText(String.valueOf(moto.getPrecio_compra()));
        }

        TF_precio_total.setText(String.valueOf(Double.parseDouble(TF_precio_unidad.getText()) * cantidad));
        resetStock();
    }

    /**
     * Elimina la moto con el DAO de moto y cargando de la tabla la moto selecionada.
     * Restablecemos los datos.
     * @param actionEvent
     */
    @FXML
    public void eliminarModeloMoto(ActionEvent actionEvent) {
        Motocicleta motoEliminar = T_tablaExistencias.getSelectionModel().getSelectedItem();
        motoDAO.eliminarMoto(motoEliminar, miEntityManager.getEntityManager());
        resetStock();
    }

    /**
     * Boton para Restablecer los datos en blanco.
     * @param actionEvent
     */
    @FXML
    public void restablecerDatosMotoSeleccion(ActionEvent actionEvent) {
        resetStock();
    }

    /**
     * Metodo para calcular el precio final adquirido de los textField de precio y cantidad.
     */
    private void mostrarPrecioFinalAutomatico(){
        TF_cantidad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                int cantidad = Integer.parseInt(newValue);
                double precioUnidad;
                try {
                    precioUnidad = Double.parseDouble(TF_precio_unidad.getText());
                } catch (NumberFormatException e) {
                    precioUnidad = 0;
                }
                double precioTotal = precioUnidad * cantidad;
                if(precioTotal%1==0){
                    TF_precio_total.setText(String.valueOf((int)precioTotal)+" €");
                }else{
                    TF_precio_total.setText(String.format("%.2f", precioTotal));
                }

            }else{
                TF_precio_total.setText("");
            }

        });
    }

    /**
     * Restablecer los valores de los comboBox y TExtFields.
     */
    private void resetStock() {
        BTN_eliminar.setVisible(false);
        BTN_modificar.setVisible(false);
        BTN_comprar.setVisible(true);

        CmB_marca.setDisable(false);
        CmB_modelo.setDisable(false);
        TF_cilindrada.setDisable(false);
        TF_precio_unidad.setDisable(false);
        TF_cantidad.setDisable(false);
        TF_precio_total.setDisable(false);

        TF_cilindrada.setText("");
        TF_precio_unidad.setText("");
        TF_cantidad.setText("");
        TF_precio_total.setText("");

        CmB_marca.getItems().clear();
        CmB_modelo.getItems().clear();
        CmB_concesionarios_anadir.getItems().clear();
        CmB_color.getItems().clear();

        llenarComboBoxColores();
        llenarComboBoxMarcas();
        llenarComboBoxConcesionarios(CmB_concesionarios_anadir);
        CmB_marca.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            llenarComboBoxModelo((Marcas) newValue);
        });
        cargarMotocicletasSegunConcesionarioSeleccionado();
    }

    /**
     * Metodo de carga de Datos de la seleccion de la tabla a los ComboBox y TextField.
     */
    private void cargarSeleccionTabla() {
        T_tablaExistencias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                BTN_eliminar.setVisible(true);
                BTN_modificar.setVisible(true);
                BTN_comprar.setVisible(false);

                CmB_marca.setDisable(true);
                CmB_modelo.setDisable(true);
                TF_cilindrada.setDisable(true);
                TF_precio_unidad.setDisable(true);
                TF_cantidad.setDisable(true);
                TF_precio_total.setDisable(true);

                Concesionario seleccionado = (Concesionario) CmB_concesionarios_selecion.getValue();
                CmB_marca.setValue(Marcas.BMW.str2Marcas(newValue.getMarca()));
                CmB_modelo.setValue(newValue.getModelo());
                CmB_concesionarios_anadir.setValue(newValue.getConcesionario());
                CmB_color.setValue(Colores.GRIS.str2Color(newValue.getColor()));
                TF_cilindrada.setText(String.valueOf(newValue.getCc()));
                TF_precio_unidad.setText(String.valueOf((int)newValue.getPrecio_compra()));
                TF_cantidad.setText(String.valueOf(contarMotosIguales(
                        newValue,
                        motoDAO.listarMotosConcesionario(
                                seleccionado.getId_concesionario(),
                                miEntityManager.getEntityManager()
                        ))));
                TF_precio_total.setText(String.valueOf(newValue.getPrecio_compra() * Double.parseDouble(TF_cantidad.getText())));


            }
        });

    }

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
        Concesionario concesionarioSeleccionado = (Concesionario) CmB_concesionarios_selecion.getValue();
        List<Motocicleta> motocicletas = motoDAO.listarMotosConcesionario(
                concesionarioSeleccionado.getId_concesionario(), miEntityManager.getEntityManager()
        );

        T_tablaExistencias.getItems().clear();
        ObservableList<Motocicleta> motocicletasCantList = FXCollections.observableArrayList();

        for (Motocicleta moto : motocicletas) {
            motocicletasList.add(moto);
        }

        T_tablaExistencias.setItems(motocicletasList);
    }

    /**
     * Cuenta las motos mientras carga las motos en la tabla.
     * @param moto
     * @param listaMotos
     * @return Cantidad de motos, de la moto seleccionada o agregada.
     */
    private int contarMotosIguales(Motocicleta moto, List<Motocicleta> listaMotos) {
        int contador = 0;

        for (Motocicleta motocicleta : listaMotos) {
            if (sonMotocicletasIguales(moto, motocicleta)) {
                contador++;
            }
        }

        return contador;
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

    /**
     * Rellena el comboBox de MArcas.
     */
    private void llenarComboBoxMarcas() {
        ObservableList<Marcas> marcasList = FXCollections.observableArrayList(Marcas.values());
        CmB_marca.setItems(marcasList);
    }

    /**
     * Rellena el comboBox de Colores.
     */
    private void llenarComboBoxColores() {
        ObservableList<Colores> coloresList = FXCollections.observableArrayList(Colores.values());
        CmB_color.setItems(coloresList);
    }

    /**
     * Llena el ComboBox con los nombres de los concesionarios disponibles.
     *
     * @param comboBox El ComboBox donde se van a mostrar los nombres de los concesionarios.
     */
    private void llenarComboBoxConcesionarios(ComboBox<Concesionario> comboBox) {
        List<Concesionario> nombresConcesionarios = concDAO.listarConcesionarios(miEntityManager.getEntityManager());
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
    private void llenarComboBoxModelo(Marcas marcaSeleccionada) {
        if (marcaSeleccionada != null) {
            ObservableList<String> modelosList = FXCollections.observableArrayList();
            switch (marcaSeleccionada) {
                case BMW:
                    for (ModelosBMW modelo : ModelosBMW.values()) {
                        modelosList.add(modelo.name());
                    }
                    break;
                case DAISVI:
                    for (ModelosDAISVI modelo : ModelosDAISVI.values()) {
                        modelosList.add(modelo.name());
                    }
                    break;
                case DUCATI:
                    for (ModelosDUCATI modelo : ModelosDUCATI.values()) {
                        modelosList.add(modelo.name());
                    }
                    break;
                case HONDA:
                    for (ModelosHONDA modelo : ModelosHONDA.values()) {
                        modelosList.add(modelo.name());
                    }
                    break;
                case KAWASAKI:
                    for (ModelosKAWASAKI modelo : ModelosKAWASAKI.values()) {
                        modelosList.add(modelo.name());
                    }
                    break;
                case KTM:
                    for (ModelosKTM modelo : ModelosKTM.values()) {
                        modelosList.add(modelo.name());
                    }
                    break;
                case SUZUKI:
                    for (ModelosSUZUKI modelo : ModelosSUZUKI.values()) {
                        modelosList.add(modelo.name());
                    }
                    break;
                case YAMAHA:
                    for (ModelosYAMAHA modelo : ModelosYAMAHA.values()) {
                        modelosList.add(modelo.name());
                    }
                    break;

            }
            CmB_modelo.setItems(modelosList);
        } else {
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
    }

    /**
     * Rellena los campos de cilindrada y precio por unidad basándose en la marca y el modelo seleccionados.
     */
    private void rellenarCamposCilindradaYPrecio() {
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