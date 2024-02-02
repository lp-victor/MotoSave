package motosave.motosavefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import motosave.motosavefx.controlador.LoginController;

import java.io.IOException;

public class MotosaveMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MotosaveMain.class.getResource("/motosave/motosavefx/vista/login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1440, 920);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();

        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motosave/motosavefx/vista/login.fxml"));
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

    public static void main(String[] args) {
        launch();
    }
}