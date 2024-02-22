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
import javafx.stage.Stage;
import motosave.ImplementacionesDAO.ImpAdministradorDAO;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.Modelos.Comercial;
import motosave.Persistencia.miEntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * @author MotoSave
 */
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

    /**
     * Inicializa el controlador.
     *
     * @param url            La URL de inicialización.
     * @param resourceBundle El ResourceBundle utilizado para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comercial = new Comercial();
        comDAO = new ImpComercialDAO();
        adminDAO = new ImpAdministradorDAO();

        TF_usuario.setText("Admin");
        PF_contrasena.setText("admin");
    }

    /**
     * Maneja el evento de inicio de sesión.
     *
     * @param actionEvent El evento de acción.
     */
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

    /**
     * Inicia la sesión de un comercial y abre la ventana correspondiente.
     */
    private void iniciarComercial() {
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
            ex.printStackTrace();
            System.out.println("Error!");
        }
    }

    /**
     * Inicia la sesión de un administrador y abre la ventana correspondiente.
     */
    private void iniciarAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Admin_Dashboard.fxml"));

            Parent root = loader.load();

            AdminDashboardController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

            Stage myStage = (Stage) this.BTN_acceder.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error!");
        }
    }
}