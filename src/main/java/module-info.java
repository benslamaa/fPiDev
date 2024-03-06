module com.example.fpidev {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.mail;


    opens com.example.fpidev to javafx.fxml;
    exports com.example.fpidev;

}