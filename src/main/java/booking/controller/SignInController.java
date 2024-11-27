package booking.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignInController {
    @FXML
    private TextField usernameField; // Links to TextField in scene

    @FXML
    private TextField passwordField; // Links to PasswordField in scene

    @FXML
    private void handleLogin() {
        // Saves entries
        String username = usernameField.getText();
        String password = passwordField.getText();

        // For testing
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
