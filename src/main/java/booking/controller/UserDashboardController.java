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

/**
 * Handles the dashboard logic which includes the profile settings button, reservation button, signOut button, and back button
 * @author Andres Feldstedt and Joseph Salama
 * @version 1.0
 */
public class UserDashboardController {

    @FXML
    private Button profileSettingsButton;
    @FXML
    private Button reservationsButton;
    @FXML
    private Button signOutButton; // Added Sign Out Button
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button backButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String loggedInUser = null;

    /**
     * Initialize the dashboard, fetching user session information.
     */
    public void initialize() {
        if (UserSession.getInstance().isLoggedIn()) {
            loggedInUser = UserSession.getInstance().getLoggedInUser();
            welcomeLabel.setText("WELCOME, " + loggedInUser + "!");
        } else {
            System.err.println("No user is logged in.");
        }
    }

    /**
     * Navigate to Profile Settings Page.
     */
    @FXML
    public void handleProfileSettingsButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/profileSettings.fxml"));
        System.out.println("Navigating to Profile Settings...");
        stage = (Stage) profileSettingsButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigate back to Homepage.
     */
    @FXML
    private void handleHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/initial.fxml"));
        System.out.println("Loading initial.fxml");
        stage = (Stage) backButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigate to Reservations Page.
     */
    @FXML
    public void handleReservationsAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/reservationsConfirmed.fxml"));
        System.out.println("Navigating to Reservations...");
        stage = (Stage) reservationsButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handle Sign Out action.
     * Clears the session and navigates back to the homepage.
     */
    @FXML
    public void handleSignOutAction(ActionEvent event) throws IOException {
        UserSession.getInstance().signOut(); // Clear the user session
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/initial.fxml"));
        System.out.println("User signed out. Redirecting to Homepage...");
        stage = (Stage) signOutButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
