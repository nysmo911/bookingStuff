package booking.controller;

import booking.dao.HotelDAO;
import booking.model.Hotel;
import booking.util.HotelSearchModel;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.FindIterable;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.bson.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.conversions.Bson;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<HotelSearchModel> hotelTableView;
    @FXML
    private TableColumn<HotelSearchModel, String> hotelColumn;
    @FXML
    private TableColumn<HotelSearchModel, String> cityColumn;
    @FXML
    private TableColumn<HotelSearchModel, String> stateColumn;

    ObservableList<HotelSearchModel> hotelSearchModelObservableList = FXCollections.observableArrayList();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String loggedInUser = null;

    public void initialize() {
        refreshUI();
       /* MongoDatabase database = getInstance().getDatabase();
        String hotelViewQuery = "";
        try{



            while (queryOutput.next()){
                hotelSearchModelObservableList.add(new HotelSearchModel());
            }


        } catch (Exception e) {

        } */
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

    /**
     *
     * @param event event parameter that is passed from the fxml file
     */
    public void submit(ActionEvent event) {
        //Create instance of the database
        MongoDatabase database = getInstance().getDatabase();

        //Get user input from TextField
        String cityName = searchTextField.getText();

        //Get hotel collection from mongoDB and filter query
        MongoCollection<Document> collection = database.getCollection("hotels");
        Bson filter = new Document("city", cityName);
        Bson projection = Projections.fields(
                Projections.include("name" , "city", "state"),
                Projections.excludeId()
        );

        //Execute the query
        FindIterable<Document> searchResult = collection.find(filter).projection(projection);

        //Print result if city is found in the database
        if (searchResult != null) {
            for (Document doc : searchResult) {
                searchTextField.setText(doc.toJson());
            }
        } else {
            searchTextField.setText("Awww we don't support that city yet :(....but maybe one day!");
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
}
