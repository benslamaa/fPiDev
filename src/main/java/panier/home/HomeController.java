package panier.home;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import panier.cart.ShoppingCart;

import java.io.InputStream;

public class HomeController {

    @FXML
    private GridPane productGridPane;

    @FXML
    public void initialize() {
        productGridPane.getChildren().clear();

        try {
            VBox productView1 = productView(Product.APPLE);
            productGridPane.add(productView1, 0, 0);

            VBox productView2 = productView(Product.MILK);
            productGridPane.add(productView2, 1, 0);

            VBox productView3 = productView(Product.JUICE);
            productGridPane.add(productView3, 2, 0);

            VBox productView4 = productView(Product.LETTUCE);
            productGridPane.add(productView4, 0, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private VBox productView(Product product) {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        try {
            InputStream input = getClass().getResourceAsStream("/images/" + product.getImageFile());

            if (input != null) {
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                Label productName = new Label(product.name());
                Label price = new Label(product.getPrice() + " DT");

                Button addButton = new Button("add to cart");
                addButton.setUserData(product.name());
                addButton.setOnAction(event -> handleAddToCart(event));

                layout.getChildren().addAll(imageView, productName, price, addButton);
            } else {
                System.err.println("Error loading image for " + product.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return layout;
    }

    private void handleAddToCart(ActionEvent event) {
        Node sourceComponent = (Node) event.getSource();
        String productName = (String) sourceComponent.getUserData();
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        shoppingCart.addProduct(productName);
    }
}



