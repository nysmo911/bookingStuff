package booking.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.*;
import java.io.IOException;

/**
 * booking.application.java is the entry point of our application. It extends application
 * from javafx.
 * @author Brandon Brenes
 * @version 1.0
 */
public class Main extends Application {

    public static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //Stage stage = new Stage();
        scene = new Scene(loadFXML("/booking/fxml/initial"));
        Image icon = new Image("booking/images/airplane.png");

        stage.getIcons().add(icon);
        stage.setTitle("bookingStuff");
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


}


