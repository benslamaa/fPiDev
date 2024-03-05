package panier.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.*;

public class MenuView {

    private ObservableList<ProductItem> productList;
    private Connection con;
    private PreparedStatement pst;

    public MenuView() {
        connect();
        productList = FXCollections.observableArrayList();
        loadData();
    }

    private void connect() {
        // Database connection logic goes here
    }

    private void loadData() {
        // Load data from the database logic goes here
    }

    public ObservableList<ProductItem> getProductList() {
        return productList;
    }

    public void insertProductIntoDatabase(String name, int quantity, double price) {
        // Insert product into the database logic goes here
    }

    public void deleteProductFromDatabase(String name) {
        // Delete product from the database logic goes here
    }

    public void updateProductInDatabase(String name, int quantity, double price) {
        // Update product in the database logic goes here
    }

    public Parent getView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
        return loader.load();
    }
}
