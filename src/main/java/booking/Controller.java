package booking;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.conversions.Bson;


import static booking.dbConnection.getInstance;
import static com.mongodb.client.model.Filters.eq;


public class Controller {

    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;

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
        //Document searchResult = collection.find(eq("city", cityName)).first();

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


}
