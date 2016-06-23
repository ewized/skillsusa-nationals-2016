package common;

import javafx.scene.control.Alert;

import java.util.Optional;
import java.util.function.Supplier;

/** A common utility class */
public final class Common {
    private Common() {} // Util class

    /** Create a simple supplier with the message */
    public static Supplier<RuntimeException> error(String message) {
        return () -> new RuntimeException(message);
    }

    /** Convert empty strings to nulls for validation */
    public static String nullString(String value) {
        return value != null && !value.isEmpty() ? value : null;
    }

    /** Create an alert box */
    public static Alert alert(Alert.AlertType type, String title, String header) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        return alert;
    }

    /** Convert an exception into an alert box */
    public static void exception(Throwable throwable) {
        while (throwable.getCause() != null) {
            throwable = throwable.getCause(); // get the root of the cause
        }
        alert(Alert.AlertType.ERROR, throwable.getClass().getSimpleName(), throwable.getMessage()).showAndWait();
    }

    /** Convert the string into an optional integer to get the value if it contains it */
    public static Optional<Integer> parseInt(String value) {
        try {
            if (value == null || value.isEmpty()) {
                return Optional.empty(); // It its better to check and return than filling a stack trace
            }
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException | NullPointerException error) {
            return Optional.empty();
        }
    }

    /** Convert the string into an optional double to get the value if it contains it */
    public static Optional<Double> parseDouble(String value) {
        try {
            if (value == null || value.isEmpty()) {
                return Optional.empty(); // It its better to check and return than filling a stack trace
            }
            return Optional.of(Double.parseDouble(value));
        } catch (NumberFormatException | NullPointerException error) {
            return Optional.empty();
        }
    }
 }
