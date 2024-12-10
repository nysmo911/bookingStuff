package booking.controller;

import booking.application.Main;
import booking.dao.HotelDAO;
import booking.dao.RoomDAO;
import booking.model.Hotel;
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
    private Hotel hotel = new Hotel();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String loggedInUser = null;

    // Stores the name of the hotel to display.

    @FXML
    private Label hotelNameLabel;

    /**
     * Initialize method called automatically when the FXML is loaded.
     */
    public roomSelectionController(Hotel h) {
        this.hotel = h;
    }
    /*
    @FXML
    public void initialize() {
        if (hotelName != null) {
            hotelNameLabel.setText(hotelName);
        }
    }
    /*

    /**
     * Sets the hotel name and updates the label dynamically.
     *
     * @param hotel The `Hotel` object containing the name.
     */


    @FXML
    private void openRoomPage(Hotel selectedHotel) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/roomSelection.fxml"));
        System.out.println("Loading roomSelection.fxml");
        //stage = (Stage) dashboardButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        //scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /*
    public void setHotelName(Hotel hotel) {
        this.hotelName = hotel.getName(); // Assuming Hotel has a `getName()` method.
        if (hotelNameLabel != null) {
            hotelNameLabel.setText(hotelName);
        }
    }

     */
}
