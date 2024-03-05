package com.example.fpidev.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Button Exit;

    @FXML
    private Label echecLoginLabel;

    @FXML
    private Button eyeIconLogin;

    @FXML
    private Button loginButton;

    @FXML
    private TextField mdpLogin;

    @FXML
    private Button mdpOublie;

    @FXML
    private TextField pseudoLogin;

    @FXML
    private Button signupLogin;

    @FXML
    void initialize() {
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }


}
