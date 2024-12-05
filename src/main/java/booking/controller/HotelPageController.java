package booking.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;



public class HotelPageController {

    @FXML
    private ImageView HotelImage; // Hotel image
    @FXML
    private TextField HotelName; // Hotel name
    @FXML
    private javafx.scene.control.Button ViewProfileButton;
    @FXML
    private javafx.scene.control.Button SearchPageButton;
    @FXML
    private Button nextImageBtn; // Next image button
    @FXML
    private Button prevImageBtn; // Previous image button
    @FXML
    private TabPane TabPane; // Tab Pane for hotel details
    @FXML
    private Tab overviewTab, amenitiesTab, pricesTab, policiesTab;

    private String[] imageUrls = {"file:path/to/hotel/image1.jpg", "file:path/to/hotel/image2.jpg"}; // Image URLs
    private int currentImageIndex = 0; // Keep track of the current image index

    private String hotelName; // Dynamic hotel name
    private String hotelImageUrl; // Dynamic hotel image URL

    // Method to set hotel data dynamically
    public void setHotelData(String hotelName, String hotelImageUrl) {
        this.hotelName = hotelName;
        this.hotelImageUrl = hotelImageUrl;
        // Update UI elements dynamically
        updateUI();
    }

    // Method to update UI elements
    private void updateUI() {
        HotelName.setText(hotelName);
        HotelImage.setImage(new Image(hotelImageUrl));
    }

    //View Profile
    @FXML
    public void handleSignInAction(ActionEvent event) throws IOException {
        // Load Sign In Page
        Parent root = FXMLLoader.load(getClass().getResource("/booking/fxml/signInPage.fxml"));
        System.out.println("Loading SignInPage.fxml");

        Stage stage = (Stage) ViewProfileButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Change the displayed hotel image
    @FXML
    private void changeImage() {
        if (nextImageBtn.isArmed()) {
            currentImageIndex = (currentImageIndex + 1) % imageUrls.length;
        } else if (prevImageBtn.isArmed()) {
            currentImageIndex = (currentImageIndex - 1 + imageUrls.length) % imageUrls.length;
        }
        HotelImage.setImage(new Image(imageUrls[currentImageIndex]));
    }

    //Back to Search Page
    @FXML
    public void handleBackToSearchAction(ActionEvent event) {
        try {
            // Load and display Homepage (Search page)
            Parent root = FXMLLoader.load(getClass().getResource("/booking/fxml/initial.fxml"));
            System.out.println("Loading initial.fxml");
            Stage stage = (Stage) SearchPageButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load content for the tabs
    private void loadTabContent() {
        //Load actual data here based on the selected hotel
    }
}
