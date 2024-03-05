module com.example.fpidev {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fpidev to javafx.fxml;
    exports com.example.fpidev;
}