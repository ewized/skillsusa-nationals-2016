package grade;

import common.AbstractController;
import common.Common;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** The controller that is for the view of a class */
public class ClassView extends AbstractController {
    private static final Map<Parent, ClassView> VIEWS = new HashMap<>();

    /** Create the instance of class view */
    public static ClassView loadFX(ClassModel model) {
        ClassView classView = loadFX(ClassView.class, "/grade/class.fxml");
        VIEWS.put(classView.parent(), classView);
        classView.model = model;
        classView.name.setText(model.name());
        return classView;
    }

    private ClassModel model; //  The view of the class that is linked to the model

    @FXML private Text name; // the xml element for the name of the class
    @FXML private TextField value; // the xml element for the user entered data

    /** When the user clicks on the class */
    @FXML private void onMore(Event event) {
        String title = String.format("%s Grades - Grade #2 506", model().name());
        try {
            double average = Math.ceil(model().averageScore());
            String message = String.format("Average:\t%.2f\nLowest:\t%.2f\nHighest:\t%.2f", average, model().lowScore(), model().highScore());
            Common.alert(Alert.AlertType.INFORMATION, title, message).showAndWait();
        } catch (Exception error) {
            Common.alert(Alert.AlertType.INFORMATION, title, "The class currently has no grades");
            error.printStackTrace();
        }
    }

    /** Get the grade from the text field and throw an error when its not a number */
    public double getGrade() {
        return Common.parseDouble(value.getText()).orElseThrow(Common.error("Grade is not a number."));
    }

    /** Get the model this view represents */
    public ClassModel model() {
        return model;
    }

    /** Reset the grade back to 0 */
    public void clearGrade() {
        value.setText("0");
    }

    /** Reset the grades back to zero */
    public void reset() {
        model().reset();
    }

    /** Get the view from the parent */
    public static Optional<ClassView> fromParent(Object parent) {
        return Optional.ofNullable(VIEWS.get(parent));
    }
}
