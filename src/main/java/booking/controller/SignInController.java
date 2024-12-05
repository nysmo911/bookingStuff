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

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        // Saves entries
        String username = usernameField.getText();
        String password = passwordField.getText();

        // For testing
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        UserSession.getInstance().setLoggedIn(true);
        UserSession.getInstance().setLoggedInUser(username);
        System.out.println(UserSession.getInstance().getLoggedInUser());
        System.out.println(UserSession.getInstance().isLoggedIn());

        root = FXMLLoader.load(getClass().getResource("/booking/fxml/initial.fxml"));
        System.out.println("Loading initial.fxml");
        stage = (Stage) signInButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
}

