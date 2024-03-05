module Foody {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.naming;

    requires javafx.swing;
    requires jakarta.mail;
    requires com.google.zxing.javase;
    requires com.google.zxing;
    //   requires javase.java6;
    // requires core;


    opens org.example to javafx.fxml;
    opens controllers to javafx.fxml;

    exports org.example;
    exports entities;
    exports service;
    exports utils;
    exports controllers;


}