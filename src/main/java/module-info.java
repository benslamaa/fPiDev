module panier {
    requires javafx.controls;
    requires javafx.fxml;

    exports panier; // export the panier package
    exports panier.home; // export the panier.home package

    opens panier to javafx.fxml;
    opens panier.home to javafx.fxml;
}