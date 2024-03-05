package controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import entities.evenement;
import entities.tickets;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import service.event_service;
import service.tickets_service;
import utils.Sendmail;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;


public class TicketsAdd {


    @FXML
    private Label showT;
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
    private Label EventA;
    @FXML
    private Label show;

    String filePath;
    private  Sendmail sn = new Sendmail();

private event_service es= new event_service();
    @FXML
    void showE(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/event_show.fxml"));
        try {
            Parent root = loader.load();
            EventShow controller = loader.getController();
            show.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void eventA(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Event_Add.fxml"));
        try {
            Parent root = loader.load();
            EventAdd controller = loader.getController();
            EventA.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
            evenement e = es.getOneById(id);

            String qrText="Event : "+e.getEvent_name()+"Ticke type"+type+"Price :"+prix;

            this.generateQRCodeAndSave(qrText, String.valueOf(prix));


            // Si toutes les validations sont passées, ajouter le produit
            ts.add(new tickets(type, prix, Quantite, id,filePath));


            sn.envoyer(
                    "youssef.guebzili@esprit.tn",
                    "Ajout d'un ticket",
                    "Le ticket de type"+type+" a été ajouté avec succès"
            );
            // Afficher une alerte d'information en cas de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Le produit a été ajouté avec succès.");
            alert.showAndWait();
        } catch (IllegalArgumentException | WriterException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public String generateQRCodeAndSave(String text, String fileName) throws WriterException {
        // Generate the QR code

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Convert the BufferedImage to a JavaFX Image
        Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);

        // Save the image to the specified directory
        String directoryPath = "C:/Users/pc/Desktop/foody/src/main/java/imagesqr";
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        filePath = directoryPath + "/" + fileName + ".png";
        System.out.println(filePath);
        File file = new File(filePath);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(fxImage, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }




}

