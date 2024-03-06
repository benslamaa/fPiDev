package com.example.fpidev;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.fpidev.entities.User;
import com.example.fpidev.interfaces.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ClientController {
    @FXML
    private Label uername;


    @FXML
    private Label cin;

    @FXML
    private Label email;

    @FXML
    private Button logout;

    @FXML
    private Label name;

    @FXML
    private Label prenon;

    @FXML
    private Label tel;




    @FXML
    private AnchorPane ProfileFX;

    @FXML
    private Label agelab;

    @FXML
    private ListView<?> compteFX1;

    @FXML
    private Label emaillab;

    @FXML
    private Label idlab;

    @FXML
    private Label nomlab;

    @FXML
    private Label prenomlab;


    @FXML
    private static User loggedInUser;
    public static void setLoggedInUser(User user){loggedInUser = user;}
    ServiceUser serviceUser = new ServiceUser();

    @FXML
    void CreerFX(ActionEvent event) {

    }

    @FXML
    void ModifierFX(ActionEvent event) {

    }

    @FXML
    void return2(MouseEvent event) {

    }
    @FXML
    private void deconnecterButtonOnClick(ActionEvent event){
        loggedInUser = null;
        serviceUser.changeScreen(event, "/com/example/fpidev/login.fxml", "LOGIN");
    }
    @FXML
    void initialize() {

        uername.setText(loggedInUser.getNom());
        email.setText(loggedInUser.getEmail());
        name.setText(loggedInUser.getNom());
        prenon.setText(loggedInUser.getPrenom());
        cin.setText(String.valueOf(loggedInUser.getCin()));
        tel.setText(String.valueOf(loggedInUser.getNumtel()));

    }

}
