package motosave.motosavefx.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import motosave.ImplementacionesDAO.ImpComercialDAO;
import motosave.Modelos.Comercial;
import motosave.Modelos.Concesionario;
import motosave.Persistencia.miEntityManager;


import java.io.IOException;
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
    @FXML
    private Button BTN_salir;
    @FXML
    private TableView TV_comerciales;
    @FXML
    private Button BTN_dashboard;
    @FXML
    private Button BTN_stock;


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

    @Deprecated
    public void logOut(ActionEvent actionEvent) {
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
    public void cerrar_sesion(ActionEvent actionEvent) {
    }

    @FXML
    public void eliminar_comercial(ActionEvent actionEvent) {
    }
}