package grade;

import common.AbstractController;
import common.Common;
import javafx.application.Application;
import javafx.stage.Stage;

/** The main entrance to the program sets up the window */
public class Main extends Application {
    private Stage window; // The main window, this will allow for simple upgrading later

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            window = primaryStage;
            window.setMinWidth(AbstractController.WIDTH);
            window.setMinHeight(AbstractController.HEIGHT);
            window.setMaxHeight(AbstractController.HEIGHT * 1.5);
            window.setTitle("Grade #2 - 506");
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
