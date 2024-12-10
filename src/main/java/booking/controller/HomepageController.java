package booking.controller;

import booking.dao.HotelDAO;
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

import javafx.collections.ObservableList;

import static booking.util.DbConnection.getInstance;
import static com.mongodb.client.model.Filters.eq;

/**
 * The booking.controller.HomepageController class provides the functionality to the first page of our application.
 * @author Brandon Brenes
 * @version 1.0
 * @date 11/11/2024
 */

public class HomepageController {
    /**
     * Private TextField and button objects (imported from javafx.scene.control)
     */
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField searchTextField1;
    @FXML
    private Button signinButton;
    @FXML
    private AnchorPane loggedOutPane;
    @FXML
    private AnchorPane loggedInPane;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button dashboardButton;
    @FXML
    private TableView<Hotel> hotelSearchTable;
    @FXML
    private TableColumn<Hotel, String> hotelColumn;
    @FXML
    private TableColumn<Hotel, String> cityColumn;
    @FXML
    private TableColumn<Hotel, String> stateColumn;
    @FXML
    private TableView<Hotel> hotelSearchTable1;
    @FXML
    private TableColumn<Hotel, String> hotelColumn1;
    @FXML
    private TableColumn<Hotel, String> cityColumn1;
    @FXML
    private TableColumn<Hotel, String> stateColumn1;
    private String selectedHotelName;


    private Stage stage;
    private Scene scene;
    private Parent root;
    private String loggedInUser = null;

    public void initialize() {
        refreshUI();
        //Get all hotels from database
        HotelDAO HotelDAO = new HotelDAO();
        List<Hotel> db_hotels = HotelDAO.getAll(false);
        ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList();
        hotelObservableList.addAll(db_hotels);

        //Set Column names
        hotelColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        //Wrap with filter list to show filtered results
        FilteredList<Hotel> filteredHotels = new FilteredList<>(hotelObservableList, h -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> filteredHotels.setPredicate(Hotel -> {
                            if (newValue == null || newValue.isBlank()) {
                                return true;
                            }

                            String searchEntry = newValue.toLowerCase();
                            Boolean nameMatch = Hotel.getName().toLowerCase().contains(searchEntry);
                            Boolean cityMatch = Hotel.getCity().toLowerCase().contains(searchEntry);
                            Boolean stateMatch = Hotel.getState().toLowerCase().contains(searchEntry);

                            return nameMatch || stateMatch || cityMatch;
                            }));
        });

        //Sort Filtered List Sorted List doesn't filter ) fix later
        //SortedList<Hotel> sortedHotels = new SortedList<>(filteredHotels);
        //sortedHotels.comparatorProperty().bind(hotelSearchTable.comparatorProperty());
        hotelSearchTable.setItems(filteredHotels);
        setupRowClickHandler(hotelSearchTable);


        //Determine which table to set
        }

    public void refreshUI() {
        if (UserSession.getInstance().isLoggedIn()) {
            loggedOutPane.setVisible(false);
            loggedOutPane.setManaged(false);

            loggedInPane.setVisible(true);
            loggedInPane.setManaged(true);
            loggedInUser = UserSession.getInstance().getLoggedInUser();

            welcomeLabel.setText("Welcome, " + loggedInUser + "!");
        } else {
            loggedOutPane.setVisible(true);
            loggedOutPane.setManaged(true);

            loggedInPane.setVisible(false);
            loggedInPane.setManaged(false);
        }
    }

    public void handleSignInAction(ActionEvent event) throws IOException {
            // Loads Sign In Page
            root = FXMLLoader.load(getClass().getResource("/booking/fxml/signInPage.fxml"));
            System.out.println("Loading SignInPage.fxml");
            stage = (Stage) signinButton.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void handleDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/booking/fxml/userDashboard.fxml"));
        System.out.println("Loading userDashboard.fxml");
        stage = (Stage) dashboardButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void setupRowClickHandler(TableView<Hotel> tableView) {
        tableView.setRowFactory(tv -> {
            TableRow<Hotel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Hotel selectedHotel = row.getItem();
                    openHotelDetails(selectedHotel);
                    System.out.println("Selected Hotel: " + selectedHotel.getName());

                }
            });
            return row;
        });
    }

    @FXML
    private void openHotelDetails(Hotel hotel) {
        Hotel selectedHotel = hotelSearchTable.getSelectionModel().getSelectedItem();

        try {
            // Load the new FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/booking/fxml/roomSelection.fxml"));
            Parent root = loader.load();

            // Get the controller of the new scene
            roomSelectionController roomSelectioncontroller = loader.getController();

            // Inject the selected hotel
            roomSelectioncontroller.setHotel(selectedHotel);

            // Create and show the new stage
            Stage roomSelectionStage = (Stage) dashboardButton.getScene().getWindow();
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
}