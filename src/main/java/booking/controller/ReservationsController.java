package booking.controller;

import booking.dao.HotelDAO;
import booking.dao.ReservationDAO;
import booking.dao.RoomDAO;
import booking.dao.UserDAO;
import booking.model.Hotel;
import booking.model.Reservation;
import booking.model.UserProfile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ReservationsController {
    @FXML
    private ListView<Reservation> currentReservationsList;
    @FXML
    private ListView<Reservation> pastReservationsList;
    ReservationDAO reservationDAO = new ReservationDAO();
    UserDAO userDAO = new UserDAO();
    HotelDAO hotelDAO = new HotelDAO();
    RoomDAO roomDAO = new RoomDAO();

    @FXML
    public void initialize() {
        try {
            currentReservation(); // Call your reservation-loading logic here
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void currentReservation() throws IOException {
        //Get username
        String loggedInUser = UserSession.getInstance().getLoggedInUser();
        Long uid = userDAO.getID(loggedInUser);
        //Get all reservations with userID
        List<Reservation> reservations = reservationDAO.getByField("UserID",uid);

        //Get current reservations (have not happened yet)
        //List<Reservation> curRes = new ArrayList<>();
       // for (Reservation r : reservations) {
         //   if (r.getStartDate().isAfter(LocalDate.now())){
           //     curRes.add(r);
        //    }
      //  }
        //Set fields to curRes
        ObservableList<Reservation> reservationsObservableList = FXCollections.observableList(reservations);
        currentReservationsList.setItems(reservationsObservableList);

       //Display details
       currentReservationsList.setCellFactory(view -> new ListCell<Reservation>() {
            @Override
            protected void updateItem(Reservation res, boolean empty) {
                super.updateItem(res, empty);
                if(empty || res == null) {
                    setText(null);
                } else {
                    setText(res.getHotel().getName() + ": "+ res.getRoom().getTypeName()+" Start Date: " + res.getStartDate() + " - End Date" + res.getEndDate());
                }

            }
        });
    }
    @FXML
    private Button backButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void handleDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/userDashboard.fxml"));
        System.out.println("Loading userDashboard.fxml");
        stage = (Stage) backButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

