package panier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        URL url = new File("C:/Users/admin/IdeaProjects/panier/src/main/java/panier/cart-ui.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Shopping Cart App");
        stage.setScene(new Scene(root, Color.TRANSPARENT));

        // phase 2
        stage.initStyle(StageStyle.TRANSPARENT);
        makeDraggable(root);
        stage.show();

        window = stage;
    }

    public static void main(String[] args) {
        launch();
    }


    public static Stage getWindow() {
        return window;
    }

    private void makeDraggable(Parent root){
        root.setOnMousePressed( e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        root.setOnMouseDragged( e -> {
            window.setX( e.getScreenX() - xOffset);
            window.setY( e.getScreenY() - yOffset);
        });
    }

}