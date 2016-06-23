package food;

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

/** This is the main screen where the user can enter in the quantities */
public class MainScreen extends AbstractController {
    /** The constants of all the food that the truck and order from*/
    private static final List<Food> foodList = Arrays.asList(
            new Food("Hot Dog", "$2.50", 2.5),
            new Food("Brat", "$3.50", 3.5),
            new Food("Hamburger", "$5.00", 5),
            new Food("Fries", "$2.00", 2),
            new Food("Soda", "$2.00", 2),
            new Food("Water", "Free", 0)
    );

    /** Create the instance of the main screen */
    public static MainScreen loadFX() {
        MainScreen view = loadFX(MainScreen.class, "/food/main_screen.fxml");
        view.foods.getItems().addAll(
                foodList.stream()
                .map(food -> FoodView.loadFX(food).parent())
                .collect(Collectors.toList())
        );
        return view;
    }

    /** The running total of each transaction */
    private double runningTotal;

    /** The foods and quantity that is on the screen */
    @FXML private ListView<Parent> foods;

    /** Calculate the the price and running total */
    @FXML private void onCaculate(Event event) {
        try {
            double total = foods.getItems().stream().mapToDouble(view -> {
                FoodView foodView = FoodView.fromParent(view);
                return foodView.getQuantity() * foodView.food().price();
            }).sum();
            runningTotal += total;
            String message = String.format("Current Total: $%.2f \nRunning Total: $%.2f ", total, runningTotal);
            Common.alert(Alert.AlertType.INFORMATION, "Totals - Food #1 - 506", message).showAndWait();
        } catch (Exception error) {
            Common.exception(error);
        }
    }

    /** Clear the quantity of all the items */
    @FXML private void onClear(Event event) {
        foods.getItems().forEach(parent -> {
            FoodView.fromParent(parent).clearQuantity();
        });
    }

    /** End the program does exactly what the x does */
    @FXML private void onExit(Event event) {
        System.exit(0);
    }
}
