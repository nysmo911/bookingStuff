package booking.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserDashboardController {

    @FXML
    private Button profileSettingsButton;

    @FXML
    private Button reservationsButton;

    /**
     * This method will be invoked when the Profile Settings button is clicked.
     */
    @FXML
    private void handleProfileSettingsAction(ActionEvent event) {
        // Your method code here
        System.out.println("Navigating to Profile Settings...");
    }

    /**
     * This method will be invoked when the Reservations button is clicked.
     */
    @FXML
    private void handleReservationsAction(ActionEvent event) {
        // Your method code here
        System.out.println("Navigating to Reservations...");
    }
}
