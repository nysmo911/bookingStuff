package booking.dao;

import booking.model.Hotel;
import booking.model.Room;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.List;
import static booking.util.DbConnection.getInstance;
import static com.mongodb.client.model.Filters.eq;

/**
 * HotelDAO implements GenericDAO as an abstraction between the Hotel Class and database operations
 *
 * @author Brandon Brenes
 * @version 1.0
 **/
public class HotelDAO implements GenericDAO<Hotel> {

    //Private instance of db (from DbConnection)
    private MongoDatabase db = getInstance().getDatabase();
    //Private collection
    final private MongoCollection<Document> collection = db.getCollection("hotels");

    /**
     * Receives a Hotel object as an argument and adds to the database if duplicate isn't found.
     * @param hotel
     * @return Boolean
     */
    @Override
    public void add(Hotel hotel) {
        //Need dedupe
        //Update to create reference to room

        try {
            collection.insertOne( new Document()

                    .append("name", hotel.getName())
                    .append("city", hotel.getCity())
                    .append("state", hotel.getState())
                    .append("number_of_rooms", hotel.getNumOfAvailableRooms())
                    .append("room_types", hotel.getRooms())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Searches the Database for the first database entry matching the passed string. Returns the found entry in the form of a Hotel object.
     * @param hotelName
     * @return Hotel
     */
    @Override
    public Hotel get(String hotelName) {
        //Create Projections
        Bson projection = Projections.fields(
                Projections.include("name" , "city", "state"),
                Projections.excludeId()
        );

        //Execute the query
       Document searchResult = collection.find(eq("name", hotelName)).projection(projection).first();
            try {
            String name = searchResult.getString("name");
            String city = searchResult.getString("city");
            String state = searchResult.getString("state");
            //int number_of_rooms = searchResult.getInteger("number_of_rooms"); //check for null values
            List<Room> rooms = searchResult.getList("room_types", Room.class);
            return new Hotel(name, city, state, 5, rooms);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;

    }

    /**
     * Searches for a hotel document with a matching name and returns the ID
     *
     * @param hotelName
     * @return String
     */
    public String getID(String hotelName) {
        //Query using passed parameter
        Document queryDoc = collection.find(eq("name", hotelName)).first();

        // Check if null
        if (queryDoc == null) {
            return null;
        }
        // Assign resulting Document to queryDoc and extract ID
            Object objectID = queryDoc.get("_id");
            String stringID = objectID.toString();
            return stringID;



    }



        // @Override
    //public List<Hotel> getAll() {
        //complete later
   // }

    /**
     * Updates database entry matching the name of the passed Hotel object, with the passed Hotel object.
     * @param hotel
     * @return void
     */
    @Override
    public void update(Hotel hotel) {
        Document doc = new Document(new Document()
                .append("name", hotel.getName())
                .append("city", hotel.getCity())
                .append("state", hotel.getState())
                .append("number_of_rooms", hotel.getNumOfAvailableRooms())
                .append("room_types", hotel.getRooms())
        );
        try {
            collection.replaceOne(eq("name", hotel.getName()), doc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Deletes first database entry with a name that matches the string parameter
     * @param name
     */
    @Override
    public void delete(String name) {
        try {
            collection.deleteOne(eq("name", name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
