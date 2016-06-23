package food;

import common.AbstractController;
import common.Common;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {
    private static Main inst;
    private Stage window;

    public static Main instance() {
        return Optional.ofNullable(inst).orElseThrow(Common.error("Instance has not been loaded"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            window = primaryStage;
            window.setMinWidth(AbstractController.WIDTH);
            window.setMinHeight(AbstractController.HEIGHT);
            window.setTitle("Food #1 - 506");
            window.setScene(MainScreen.loadFX().scene());
            window.show();
        } catch (Exception error) {
            Common.exception(error);
            error.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
