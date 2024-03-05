package panier;

import panier.cart.CartView;
import panier.home.HomeView;
import panier.Menu.MenuView;  // Import the MenuView class
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class AppController {

    @FXML
    private BorderPane contentPane;

    public void closeApp() {
        App.getWindow().close();
    }

    public void showHomeView() throws IOException {
        contentPane.setCenter(new HomeView().getView());
    }

    public void showCartView() throws IOException {
        contentPane.setCenter(new CartView().getView());
    }


    @FXML
    private void showMenuView() {
        try {
            contentPane.setCenter(new MenuView().getView());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
