package booking.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HotelListController {


    @FXML
    private javafx.scene.control.Button ViewProfileButton;
    @FXML
    private javafx.scene.control.Button SearchPageButton;

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

    //hotel page navigation
    private void loadHotelPage() {
        try {
            // Load and display HotelPage
            Parent root = FXMLLoader.load(getClass().getResource("/booking/fxml/HotelPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Hotel Page");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleHotelPageAction1(ActionEvent event) {
        loadHotelPage();
    }

    @FXML
    public void handleHotelPageAction2(ActionEvent event) {
        loadHotelPage();
    }

    @FXML
    public void handleHotelPageAction3(ActionEvent event) {
        loadHotelPage();
    }

}
