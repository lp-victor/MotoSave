package motosave.motosavefx.controlador;


import jakarta.persistence.EntityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import motosave.ImplementacionesDAO.ImpAdministradorDAO;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.Modelos.Comercial;
import motosave.Persistencia.miEntityManager;
import org.w3c.dom.Entity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Comercial comercial;
    ImpComercialDAO comDAO;
    ImpAdministradorDAO adminDAO;

    @FXML
    private Label L_control_correcto;
    @FXML
    private Label L_control_vacios;
    @FXML
    private PasswordField PF_contrasena;
    @FXML
    private TextField TF_usuario;
    @FXML
    private Button BTN_acceder;
    @FXML
    private AnchorPane P_login;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comercial = new Comercial();
        comDAO = new ImpComercialDAO();
        adminDAO = new ImpAdministradorDAO();
    }

    @FXML
    public void loggear(ActionEvent actionEvent) {
        L_control_vacios.setVisible(false);
        L_control_correcto.setVisible(false);

        EntityManager em = miEntityManager.getEntityManager();

        if (PF_contrasena.getText().isEmpty() || TF_usuario.getText().isEmpty()) {
            L_control_vacios.setVisible(true);
            L_control_correcto.setVisible(false);
        } else {
            if (comDAO.loggearComercial(em, PF_contrasena.getText(), TF_usuario.getText())) {
                iniciarComercial();
            } else if (adminDAO.loggearAdmin(em, PF_contrasena.getText(), TF_usuario.getText())) {
                iniciarAdmin();
            } else {
                L_control_vacios.setVisible(false);
                L_control_correcto.setVisible(true);
            }
        }
    }

    private void iniciarComercial(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Comercial_Ventas.fxml"));

            Parent root = loader.load();

            ComercialVentasController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_acceder.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {

        }
    }

    private void iniciarAdmin(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Admin_Comerciales.fxml"));

            Parent root = loader.load();

            AdminComercialesController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_acceder.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {

        }
    }

    @Deprecated
    public void botonLogin(ActionEvent actionEvent) {
    }
}