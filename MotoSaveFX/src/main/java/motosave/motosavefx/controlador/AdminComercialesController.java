package motosave.motosavefx.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.Modelos.Comercial;
import motosave.Modelos.Concesionario;
import motosave.Persistencia.miEntityManager;


import java.net.URL;
import java.util.ResourceBundle;

public class AdminComercialesController implements Initializable {

    private Comercial comercial;
    private ImpComercialDAO comDAO;


    @FXML
    private ComboBox CmB_concesionario;
    @FXML
    private TextField TF_NIF;
    @FXML
    private TextField TF_nombre;
    @FXML
    private TextField TF_usuario;
    @FXML
    private TextField TF_apellidos;
    @FXML
    private PasswordField PF_pass1;
    @FXML
    private PasswordField PF_pass2;
    @FXML
    private Button BTN_agregar;
    @FXML
    private Button BTN_eliminar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comDAO = new ImpComercialDAO();
    }

    @FXML
    public void agregarComercial(ActionEvent actionEvent) {
        // Faltan todos los controles
        Concesionario c1 = new Concesionario(1,"Madrid");
        comercial = new Comercial(c1, TF_usuario.getText(), PF_pass1.getText(), TF_NIF.getText(), TF_nombre.getText(), TF_apellidos.getText());

        comDAO.anadirComercial(miEntityManager.getEntityManager(), comercial);
    }
}