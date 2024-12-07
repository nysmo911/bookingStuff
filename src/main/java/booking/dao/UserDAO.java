package booking.dao;

import booking.model.UserProfile;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static booking.util.DbConnection.getInstance;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDAO implements GenericDAO<UserProfile>{

    //Private instance of db
    private MongoDatabase db = getInstance().getDatabase();
    //Private user collection
    final private MongoCollection<Document> collection = db.getCollection("users");
    private static final Set<String> validFieldNames = Set.of("first_name", "last_name", "email", "phone", "username", "password", "reservation_history");

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

    /**
     * Overloaded get method to query the database for an entry with a matching first and last name and return it as
     * a UserProfile object
     * @param firstName
     * @param lastName
     * @return
     */
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

    /**
     * Queries the database for a document with matching first and last name fields and returns the ID of that Document
     * @param firstName
     * @param lastName
     * @return
     * @param <Thing>
     */
    public <Thing> Thing getID(String firstName, String lastName){

        //Query for a document with a matching first and last name
        Bson query = Filters.and(
                Filters.eq("first_name", firstName),
                Filters.eq("last_name", lastName)
        );
        Document queryDoc = collection.find(query).first();

        if(queryDoc == null) {
            return null;
        }
        Object id = queryDoc.get("_id");
        return (Thing)id;

    }

    @Override
    public <Thing> void update(String id, String fieldName, Thing fieldValue) throws IllegalArgumentException {
        //Validate fieldName input
        if(!validFieldNames.contains(fieldName)) {
            throw new IllegalArgumentException("Invalid fieldName. Please pass one of the following: " + String.join(", ", validFieldNames));
        }

        //Create filter and update
        Document filter = new Document("_id", new ObjectId(id));
        Document update = new Document("$set", new Document(fieldName, fieldValue));
        try {
            collection.updateOne(filter, update);
        } catch(Exception e) {
            e.printStackTrace();
        }






    }

    public <Thing> void update(String firstName, String lastName, String fieldName, Thing fieldValue) throws IllegalArgumentException {
        //Ensure fieldName is a valid field
    }

    @Override
    public void delete(String name) {

    }

}
