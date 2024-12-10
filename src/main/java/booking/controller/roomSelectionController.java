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

        //Room 1
        roomLabel1.setText(room1.getTypeName());
        priceLabel1.setText("$" + String.valueOf(Math.round(room1.getPrice())));
        capacityLabel1.setText("Capacity: " + String.valueOf(room1.getCapacity()));
        descriptionLabel1.setText(room1.getDescription());

        //Room 2
        roomLabel2.setText(room2.getTypeName());
        priceLabel2.setText("$" + String.valueOf(Math.round(room2.getPrice())));
        capacityLabel2.setText("Capacity: " + String.valueOf(room2.getCapacity()));
        descriptionLabel2.setText(room2.getDescription());

        //Room 3
        roomLabel3.setText(room3.getTypeName());
        priceLabel3.setText("$" + String.valueOf(Math.round(room3.getPrice())));
        capacityLabel3.setText("Capacity: " + String.valueOf(room3.getCapacity()));
        descriptionLabel3.setText(room3.getDescription());
    }


}
