package panier.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class menuController implements Initializable {

    @FXML
    private TableColumn<ProductItem, String> NameColmn;

    @FXML
    private TableColumn<ProductItem, Integer> QteColmn;

    @FXML
    private TableColumn<ProductItem, Double> PriceColmn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TableView<ProductItem> tableView;

    private ObservableList<ProductItem> productList;

    private Connection con;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic goes here
    }

    @FXML
    void Add(ActionEvent event) {
        // Add product logic goes here
    }

    @FXML
    void Delete(ActionEvent event) {
        // Delete product logic goes here
    }

    @FXML
    void Update(ActionEvent event) {
        // Update product logic goes here
    }

    private void connect() {
        // Database connection logic goes here
    }

    private void loadData() {
        // Load data from the database logic goes here
    }

    private void insertProductIntoDatabase(String name, int quantity, double price) {
        // Insert product into the database logic goes here
    }

    private void deleteProductFromDatabase(String name) {
        // Delete product from the database logic goes here
    }

    private void updateProductInDatabase(String name, int quantity, double price) {
        // Update product in the database logic goes here
    }
}
