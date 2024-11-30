package booking.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.FindIterable;
import javafx.fxml.FXMLLoader;
import org.bson.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.conversions.Bson;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


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
    private Button searchButton;
    @FXML
    private Button signinButton;

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

    public void handleSignInAction(ActionEvent event) {
        try{
            // Loads Sign In Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/booking/fxml/@signInPage.fxml"));
            System.out.println("Loading SignInPage.fxml");
            Parent root = loader.load();
            System.out.println("SignInPage.fxml loaded successfully");

            // Creates a new STage for Sign-In
            Stage stage = new Stage();
            stage.setTitle("Sign In");
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("Sign-In stage displayed");
        }   catch (Exception e) {
            e.printStackTrace(); //used for debugging
        }
    }


}
