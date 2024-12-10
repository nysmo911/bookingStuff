package booking.controller;

import booking.application.Main;
import booking.dao.HotelDAO;
import booking.dao.RoomDAO;
import booking.model.Hotel;
import booking.model.Room;
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
import java.util.List;



import static booking.application.Main.scene;

public class roomSelectionController {

    private RoomDAO roomDAO = new RoomDAO(); // Presuming you use this later for fetching rooms.
    private HotelDAO hotelDAO = new HotelDAO();
    private Hotel hotel = new Hotel();



    @FXML
    private Label hotelNameLabel;
    @FXML
    private Label roomLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label capacityLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label roomLabel1;
    @FXML
    private Label priceLabel1;
    @FXML
    private Label capacityLabel1;
    @FXML
    private Label descriptionLabel1;
    @FXML
    private Label roomLabel2;
    @FXML
    private Label priceLabel2;
    @FXML
    private Label capacityLabel2;
    @FXML
    private Label descriptionLabel2;
    @FXML
    private Label roomLabel3;
    @FXML
    private Label priceLabel3;
    @FXML
    private Label capacityLabel3;
    @FXML
    private Label descriptionLabel3;
    @FXML
    private VBox roomVbox;
    @FXML
    private VBox room1Vbox;
    @FXML
    private VBox room2Vbox;
    @FXML
    private VBox room3Vbox;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button backButton;

    public void setHotel(Hotel h) {
        if(h == null){
            hotelNameLabel.setText("");
            return;
        }
        //Set name label
        hotelNameLabel.setText(h.getName());

        //Get hotel with Room references
        System.out.println("hotel" + hotel);
        Hotel hotelWithRooms = hotelDAO.get(h.getName());
        System.out.println("hotelWithRooms" + hotelWithRooms);
        List<Room> rooms = hotelWithRooms.getRooms();
        Room room = rooms.getFirst();
        Room room1 = rooms.get(1);
        Room room2 = rooms.get(2);
        Room room3 = rooms.get(3);
      //"$" + String.valueOf(Math.round(room1.getPrice())));

        //Set labels

        //Room 0
        roomLabel.setText(room.getTypeName());
        priceLabel.setText("$" + String.valueOf(Math.round(room.getPrice())));
        capacityLabel.setText("Capacity: " + String.valueOf(room.getCapacity()));
        descriptionLabel.setText(room.getDescription());
        roomVbox.setOnMouseClicked(event -> {
            openReservationConfirmation(hotel, room);
        });

        //Room 1
        roomLabel1.setText(room1.getTypeName());
        priceLabel1.setText("$" + String.valueOf(Math.round(room1.getPrice())));
        capacityLabel1.setText("Capacity: " + String.valueOf(room1.getCapacity()));
        descriptionLabel1.setText(room1.getDescription());
        room1Vbox.setOnMouseClicked(event -> {
            openReservationConfirmation(hotel, room1);
        });

        //Room 2
        roomLabel2.setText(room2.getTypeName());
        priceLabel2.setText("$" + String.valueOf(Math.round(room2.getPrice())));
        capacityLabel2.setText("Capacity: " + String.valueOf(room2.getCapacity()));
        descriptionLabel2.setText(room2.getDescription());
        room2Vbox.setOnMouseClicked(event -> {
            openReservationConfirmation(hotel, room2);
        });

        //Room 3
        roomLabel3.setText(room3.getTypeName());
        priceLabel3.setText("$" + String.valueOf(Math.round(room3.getPrice())));
        capacityLabel3.setText("Capacity: " + String.valueOf(room3.getCapacity()));
        descriptionLabel3.setText(room3.getDescription());
        room3Vbox.setOnMouseClicked(event -> {
            openReservationConfirmation(hotel, room3);
        });
    }

    @FXML
    private void openReservationConfirmation(Hotel hotel, Room room) {

        try {
            // Load the new FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/booking/fxml/roomSelection.fxml"));
            Parent root = loader.load();

            // Get the controller of the new scene
            BookingConfirmController BookingConfirmController = loader.getController();

            // Inject the selected hotel
            BookingConfirmController.setHotelRoom(hotel, room);

            // Create and show the new stage
            Stage bookingConfirmStage = (Stage) backButton.getScene().getWindow();
            bookingConfirmStage.setScene(new Scene(root));
            bookingConfirmStage.show();
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
    private void handleHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/initial.fxml"));
        System.out.println("Loading initial.fxml");
        stage = (Stage) backButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
