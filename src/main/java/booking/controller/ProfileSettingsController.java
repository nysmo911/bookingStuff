package booking.controller;

import booking.dao.UserDAO;
import booking.model.UserProfile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for Profile Settings Page.
 */
public class ProfileSettingsController {

    @FXML
    private TextField fnameField;
    @FXML
    private TextField lnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button backButton; // Back Button

    private Stage stage;
    private Scene scene;

    private UserDAO userDAO = new UserDAO();
    private String loggedInUser;

    /**
     * Initialize the profile settings page with the current user's information.
     */
    public void initialize() {
        if (UserSession.getInstance().isLoggedIn()) {
            loggedInUser = UserSession.getInstance().getLoggedInUser();
            loadUserData();
        } else {
            showErrorAlert("No user is logged in.");
        }
    }

    /**
     * Load user data from the database into the fields.
     */
    private void loadUserData() {
        UserProfile user = userDAO.get(loggedInUser);
        if (user != null) {
            fnameField.setText(user.getFName());
            lnameField.setText(user.getLName());
            emailField.setText(user.getEmail());
            usernameField.setText(user.getUserName());
            passwordField.setText(user.getPassword());
        } else {
            showErrorAlert("Failed to load user data.");
        }
    }

    /**
     * Save the updated user profile information.
     *
     * @param event Action event triggered by the Save button.
     */
    @FXML
    private void handleSave(ActionEvent event) {
        // Get updated values from the fields
        String updatedFirstName = fnameField.getText();
        String updatedLastName = lnameField.getText();
        String updatedEmail = emailField.getText();
        String updatedUsername = usernameField.getText();
        String updatedPassword = passwordField.getText();

        // Validate the inputs
        if (updatedFirstName.isEmpty() || updatedLastName.isEmpty() || updatedEmail.isEmpty() ||
                updatedUsername.isEmpty() || updatedPassword.isEmpty()) {
            showErrorAlert("All fields must be filled out.");
            return;
        }

        // Update user in the database
        try {
            userDAO.update(loggedInUser, "first_name", updatedFirstName);
            userDAO.update(loggedInUser, "last_name", updatedLastName);
            userDAO.update(loggedInUser, "email", updatedEmail);
            userDAO.update(loggedInUser, "username", updatedUsername);
            userDAO.update(loggedInUser, "password", updatedPassword);

            // Update the logged-in user session
            UserSession.getInstance().setLoggedInUser(updatedUsername);

            showSuccessAlert("Profile updated successfully!");
        } catch (Exception e) {
            showErrorAlert("An error occurred while saving your changes.");
            e.printStackTrace();
        }
    }

    /**
     * Cancel changes and return to the Dashboard page.
     *
     * @param event Action event triggered by the Cancel button.
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        navigateToDashboard();
    }

    /**
     * Navigate back to the Dashboard page.
     *
     * @param event Action event triggered by the Back button.
     */
    @FXML
    private void handleBackAction(ActionEvent event) {
        navigateToDashboard();
    }

    /**
     * Helper method to navigate back to the Dashboard page.
     */
    private void navigateToDashboard() {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/booking/fxml/userDashboard.fxml"));
            stage = (Stage) backButton.getScene().getWindow(); // Use cancelButton or backButton
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showErrorAlert("Error loading the dashboard page.");
        }
    }

    /**
     * Display a success alert.
     *
     * @param message Success message to be displayed.
     */
    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Display an error alert.
     *
     * @param message Error message to be displayed.
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
