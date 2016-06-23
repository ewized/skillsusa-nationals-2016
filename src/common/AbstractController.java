package common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

/** An abstract controller to allow simple creation of controllers to link then with their view */
public abstract class AbstractController {
    public static final int HEIGHT = 320;
    public static final int WIDTH = 480;
    protected Parent parent;

    /** This will load the controller with the view of the fxml file */
    public static <T extends AbstractController> T loadFX(Class<T> clazz, String location) {
        try {
            Objects.requireNonNull(clazz, "clazz");
            Objects.requireNonNull(Common.nullString(location), "location");
            FXMLLoader loader = new FXMLLoader(clazz.getResource(location));
            Parent instance = loader.load();
            T derived = loader.getController();
            derived.parent = instance;
            return derived;
        } catch (IOException | NullPointerException error) {
            Common.exception(error);
            throw new RuntimeException(error);
        }
    }

    /** Get the parent of the controller */
    public Parent parent() {
        return parent;
    }

    /** Construct the scene with the default constant values */
    public Scene scene() {
        return scene(HEIGHT, WIDTH);
    }

    /** Construct the scene with the height and width specified */
    public Scene scene(int height, int width) {
        return new Scene(parent, width, height);
    }
}
