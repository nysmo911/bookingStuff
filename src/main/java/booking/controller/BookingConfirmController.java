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
import java.util.ArrayList;
import java.util.List;

public class BookingConfirmController {
    private RoomDAO roomDAO = new RoomDAO(); // Presuming you use this later for fetching rooms.
    private HotelDAO hotelDAO = new HotelDAO();
    private Hotel hotel = new Hotel();
    private Room room = new Room();
    private String hotelName;

    public void setHotelRoom(Hotel h, Room r) {
        if (r == null) {
            roomNameLabel.setText("");
            return;
        }
        if (h == null) {
            hotelNameLabel.setText("");
            return;
        }

        //Set Hotel name label
        hotelNameLabel.setText(h.getName());

        //Set Room name label
        roomNameLabel.setText(r.getTypeName());

        //Get hotel with Room references
        System.out.println("hotel" + h.getName());

        roomPriceLabel.setText("$" + String.valueOf(Math.round(room.getPrice())));
    }
}
