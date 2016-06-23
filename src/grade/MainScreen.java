package grade;

import common.AbstractController;
import common.Common;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** The main scene that handles the view of the classes and the action of the main buttons */
public class MainScreen extends AbstractController {
    public static final List<ClassModel> CLASS_MODELS = Arrays.asList(
            new ClassModel("Programming"),
            new ClassModel("Art"),
            new ClassModel("Science"),
            new ClassModel("Math"),
            new ClassModel("History")
    );

    /** Load the view into the controller */
    public static MainScreen loadFX() {
        MainScreen view = loadFX(MainScreen.class, "/grade/main_screen.fxml");
        view.classes.getItems().addAll(
                CLASS_MODELS.stream()
                    .map(clazz -> ClassView.loadFX(clazz).parent())
                    .collect(Collectors.toList())
        );
        return view;
    }

    @FXML private ListView<Parent> classes; // The view of the list of classes

    /** Calculate the the price and running total */
    @FXML private void onCaculate(Event event) {
        Common.alert(Alert.AlertType.INFORMATION, "Status Update - Grade #2 - 506", "Grades has been updated, you may click view more to view them.");
        try {
            classes.getItems().forEach(parent -> ClassView.fromParent(parent).ifPresent(view -> {
                view.model().addScore(view.getGrade());
                view.clearGrade();
            }));
        } catch (Exception error) {
            Common.exception(error);
        }
    }

    /** Clear the quantity of all the items */
    @FXML private void onClear(Event event) {
        classes.getItems().forEach(parent -> ClassView.fromParent(parent).ifPresent(ClassView::clearGrade));
    }

    /** Clear the quantity of all the items */
    @FXML private void onReset(Event event) {
        onClear(event);
        classes.getItems().forEach(parent -> ClassView.fromParent(parent).ifPresent(ClassView::reset));
    }

    /** End the program does exactly what the x does */
    @FXML
    private void onExit(Event event) {
        System.exit(0);
    }
}
