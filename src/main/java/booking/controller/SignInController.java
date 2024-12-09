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

public class SignInController {
    @FXML
    private TextField usernameField; // Links to TextField in scene
    @FXML
    private TextField passwordField; // Links to PasswordField in scene
    @FXML
    private Button signUpButton;
    @FXML
    private Button signInButton;
    @FXML
    private Button backButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Instance of UserDAO to access the database
    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        // Retrieve username and password from text fields
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Input validation
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Login Error", "Username and password cannot be empty.");
            return;
        }

        // Retrieve the user from the database
        UserProfile user = userDAO.get(username);

        // Check if the user exists and the password matches
        if (user == null) {
            showAlert(Alert.AlertType.ERROR, "Login Error", "User does not exist.");
        } else if (!user.getPassword().equals(password)) {
            showAlert(Alert.AlertType.ERROR, "Login Error", "Incorrect password. Please try again.");
        } else {
            // Set the logged-in user in the session
            UserSession.getInstance().setLoggedIn(true);
            UserSession.getInstance().setLoggedInUser(username);

            // Redirect to the homepage
            root = FXMLLoader.load(getClass().getResource("/booking/fxml/initial.fxml"));
            System.out.println("Loading initial.fxml");
            stage = (Stage) signInButton.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/createAnAccountPage.fxml"));
        System.out.println("Loading SignUpPage.fxml");
        stage = (Stage) signUpButton.getScene().getWindow();
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
