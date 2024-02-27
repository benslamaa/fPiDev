package controllers;
import entities.evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import service.tickets_service;
import entities.evenement;
import entities.tickets;
import service.event_service;

import java.sql.SQLException;
import java.util.Date;

import java.time.LocalDate;
import java.time.temporal.TemporalField;

public class TicketsAdd {
    private final tickets_service ts= new tickets_service();
    @FXML
    private TextField eventID;

    @FXML
    private TextField priceT;

    @FXML
    private TextField quantiteT;

    @FXML
    private TextField typeT;

    @FXML
    void addT(ActionEvent event) {
        try {
            String idevent = eventID.getText();
            String prixt = priceT.getText();
            String quantite = quantiteT.getText();
            String type = typeT.getText();


            // Vérifier si les champs obligatoires ne sont pas vides
            if (idevent.isEmpty() || prixt.isEmpty() || quantite.isEmpty() || type.isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return; // Sortir de la méthode si les champs obligatoires sont vides
            }

            // Vérifier si les valeurs numériques sont valides
            int Quantite = 0;
            int prix = 0;
            int id = 0;
            try {
                Quantite = Integer.parseInt(quantite);
                prix = Integer.parseInt(prixt);
                id = Integer.parseInt(idevent);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez saisir des valeurs numériques valides pour la quantité, le prix et la catégorie.");
                alert.showAndWait();
                return; // Sortir de la méthode si les valeurs numériques ne sont pas valides
            }

            // Si toutes les validations sont passées, ajouter le produit
            ts.add(new tickets(type, prix, Quantite, id));

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
    }
}

