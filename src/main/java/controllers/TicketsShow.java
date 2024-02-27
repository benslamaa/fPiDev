package controllers;

import entities.evenement;
import entities.tickets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import utils.data_source;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class TicketsShow {
    @FXML
    private ListView<tickets> listV;
    private Connection conn;
    private java.awt.Label IdSupp;
    @FXML
    private Label add;

    @FXML
    private TextField deleteT;

    @FXML
    private Button deleteTI;
    @FXML
    void ADD(MouseEvent event) {
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Event_Add.fxml"));
            try {
                Parent root = loader.load();
                TicketsAdd controller = loader.getController();
                add.getScene().setRoot(root);
            }catch (IOException e){
                e.printStackTrace();
            }}
    }
    @FXML
    void deleteT(ActionEvent event)

    {
        int selectedItem = listV.getSelectionModel().getSelectedItem().getTickets_id();
        deleteT.setText(String.valueOf(selectedItem));
        String sql = "DELETE FROM tickets WHERE tickets_id = ?";
        try {
            // Create a confirmation dialog
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirmation");
            dialog.setContentText("Are you sure you want to delete this product?");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

            // Show the dialog and wait for the admin's response
            Optional<ButtonType> result = dialog.showAndWait();

            // If the admin chooses "Yes", delete the product
            if (result.isPresent() && result.get() == ButtonType.YES) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    int idSuppValue = Integer.parseInt(deleteT.getText());
                    preparedStatement.setInt(1, idSuppValue);
                    preparedStatement.executeUpdate();
                    System.out.println("Product Deleted successfully!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setContentText("Product Deleted successfully!");
                    alert.showAndWait();
                    initialize();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    {
        conn= data_source.getInstance().getCnx();
    }
    @FXML
    public void initialize() {
        // Créer une liste d'objets Produit
        ObservableList<tickets> items = FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM tickets";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                tickets t = new tickets();
                t.setTickets_id(rs.getInt(1));
                t.setTickets_type(rs.getString("tickets_type"));
                t.setTickets_price(rs.getInt("tickets_price"));
                 t.setQuantite(rs.getInt("quantite"));
                 t.setEvent_id(rs.getInt("event_id"));
                items.add(t);
            }

            // Lier la liste à la ListView
            listV.setItems(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
