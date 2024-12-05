package booking.controller;

import booking.model.ProfileSettings;
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
    private TextField addressField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private Button editButton;

    private ProfileSettings profileSettings;

    // Method to initialize the profile settings with existing data
    public void initialize(ProfileSettings profileSettings) {
        this.profileSettings = profileSettings;
        fNameField.setText(profileSettings.getFName());
        lNameField.setText(profileSettings.getLName());
        addressField.setText(profileSettings.getAddress());
        passwordField.setText(profileSettings.getPassword());
    }

    // Handle Edit button action
    @FXML
    public void handleEditAction(ActionEvent event) {
        // Update the ProfileSettings object with the new values
        profileSettings.setFName(fNameField.getText());
        profileSettings.setLName(lNameField.getText());
        profileSettings.setAddress(addressField.getText());

        // Optionally handle password changes
        if (!newPasswordField.getText().isEmpty()) {
            profileSettings.setPassword(newPasswordField.getText());
        }

        System.out.println("Profile updated successfully:");
        System.out.println("First Name: " + profileSettings.getFName());
        System.out.println("Last Name: " + profileSettings.getLName());
        System.out.println("Address: " + profileSettings.getAddress());
    }
}
