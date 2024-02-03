package motosave.motosavefx.controlador;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import motosave.DATA.LOAD;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.Modelos.Comercial;
import motosave.motosavefx.MotosaveMain;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Comercial comercial;
    ImpComercialDAO comDAO;


    @FXML
    private Button boton_acceder1;
    @FXML
    private Button boton_acceder;
    @FXML
    private Label L_control_correcto;
    @FXML
    private Label L_control_vacios;
    @FXML
    private PasswordField PF_contrasena;
    @FXML
    private TextField TF_usuario;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comercial = new Comercial();
        comDAO = new ImpComercialDAO();
    }

    @FXML
    public void loggear(ActionEvent actionEvent) {
        L_control_vacios.setVisible(false);
        L_control_correcto.setVisible(false);

        if (PF_contrasena.getText().isEmpty() || TF_usuario.getText().isEmpty()) {
            L_control_vacios.setVisible(true);
            L_control_correcto.setVisible(false);
        } else {
            if (comDAO.loggearComercial(LOAD.getEntityManagerCargado(), PF_contrasena.getText(), TF_usuario.getText())) {
                Platform.exit();
            } else {
                L_control_vacios.setVisible(false);
                L_control_correcto.setVisible(true);
            }
        }
    }

    @FXML
    public void botonLogin(ActionEvent actionEvent) {
    }
}