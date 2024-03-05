module panier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports panier; // export the panier package
    exports panier.home; // export the panier.home package
    exports panier.cart;
    exports panier.Menu;

    opens panier to javafx.fxml;
    opens panier.home to javafx.fxml;
    opens panier.cart to javafx.fxml;
    opens panier.Menu to javafx.fxml;
}