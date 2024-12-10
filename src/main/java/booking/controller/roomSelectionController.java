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
import org.bson.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.bson.conversions.Bson;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static booking.application.Main.scene;

public class roomSelectionController {

    private RoomDAO roomDAO = new RoomDAO(); // Presuming you use this later for fetching rooms.
    private Hotel hotel = new Hotel();


    @FXML
    private Label hotelNameLabel;


    public void setHotel(Hotel h) {
        if(h == null){
            hotelNameLabel.setText("");
            return;
        }
        //Set name label
        hotelNameLabel.setText(h.getName());

        //Get hotel with Room references
        //

       // List<Object> roomReferences = new ArrayList<>();
       // List<Room>
        // room
        //Set labels
    }


}
