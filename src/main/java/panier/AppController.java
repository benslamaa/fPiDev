package panier;

import panier.cart.CartView;
import panier.home.HomeView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AppController {

    @FXML
    private BorderPane contentPane;

    public void closeApp() {
        App.getWindow().close();
    }

    public void showHomeView() throws IOException{

            contentPane.setCenter(new HomeView().getView());
    }
    public void showCartView() throws IOException{
            contentPane.setCenter(new CartView().getView());
    }
}
