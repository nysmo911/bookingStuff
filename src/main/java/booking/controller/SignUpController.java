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
import booking.model.UserProfile;

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
    private String username;
    private String password;
    private String fname;
    private String lname;
    private String email;


    @FXML
    private void handleCreateUser(ActionEvent event) throws IOException {
        username = usernameField.getText();
        password = passwordField.getText();
        fname = fnameField.getText();
        lname = lnameField.getText();
        email = emailField.getText();


        UserProfile userProfile1 = new UserProfile(fname, lname, email, username, password);

        // Loads Sign In Page
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
}
