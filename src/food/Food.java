package food;

import common.Common;

import java.util.Objects;

/** The object that contains a food object this class is immutable, once created it can not be changed */
public class Food {
    private String name;
    private String displayPrice;
    private double price;

    public Food(String name, String displayPrice, double price) {
        this.name = Objects.requireNonNull(Common.nullString(name), "name");
        this.displayPrice = Objects.requireNonNull(Common.nullString(displayPrice), "displayPrice");
        this.price = price <= 0 ? 0 : price;
    }

    /** Get the name of the food item */
    public String name() {
        return name;
    }

    /** Get the display price of the item */
    public String displayPrice() {
        return displayPrice;
    }

    /** Get the price of the food item */
    public double price() {
        return price;
    }
}
