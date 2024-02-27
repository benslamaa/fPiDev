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
import service.event_service;
import utils.data_source;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class EventShow {
    @FXML
    private ListView<evenement> ListView;
    @FXML
    private Label UpdateE;

    @FXML
    private Label addE;
    @FXML
    void addE(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Event_Add.fxml"));
        try {
            Parent root = loader.load();
            EventAdd controller = loader.getController();
            addE.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }}



    @FXML
    void updateE(MouseEvent event) {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifier_event.fxml"));
                try {
                    Parent root = loader.load();
                    ModifierEvent controller = loader.getController();
                    UpdateE.getScene().setRoot(root);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        private Connection conn;
    private java.awt.Label IdSupp;

    {
                conn= data_source.getInstance().getCnx();
        }
       @FXML
                public void initialize() {
                        // Créer une liste d'objets Produit
                        ObservableList<evenement> items = FXCollections.observableArrayList();
                        try {

                                String req = "SELECT * FROM evenement";
                                Statement st = conn.createStatement();
                                ResultSet rs = st.executeQuery(req);
                                while (rs.next()) {
                                        evenement e = new evenement();
                                        e.setEvent_id(rs.getInt(1));
                                        e.setEvent_name(rs.getString("event_name"));
                                        e.setEvent_theme(rs.getString("event_theme"));
                                       // e.setEvent_date(rs.getInt("prix_produit"));
                                        items.add(e);
                                }

                                // Lier la liste à la ListView
                                ListView.setItems(items);
                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }
       }
    @FXML
    private Button deleteE;

    @FXML
    private TextField label;

    @FXML
    void Onclick(MouseEvent event) {
        int  selectedItem = ListView.getSelectionModel().getSelectedItem().getEvent_id();
        label.setText(String.valueOf(selectedItem));
    }

    @FXML

    public void deleteEv(ActionEvent actionEvent) throws SQLException {
        int selectedItem = ListView.getSelectionModel().getSelectedItem().getEvent_id();
        label.setText(String.valueOf(selectedItem));
        String sql = "DELETE evenement FROM evenement JOIN tickets ON (tickets.event_id = evenement.event_id) WHERE tickets.event_id = ?";
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
                    int labelVallue = Integer.parseInt(label.getText());
                    preparedStatement.setInt(1, labelVallue);
                    preparedStatement.executeUpdate();
                    System.out.println("Category Deleted successfully!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
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

        }




