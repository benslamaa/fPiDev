package com.example.fpidev;

import com.example.fpidev.entities.Encryptor;
import com.example.fpidev.entities.User;
import com.example.fpidev.interfaces.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    private Button Exit;

    @FXML
    private TextField cinSignup;

    @FXML
    private Label cinSignupError;

    @FXML
    private PasswordField confirmMdpSignup;

    @FXML
    private Label confirmMdpSignupError;

    @FXML
    private TextField emailSignup;

    @FXML
    private Label emailSignupError;

    @FXML
    private Button loginSignup;

    @FXML
    private PasswordField mdpSignup;

    @FXML
    private Label mdpSignupError;

    @FXML
    private TextField nomSignup;

    @FXML
    private Label nomSignupError;

    @FXML
    private TextField numtelSignup;

    @FXML
    private Label numtelSignupError;

    @FXML
    private TextField prenomSignup;

    @FXML
    private Label prenomSignupError;

    @FXML
    private TextField pseudoSignup;

    @FXML
    private Label pseudoSignupError;

    @FXML
    private Button signUpButton;
    ServiceUser serviceUser = new ServiceUser();
    Encryptor encryptor = new Encryptor();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginSignupButtonOnClick(ActionEvent event){
        serviceUser.changeScreen(event, "login.fxml", "Login");
    }
    public boolean getErrors(){
        pseudoSignupError.setText("");
        cinSignupError.setText("");
        nomSignupError.setText("");
        prenomSignupError.setText("");
        numtelSignupError.setText("");
        emailSignupError.setText("");
        mdpSignupError.setText("");
        confirmMdpSignupError.setText("");
        if(pseudoSignup.getText().isBlank()){
            pseudoSignupError.setTextFill(Color.RED);
            pseudoSignupError.setText("Le Pseudo est invalide");
            return true;
        }
        if(cinSignup.getText().isBlank() || !cinSignup.getText().matches("\\d{1,9}")){
            cinSignupError.setTextFill(Color.RED);
            cinSignupError.setText("Le CIN est invalide");
            return true;
        }
        if(nomSignup.getText().isBlank() || !nomSignup.getText().matches("[a-zA-Z ]+")){
            nomSignupError.setTextFill(Color.RED);
            nomSignupError.setText("Le nom est invalide");
            return true;
        }
        if(prenomSignup.getText().isBlank() || !prenomSignup.getText().matches("[a-zA-Z ]+")){
            prenomSignupError.setTextFill(Color.RED);
            prenomSignupError.setText("Le prénom est invalide");
            return true;
        }

        if(numtelSignup.getText().isBlank() || !numtelSignup.getText().matches("\\d{1,12}")){
            numtelSignupError.setTextFill(Color.RED);
            numtelSignupError.setText("Le numéro de téléphone est invalide");
            return true;
        }
        if(emailSignup.getText().isBlank() || !emailSignup.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            emailSignupError.setTextFill(Color.RED);
            emailSignupError.setText("L'email est invalide");
            return true;
        }
        if(mdpSignup.getText().isBlank()|| mdpSignup.getText().length() < 8 || mdpSignup.getText().matches("[^a-zA-Z0-9]")){
            mdpSignupError.setTextFill(Color.RED);
            mdpSignupError.setText("Le mot de passe est invalide");
            return true;
        }
        if(confirmMdpSignup.getText().isBlank()){
            confirmMdpSignupError.setTextFill(Color.RED);
            confirmMdpSignupError.setText("La confirmation du mot de passe est invalide");
            return true;
        }
        if(!Objects.equals(confirmMdpSignup.getText(), mdpSignup.getText())){
            confirmMdpSignupError.setTextFill(Color.RED);
            confirmMdpSignupError.setText("Le mot de passe doit etre le meme");
            return true;
        }

        try {
            if (serviceUser.pseudoExiste(pseudoSignup.getText())){
                pseudoSignupError.setTextFill(Color.RED);
                pseudoSignupError.setText("Ce pseudo est déjà utilisé, veuillez en choisir un autre");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void signUpButtonButtonOnClick(ActionEvent event) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (!getErrors()) {
            User newUser = new User(pseudoSignup.getText(),nomSignup.getText(),prenomSignup.getText(),emailSignup.getText(),mdpSignup.getText(),"client",Integer.parseInt(cinSignup.getText()),Integer.parseInt(numtelSignup.getText()));
            try {
                serviceUser.ajouter(newUser);
                System.out.println("Utilisateur ajouté avec succès !");
                JOptionPane.showMessageDialog(null,"Vous etes inscris avec succès ! Veuillez connecter maintenant.");
                serviceUser.changeScreen(event, "/login.fxml", "LOGIN");
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
            }

        }
    }
}
