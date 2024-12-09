package booking.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class UserDashboardController {

    @FXML
    private Button profileSettingsButton;
    @FXML
    private Button reservationsButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button backButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String loggedInUser = null;

    public void initialize() {
        UserSession.getInstance().isLoggedIn();
        loggedInUser = UserSession.getInstance().getLoggedInUser();
        welcomeLabel.setText("WELCOME, " + loggedInUser + "!");
    }


    // Handle Profile Settings Button Click
    @FXML
    public void handleProfileSettingsButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/profileSettings.fxml"));
        System.out.println("Navigating to Profile Settings...");
        stage = (Stage) profileSettingsButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/initial.fxml"));
        System.out.println("Loading initial.fxml");
        stage = (Stage) backButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



/*    // Handle Reservations Button Click
    @FXML
    public void handleReservationsAction(ActionEvent event) {
        System.out.println("Navigating to Reservations...");
    }*/
}
