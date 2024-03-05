package panier.cart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;

import java.net.URL;

public class CartView {

    private Parent view;

    public CartView() throws IOException {
        URL uiFile = new File("C:/Users/admin/IdeaProjects/panier/src/main/java/panier/cart/cart.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(uiFile);
        this.view = root;
    }

    public Parent getView() {
        return view;
    }
}

