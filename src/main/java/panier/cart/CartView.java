package panier.cart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class CartView {

    private Parent view;

    public CartView() {
        URL uiFile = getClass().getResource("/cart.fxml");
        if (uiFile != null) {
            try {
                view = FXMLLoader.load(uiFile);
            } catch (IOException e) {
                System.err.println("Error loading FXML file: " + e.getMessage());
                logException(e);
            }
        } else {
            System.err.println("FXML file not found: /panier/cart/cart.fxml");
        }
    }

    public Parent getView() {
        return view;
    }

    private void logException(IOException e) {
        // Log the exception stack trace
        System.err.println("Exception details:");
        e.printStackTrace();
    }
}
