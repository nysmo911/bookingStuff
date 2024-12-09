package booking.controller;

import booking.model.Hotel;
import javafx.stage.Stage;
import javafx.scene.Parent;
import booking.dao.RoomDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class roomSelectionController {

    RoomDAO roomDAO = new RoomDAO();
    private String hotelName;
    @FXML
    private Label hotelNameLabel;

    private void initialize(){
        setHotelName(hotelName);
    }

    public void setHotelName(Hotel hotelName) {
        this.hotelName = hotelName;
        hotelNameLabel.setText(hotelName);
        System.out.println(hotelName);
    }
}
