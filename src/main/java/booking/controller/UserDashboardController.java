package booking.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class UserDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button profileSettingsButton;

    @FXML
    private Button reservationsButton;

    /**
     * Initializes the dashboard and sets up any required data.
     */
    @FXML
    private void initialize() {
        // Example: Set a dynamic welcome message (if you have user data).
        welcomeLabel.setText("Welcome, User!"); // Replace "User" with actual username logic if available
    }

    /**
     * Handles the "Profile Settings" button click.
     */
    @FXML
    private void handleProfileSettingsButton() {
        System.out.println("Navigating to Profile Settings...");
        // Add logic to navigate to Profile Settings scene.
        // Example: Load the ProfileSettings.fxml and set it as the current scene.
    }

    /**
     * Handles the "Past & Upcoming Reservations" button click.
     */
    @FXML
    private void handleReservationsAction() {
        System.out.println("Navigating to Past & Upcoming Reservations...");
        // Add logic to navigate to the Reservations scene.
        // Example: Load the Reservations.fxml and set it as the current scene.
    }
}
