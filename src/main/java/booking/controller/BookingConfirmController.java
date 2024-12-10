package booking.controller;

import booking.application.Main;
import booking.dao.HotelDAO;
import booking.dao.RoomDAO;
import booking.dao.UserDAO;
import booking.dao.ReservationDAO;
import booking.model.Hotel;
import booking.model.Reservation;
import booking.model.Room;
import booking.model.UserProfile;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.FindIterable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.bson.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.bson.conversions.Bson;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingConfirmController {
    Hotel selectedHotel;
    Room selectedRoom;
    @FXML
    private Label hotelNameLabel;
    @FXML
    private Label roomNameLabel;
    @FXML
    private Label roomPriceLabel;
    @FXML
    private Button backButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button confirmButton;

    public void setHotelRoom(Hotel h, Room r) {
        if (r == null) {
            roomNameLabel.setText("");
            return;
        }
        if (h == null) {
            hotelNameLabel.setText("");
            return;
        }

        //making passed hotel and room available outside immediate method
        selectedHotel = h;
        selectedRoom = r;

        //Set Hotel name label
        hotelNameLabel.setText(h.getName());

        //Set Room name label
        roomNameLabel.setText("Room Type: " + r.getTypeName());

        //Get hotel with Room references
        System.out.println("hotel " + h.getName());

        roomPriceLabel.setText("Room Price: $" + String.valueOf(Math.round(r.getPrice())));
    }

    @FXML
    private void handleReservationConfirmation(ActionEvent event) throws IOException {
        try {
            // Load the new FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/booking/fxml/roomSelection.fxml"));
            Parent root = loader.load();

            // Get the controller of the new scene
            roomSelectionController roomSelectioncontroller = loader.getController();

            // Inject the selected hotel
            roomSelectioncontroller.setHotel(selectedHotel);

            // Create and show the new stage
            Stage roomSelectionStage = (Stage) backButton.getScene().getWindow();
            roomSelectionStage.setScene(new Scene(root));
            roomSelectionStage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not open Room Selection");
            alert.setContentText("An error occurred while opening the room selection screen.");
            alert.showAndWait();

            // Handle exception (show error dialog, log, etc.)
            e.printStackTrace();
        }
    }

    @FXML
    public void handleConfirmButton(ActionEvent event) throws IOException {
        if(UserSession.getInstance().isLoggedIn() == true) {
            //Initializing DAOs
            UserDAO userDAO = new UserDAO();
            HotelDAO hotelDAO = new HotelDAO();
            RoomDAO roomDAO = new RoomDAO();
            ReservationDAO ReservationDAO = new ReservationDAO();



            //Get Date Input
            LocalDate today = LocalDate.now();
            LocalDate dayAfterTomorrow = today.plusDays(2);

            //Get User
            String loggedInUser = UserSession.getInstance().getLoggedInUser();
            UserProfile dbUser = userDAO.get(loggedInUser);

            //Create Reservation
            Reservation newReservation = new Reservation(today, dayAfterTomorrow, dbUser, selectedHotel, selectedRoom);
            ReservationDAO.add(newReservation);

            root = FXMLLoader.load(getClass().getResource("/booking/fxml/reservationsConfirmed.fxml"));
            System.out.println("Navigating to Reservations...");
            stage = (Stage) confirmButton.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            showAlert(Alert.AlertType.ERROR, "User Not Logged In", "Please log in first.");
        }


    }

    /**
     * Utility method to show an alert dialog.
     *
     * @param alertType The type of alert (e.g., ERROR, INFORMATION).
     * @param title     The title of the alert.
     * @param message   The message to display in the alert.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
