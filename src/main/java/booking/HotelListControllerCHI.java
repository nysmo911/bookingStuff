package booking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HotelListControllerCHI {

    @FXML
    public void handleSignInAction(ActionEvent event) {
        try {
            // Loads Sign In Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/booking/fxml/signInPage.fxml"));
            System.out.println("Loading SignInPage.fxml");
            Parent root = loader.load();
            System.out.println("SignInPage.fxml loaded successfully");

            // Creates a new Stage for Sign-In
            Stage stage = new Stage();
            stage.setTitle("Sign In");
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("Sign-In stage displayed");
        } catch (Exception e) {
            e.printStackTrace(); //used for debugging
        }
    }

    @FXML
    public void handleBackToSearchAction(ActionEvent event) {
        try {
            // Load and display Homepage (Search page)
            Parent root = FXMLLoader.load(getClass().getResource("/booking/fxml/Homepage.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Homepage");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void handleHotelPageActionCHI1(ActionEvent event) {
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
    public void handleHotelPageActionCHI2(ActionEvent event) {
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
    public void handleHotelPageActionCHI3(ActionEvent event) {
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
}
