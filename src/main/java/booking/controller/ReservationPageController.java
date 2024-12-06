package booking.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;


public class ReservationPageController {

    @FXML
    private Label hotelNameLabel;

    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;

    @FXML
    private Label priceLabel;

    @FXML
    private ComboBox<String> roomTypeComboBox;

    @FXML
    private Button reserveButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button backButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize() {
        // Initialize room types

        // Set hotel name
        // Replace with actual value from database

        // Update price based on default room type

        // Handle reservation logic
        @FXML
        private void handleReservation () {
            String checkIn = checkInDate.getValue() != null ? checkInDate.getValue().toString() : null;
            String checkOut = checkOutDate.getValue() != null ? checkOutDate.getValue().toString() : null;
            String roomType = roomTypeComboBox.getValue();
            String price = priceLabel.getText();


            // Print reservation details (placeholder for actual logic)
            System.out.println("Reservation Details:");
            System.out.println("Hotel: " + hotelNameLabel.getText());
            System.out.println("Check-In: " + checkIn);
            System.out.println("Check-Out: " + checkOut);
            System.out.println("Price: " + price);
            System.out.println("Room Type: " + roomType);


            // Add further reservation processing
        }

        //Dashboard page
        @FXML
        public void handleDashboardAction (ActionEvent event) throws IOException {
            Pane root = FXMLLoader.load(getClass().getResource("/booking/fxml/userDashboard.fxml"));
            Stage stage = (Stage) dashboardButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        //Previous page
        @FXML
        private void handleHomepage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("/booking/fxml/initial.fxml"));
            System.out.println("Loading initial.fxml");
            stage = (Stage) backButton.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        // Update price from the database based on room type
    }
}
