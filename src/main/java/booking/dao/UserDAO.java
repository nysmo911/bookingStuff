package booking.dao;

import booking.model.UserProfile;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static booking.util.DbConnection.getInstance;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements GenericDAO<UserProfile>{

    //Private instance of db
    private MongoDatabase db = getInstance().getDatabase();
    //Private user collection
    final private MongoCollection<Document> collection = db.getCollection("users");


    /**
     * Translates a User object into an acceptable format and inserts that into the database
     * @param user
     */
    @Override
    public void add(UserProfile user) {
        try {
            collection.insertOne(new Document()
                    .append("first_name", user.getFName())
                    .append("last_name", user.getLName())
                    .append("email", user.getEmail())
                    .append("phone", user.getPhone())
                    .append("username", user.getUserName())
                    .append("password", user.getPassword())
                    .append("reservation_history", user.getReservationHistory())

            );
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Queries the database for an entry with a matching username and returns it as a UserProfile Object
     * @param username
     * @return
     */
    @Override
    public UserProfile get(String username) {

        Document searchResult = collection.find(new Document("username", username)).first();
        if(searchResult == null) {
            return null;
        }
        try {
            String firstName = searchResult.getString("first_name");
            String lastName = searchResult.getString("last_name");
            String email = searchResult.getString("email");
            String phone = searchResult.getString("phone");
            String password = searchResult.getString("password");
            //Later on create a reservation class and update this to process reservations by reference
            List<String> reservationHistory = searchResult.getList("reservation_history", String.class);

            return new UserProfile(firstName, lastName, email, phone, username, password, reservationHistory);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserProfile get(String firstName, String lastName){
        //Query for a document that has a matching first and last name
        Bson query = Filters.and(
                Filters.eq("first_name", firstName),
                Filters.eq("last_name", lastName)
        );
        Document searchResult = collection.find(query).first();

        try {
            String username = searchResult.getString("username");
            String email = searchResult.getString("email");
            String phone = searchResult.getString("phone");
            String password = searchResult.getString("password");
            //Later on create a reservation class and update this to process reservations by reference
            List<String> reservationHistory = searchResult.getList("reservation_history", String.class);

            return new UserProfile(firstName, lastName, email, phone, username, password, reservationHistory);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public <Thing> void update(String name, String fieldName, Thing fieldValue) {

    }

    public void delete(String name) {

    }

}
