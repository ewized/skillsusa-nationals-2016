package food;

import common.AbstractController;
import common.Common;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

/** This is teh view that will be displayed in the list */
public class FoodView extends AbstractController {
    /** Contains the map of parent and the food objects */
    private static Map<Parent, FoodView> FOOD = new HashMap<>();

    /** Create the instance of the view from the food modle */
    public static FoodView loadFX(Food food) {
        FoodView view = loadFX(FoodView.class, "/food/food.fxml");
        FOOD.put(view.parent(), view);
        view.name.setText(food.name());
        view.price.setText(food.displayPrice());
        view.food = food;
        return view;
    }

    /** The model of the view */
    private Food food;

    @FXML private TextField amount;
    @FXML private Text name;
    @FXML private Text price;

    /** Get the quantity of the food item */
    public int getQuantity() {
        return Common.parseInt(amount.getText()).orElseThrow(Common.error("Quantity is not a number."));
    }

    /** Clear the quantity back to 0 */
    public void clearQuantity() {
        amount.setText("0");
    }

    /** Get the food module this view represents */
    public Food food() {
        return food;
    }

    /** Get the food object of the map */
    public static FoodView fromParent(Parent parent) {
        return FOOD.get(parent);
    }
}
