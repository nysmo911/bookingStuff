package booking.dao;

import booking.model.UserProfile;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static booking.util.DbConnection.getInstance;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * UserDAO implements GenericDAO as an abstraction between the UserProfile class and database operations
 *
 * @author Brandon Brenes
 * @date 12/05/2024
 * @version 1.0
 */
public class UserDAO implements GenericDAO<UserProfile>{

    private MongoDatabase db = getInstance().getDatabase();
    final private MongoCollection<Document> collection = db.getCollection("users");
    private static final Set<String> validFieldNames = Set.of("first_name", "last_name", "email", "username", "password", "reservation_history");

    /**
     * Translates a User object into an acceptable format and inserts that into the database
     *
     * @param user
     */
    @Override
    public void add(UserProfile user) {

        try {
            collection.insertOne(new Document()
                    .append("first_name", user.getFName())
                    .append("last_name", user.getLName())
                    .append("email", user.getEmail())
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
     *
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
            String password = searchResult.getString("password");
            //Later on create a reservation class and update this to process reservations by reference
            List<String> reservationHistory = searchResult.getList("reservation_history", String.class);

            return new UserProfile(firstName, lastName, email, username, password, reservationHistory);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Overloaded get method to query the database for an entry with a matching first and last name and return it as
     * a UserProfile object
     *
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
            String password = searchResult.getString("password");
            //Later on create a reservation class and update this to process reservations by reference
            List<String> reservationHistory = searchResult.getList("reservation_history", String.class);

            return new UserProfile(firstName, lastName, email, username, password, reservationHistory);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Queries the database for a document with matching username and returns the ID of that Document
     *
     * @param username
     * @return
     * @param <Thing>
     */
    public <Thing> Thing getID(String username){

        Document queryDoc = collection.find(eq("username", username)).first();

        if(queryDoc == null) {
            return null;
        }
        Object id = queryDoc.get("_id");
        return (Thing)id;

    }

    /**
     * Queries the database by username for a specified field and returns the value of that field
     *
     * @param username
     * @param fieldName
     * @return
     * @param <Thing>
     */
    public <Thing> Thing getValue (String username, String fieldName){
        //Validate fieldName input
        if(!validFieldNames.contains(fieldName)) {
            throw new IllegalArgumentException("Invalid fieldName. Please pass one of the following: " + String.join(", ", validFieldNames));
        }

        //Execute query and return
        Document queryDoc = collection.find(eq("username", username)).first();
        if(queryDoc == null) {
            return null;
        }
        switch (fieldName) {
            case "first_name":
                return (Thing)queryDoc.get("first_name");
            case "last_name":
                return (Thing)queryDoc.get("last_name");
            case "email":
                return (Thing)queryDoc.get("email");
            case "password":
                return (Thing)queryDoc.get("password");
            case "reservation_history":
                return (Thing)queryDoc.get("reservation_history");
            default:
                    return null;
        }

    }

    /**
     * Updates a single field of the document with the matching passed parameter, username.
     *
     * @param username
     * @param fieldName
     * @param fieldValue
     * @param <Thing>
     * @throws IllegalArgumentException
     */
    @Override
    public <Thing> void update(String username, String fieldName, Thing fieldValue) throws IllegalArgumentException {
        //Validate fieldName input
        if(!validFieldNames.contains(fieldName)) {
            throw new IllegalArgumentException("Invalid fieldName. Please pass one of the following: " + String.join(", ", validFieldNames));
        }
        //Get userID
        Object userID = this.getID(username);

        //Create filter and update
        Document filter = new Document("_id", userID);
        Document update = new Document("$set", new Document(fieldName, fieldValue));
        try {
            collection.updateOne(filter, update);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes the first document with a matching username
     *
     * @param username
     */
    @Override
    public void delete(String username) {
        //Execute deletion
        try {
            collection.deleteOne(eq("username", username));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
