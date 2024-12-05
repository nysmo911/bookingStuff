package booking.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    @FXML
    private void handleCreateUser(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fname = fnameField.getText();
        String lname = lnameField.getText();
        String email = emailField.getText();

        // For testing
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Fname: " + fname);
        System.out.println("Lname: " + lname);
        System.out.println("Email: " + email);
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
}
