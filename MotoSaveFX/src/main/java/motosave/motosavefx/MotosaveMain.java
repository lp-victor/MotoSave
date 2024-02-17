package motosave.motosavefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import motosave.DATA.LOAD;

import java.io.IOException;

public class MotosaveMain extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        //LOAD load = new LOAD();

        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/Login.fxml"));
            Parent root = loader.load();

            // Configurar la escena
            Scene scene = new Scene(root, 1440, 919);

            // Configurar el escenario principal
            stage.setTitle("Login Motosave");
            stage.setScene(scene);
            stage.setResizable(false);
            // Mostrar el escenario
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}