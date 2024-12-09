package booking.controller;

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

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
        hotelNameLabel.setText(hotelName);
    }
}
