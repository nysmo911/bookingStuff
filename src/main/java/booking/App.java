package booking;

import java.util.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static booking.dbConnection.getInstance;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;



public class App {

    public static void main(String[] args) {
        //Create instance of database connection
        MongoDatabase database = getInstance().getDatabase();

        //Gather user input
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to bookingStuff!\nPlease enter a city name to view our hotel selection!");
        String cityName = userInput.nextLine();

        //Search database for first hotel with matching city
        MongoCollection<Document> collection = database.getCollection("hotels");
        Document searchResult = collection.find(eq("city", cityName)).first();
        if (searchResult != null) {
            System.out.println(searchResult.toJson());
        } else {
            System.out.println("Awww we don't support that city yet :(....but maybe one day!");
        }

    }
}
