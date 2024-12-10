package booking.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class ReservationsController {
    @FXML
    private Button backButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void handleDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/userDashboard.fxml"));
        System.out.println("Loading userDashboard.fxml");
        stage = (Stage) backButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
