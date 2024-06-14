package exportation.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WindowsManager {

    public static void showMainSheet() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("view/main.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Main");
        stage.show();
    }

    public static void showStuffForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("view/stuff.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }

    public static void showAboutForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(Objects.requireNonNull(WindowsManager.class.getResource("about.fxml")))
        );

        stage.setScene(scene);
        stage.setTitle("About");
        stage.show();
    }
}
