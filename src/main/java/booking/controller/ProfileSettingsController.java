package booking.controller;

import booking.model.userProfile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProfileSettingsController {

    @FXML
    private TextField fNameField;
    @FXML
    private TextField lNameField;
    @FXML
    private TextField emailField;
    @FXML
    private Button editButton;

    private userProfile userProfile;

    // Method to initialize the profile settings with existing data
    public void initialize(userProfile userProfile) {
        this.userProfile = userProfile;
        fNameField.setText(userProfile.getFName());
        lNameField.setText(userProfile.getLName());
        emailField.setText(userProfile.getEmail());
    }

    // Handle Edit button action
    @FXML
    public void handleEditAction(ActionEvent event) {
        // Update the ProfileSettings object with the new values
        userProfile.setFName(fNameField.getText());
        userProfile.setLName(lNameField.getText());
        userProfile.setEmail(emailField.getText());

        System.out.println("Profile updated successfully:");
        System.out.println("First Name: " + userProfile.getFName());
        System.out.println("Last Name: " + userProfile.getLName());
        System.out.println("Email: " + userProfile.getEmail());
    }
}
