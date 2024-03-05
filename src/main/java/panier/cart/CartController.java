package panier.cart;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.List;

 public class CartController {

     @FXML
     private VBox cartPane;

     private Label totalPriceLabel;

     @FXML
     public void initialize() throws FileNotFoundException {
         List<CartEntry> entries = ShoppingCart.getInstance().getEntries();
         cartPane.getChildren().clear();

         if (entries.isEmpty()) {
             Label emptyLabel = new Label("Empty Cart");
             cartPane.getChildren().add(emptyLabel);
         } else {
             Label shoppingCartTitle = new Label("Shopping cart");
             cartPane.getChildren().add(shoppingCartTitle);

             for (CartEntry cartEntry:entries) {

                 HBox productView = cartEntryView(cartEntry);
                 cartPane.getChildren().add(productView);
             }

             Separator separator=new Separator();
             separator.setOrientation(Orientation.HORIZONTAL);
             cartPane.getChildren().add(separator);

             HBox totalView = totalView(ShoppingCart.getInstance().calculTotal());
             cartPane.getChildren().add(totalView);
         }
     }

   private HBox totalView(Float totalPrice){
         HBox layout = new HBox();
         layout.setAlignment(Pos.CENTER);

         Label totalLabel = new Label("Total : ");
         totalLabel.setStyle("-fx-font-size:15pt;");

         this.totalPriceLabel = new Label(String.valueOf(totalPrice));
         layout.getChildren().addAll(totalLabel,this.totalPriceLabel);
         return layout;
   }

     private HBox cartEntryView(CartEntry cartEntry) throws FileNotFoundException {
         HBox layout = new HBox();
         layout.setAlignment(Pos.CENTER_LEFT);


         InputStream input = getClass().getResourceAsStream("/images/" + cartEntry.getProduct().getImageFile());

         Image image = new Image(input);
         ImageView imageView = new ImageView(image);
         imageView.setFitWidth(50);
         imageView.setFitHeight(50);

         Label productName = new Label(cartEntry.getProduct().name());
         productName.setPrefWidth(100);
         productName.setStyle("-fx-font-size:15pt;-fx-padding:5px ");

         Label quantity = new Label(String.valueOf(cartEntry.getQuantity()));
         quantity.setStyle("-fx-padding:5px");

         Button plusButton = new Button("+");
         plusButton.setStyle("-fx-padding:5px");
         plusButton.setUserData(cartEntry.getProduct().name());
         plusButton.setOnAction(event -> {
             String name = (String) ((Node) event.getSource()).getUserData();
             ShoppingCart.getInstance().addProduct(name);
             quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(name)));
             this.totalPriceLabel.setText(String.valueOf(ShoppingCart.getInstance().calculTotal()));
         });

         Button minusButton = new Button("-");
         minusButton.setStyle("-fx-padding:5px");
         minusButton.setUserData(cartEntry.getProduct().name());
         minusButton.setOnAction(event -> {
                     String name = (String) ((Node) event.getSource()).getUserData();
                     ShoppingCart.getInstance().removeProduct(name);
                     quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(name)));
                     this.totalPriceLabel.setText(String.valueOf(ShoppingCart.getInstance().calculTotal()));
                 });
         Label price = new Label(String.valueOf(" DT"+cartEntry.getProduct().getPrice()));
         price.setStyle("-fx-padding:5px");

         layout.getChildren().addAll(imageView, productName, plusButton, quantity, minusButton, price);
         return layout;

     }
 }
