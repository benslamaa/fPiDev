package controllers;
import entities.evenement;
import entities.tickets;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.event_service;
import entities.evenement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import java.time.LocalDate;
import java.time.temporal.TemporalField;

public class EventAdd {
    private final event_service es=new event_service();
   // private DatePicker dateE;
   @FXML
   private Label UpdateE;

    @FXML
    private Label BuyT;
    @FXML
    private Label showT;
    @FXML
    private Label showE;

    @FXML
    private TextField nameE;

    @FXML
    private TextField themeE;
    @FXML
    private Label ticketA;

    @FXML
    void showT(MouseEvent event) {
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tickets_show.fxml"));
            try {
                Parent root = loader.load();
                TicketsShow controller = loader.getController();
                showT.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void buyT(MouseEvent event) {
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tickets_add.fxml"));
            try {
                Parent root = loader.load();
                TicketsAdd controller = loader.getController();
                BuyT.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void showA(MouseEvent event) {
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/event_show.fxml"));
            try {
                Parent root = loader.load();
                EventShow controller = loader.getController();
                showE.getScene().setRoot(root);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void updateE(MouseEvent event) {
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifier_event.fxml"));
            try {
                Parent root = loader.load();
                ModifierEvent controller = loader.getController();
                UpdateE.getScene().setRoot(root);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    void addE(ActionEvent event)  {
        {
            try {

                String name = nameE.getText();
                String theme = themeE.getText();



                // Vérifier si les champs obligatoires ne sont pas vides
                if (name.isEmpty() || theme.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Veuillez remplir tous les champs.");
                    alert.showAndWait();
                    return; // Sortir de la méthode si les champs obligatoires sont vides
                }



                // Si toutes les validations sont passées, ajouter le produit
                es.add(new evenement(name, theme));

                // Afficher une alerte d'information en cas de succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("Le produit a été ajouté avec succès.");
                alert.showAndWait();
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }    }

}


