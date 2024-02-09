package motosave.motosavefx.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import motosave.ImplementacionesDAO.ImpConcesionarioDAO;
import motosave.ImplementacionesDAO.ImpMotocicletaDAO;
import motosave.Modelos.Concesionario;
import motosave.Modelos.Motocicleta;
import motosave.Persistencia.miEntityManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminStockController implements Initializable {

    ImpConcesionarioDAO concDAO;
    ImpMotocicletaDAO motoDAO;
    List<Motocicleta> motocicletasList = null;

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
    private TableColumn<Motocicleta, String> colMarca;
    @FXML
    private TableColumn<Motocicleta, String> colModelo;
    @FXML
    private TableColumn<Motocicleta, String> colColor;
    @FXML
    private TableColumn<Motocicleta, Long> colCilindrada;
    @FXML
    private TableColumn<Motocicleta, String> colPrecio;
    @FXML
    private TableColumn<Motocicleta, String> colUnidades;
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
    private Label L_sede_comercial;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        concDAO = new ImpConcesionarioDAO();
        motoDAO = new ImpMotocicletaDAO();

        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colCilindrada.setCellValueFactory(new PropertyValueFactory<>("cc"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));

        cargarConcesionariosEnComboBox();

        CmB_concesionarios_selecion.setOnAction(event -> cargarMotocicletasSegunConcesionarioSeleccionado());
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


    /**
     * Método para cargar concesionarios en el ComboBox.
     */
    private void cargarConcesionariosEnComboBox() {
        List<Concesionario> concesionarios = concDAO.listarConcesionarios(miEntityManager.getEntityManager());//======== dao concesioanrio. obtener concesionarios
        CmB_concesionarios_selecion.getItems().addItems(concesionarios);
    }

    /**
     * Método para cargar motocicletas según el concesionario seleccionado
     */
    private void cargarMotocicletasSegunConcesionarioSeleccionado() {

        Concesionario concesionarioSeleccionado = (Concesionario) CmB_concesionarios_selecion.getValue();    //=============CAmbiar nombre del combobox
        List<Motocicleta> motocicletas = motoDAO(concesionarioSeleccionado); //===========DAO CONCESIONARIO POR ID O POR LO QUE ESTE en EL COMBOBOX

        // Limpiar la tabla antes de agregar nuevos datos
        T_tablaExistencias.getItems().clear();

        for (Motocicleta motocicleta : motocicletas) {
            int cantidad = calcularCantidad(motocicleta,motocicletas);
            motocicleta.setUnidades(cantidad);
        }

        motocicletasList.setAll(motocicletas);
        T_tablaExistencias.setItems(motocicletasList);
    }

    /**
     * Calculo la cantidad con el metodo de son motocicletasIguales, si es True aumenta el cantidad para averiguarla.
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
}