package booking.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReservationPageController {

    @FXML
    private Label hotelNameLabel;

    @FXML
    private TextField hotelNameField;

    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<String> roomTypeComboBox;

    // Update hotel name label dynamically
    @FXML
    private void updateHotelName() {
        hotelNameLabel.setText(hotelNameField.getText());
    }

    // Handle reservation logic
    @FXML
    private void handleReservation() {
        String hotelName = hotelNameField.getText();
        String checkIn = checkInDate.getValue() != null ? checkInDate.getValue().toString() : "N/A";
        String checkOut = checkOutDate.getValue() != null ? checkOutDate.getValue().toString() : "N/A";
        String price = priceField.getText();
        String roomType = roomTypeComboBox.getValue();

        System.out.println("Reservation Details:");
        System.out.println("Hotel: " + hotelName);
        System.out.println("Check-In: " + checkIn);
        System.out.println("Check-Out: " + checkOut);
        System.out.println("Price: " + price);
        System.out.println("Room Type: " + roomType);

        // Add further reservation processing logic here.
    }
}
