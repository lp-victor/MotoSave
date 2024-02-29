package motosave.motosavefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * @author MotoSave
 */
public class MotosaveMain extends Application {

    /**
     * Método para iniciar la aplicación.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
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
            String path = "/images/fotor-ai-2024020418187-removebg-preview.png";
            Image icon = new Image(getClass().getResourceAsStream(path));
            stage.getIcons().add(icon);
            stage.setTitle("MotoSave");
            // Mostrar el escenario
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}