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
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class SignUpController {
    @FXML
    private TextField usernameField; // Links to TextField in scene
    @FXML
    private TextField passwordField; // Links to PasswordField in scene
    @FXML
    private TextField fnameField; // Links to fnameField in scene
    @FXML
    private TextField lnameField; // Links to lnameField in scene
    @FXML
    private TextField emailField; // Links to emailField in scene
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Instance of UserDAO for database operations
    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleCreateUser(ActionEvent event) throws IOException {
        // Retrieve user input
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fname = fnameField.getText();
        String lname = lnameField.getText();
        String email = emailField.getText();

        // Validate input fields
        if (username.isEmpty() || password.isEmpty() || fname.isEmpty() || lname.isEmpty() || email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Sign-Up Error", "All fields must be filled out.");
            return;
        }

        // Check if username already exists in the database
        if (userDAO.get(username) != null) {
            showAlert(Alert.AlertType.ERROR, "Sign-Up Error", "Username already exists. Please choose a different username.");
            return;
        }

        // Create a new UserProfile and add it to the database
        UserProfile newUser = new UserProfile(fname, lname, email, username, password);
        userDAO.add(newUser);

        // Show success message
        showAlert(Alert.AlertType.INFORMATION, "Sign-Up Successful", "Account created successfully! Please sign in.");

        // Redirect to the Sign-In Page
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/signInPage.fxml"));
        System.out.println("Loading SignInPage.fxml");
        stage = (Stage) submitButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/signInPage.fxml"));
        System.out.println("Loading signInPage.fxml");
        stage = (Stage) backButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Utility method to show an alert dialog.
     *
     * @param alertType The type of alert (e.g., ERROR, INFORMATION).
     * @param title     The title of the alert.
     * @param message   The message to display in the alert.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
