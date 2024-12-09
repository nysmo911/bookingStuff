package booking.controller;

import booking.dao.HotelDAO;
import booking.dao.RoomDAO;
import booking.model.Hotel;
import booking.model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.List;

public class ReservationPageController {

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label selectedRoomTypeLabel;

    @FXML
    private Label selectedPriceLabel;

    @FXML
    private DatePicker checkInDatePicker;

    @FXML
    private DatePicker checkOutDatePicker;

    @FXML
    private Button reserveButton;

    @FXML
    private Button backButton;

    @FXML
    private Button dashboardButton;

    private HotelDAO hotelDAO;
    private RoomDAO roomDAO;

    private String hotelId;
    private String roomType;
    private String roomPrice;

    @FXML
    private void initialize() {
        // Initialize DAOs
        hotelDAO = new HotelDAO();
        roomDAO = new RoomDAO();
    }

    public void setReservationDetails(String hotelName, String roomType) {
        this.roomType = roomType;

        // Fetch hotel
        Hotel hotel = hotelDAO.get(hotelName);
        if (hotel != null) {
            hotelId = String.valueOf(hotelDAO.getID(hotelName));
            hotelNameLabel.setText(hotel.getName());

            // Fetch Room
            Long hotelIdLong = Long.parseLong(hotelId);
            Object roomId = roomDAO.getID(roomType, hotelIdLong);

            if (roomId != null) {
                Room room = roomDAO.get(roomId.toString());
                if (room != null) {
                    selectedRoomTypeLabel.setText("Selected Room: " + room.getTypeName());
                    selectedPriceLabel.setText("Price: $" + room.getPrice());
                    roomPrice = String.valueOf(room.getPrice());
                }
            }
        }
    }

    // Handle reservation logic
    @FXML
    private void handleReservation() {
        String checkInDate = (checkInDatePicker.getValue() != null) ? checkInDatePicker.getValue().toString() : null;
        String checkOutDate = (checkOutDatePicker.getValue() != null) ? checkOutDatePicker.getValue().toString() : null;

        if (checkInDate == null || checkOutDate == null) {
            System.out.println("Error: Check-in and Check-out dates are required.");
            return;
        }

        System.out.println("Reservation successful!");
        System.out.println("Hotel: " + hotelNameLabel.getText());
        System.out.println("Check-In: " + checkInDate);
        System.out.println("Check-Out: " + checkOutDate);
        System.out.println("Room Type: " + roomType);
        System.out.println("Price: $" + roomPrice);
    }

    // Navigate to the dashboard
    @FXML
    private void handleDashboardAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/booking/fxml/userDashboard.fxml"));
        Stage stage = (Stage) dashboardButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Navigate back to the homepage
    @FXML
    private void handleHomepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/booking/fxml/initial.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
