package booking.dao;

import booking.model.Hotel;
import booking.model.Room;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import static booking.util.DbConnection.getInstance;
import static com.mongodb.client.model.Filters.eq;

/**
 * HotelDAO implements GenericDAO as an abstraction between the Hotel Class and database operations
 *
 * @author Brandon Brenes
 * @date   11/26/2024
 * @version 1.0
 **/
public class HotelDAO implements GenericDAO<Hotel> {

    //Private instance of db (from DbConnection)
    private MongoDatabase db = getInstance().getDatabase();
    //Private collection
    final private MongoCollection<Document> collection = db.getCollection("hotels");
    RoomDAO roomDAO = new RoomDAO();

    /**
     * Receives a Hotel object as an argument and adds to the database if duplicate isn't found.
     * @param hotel
     */
    @Override
    public void add(Hotel hotel){
        //Need dedupe
        //Initialize List of Room Objects, and List of Document Objects
        List<Room> hotelRooms = hotel.getRooms();
        List<Document> roomReferences = new ArrayList<>();

        //Using RoomId, create reference object for each room
        for (Room room : hotelRooms) {
            roomDAO.add(room);
            Object roomRefID = roomDAO.getRoomID(room.getTypeName());
            roomReferences.add(new Document("RoomObjectID", roomRefID)
                    .append("name", room.getTypeName())
                    .append("isAvailable", room.isAvailable()));
        }

        //Create new Hotel document and add to database
        try {
            collection.insertOne( new Document()
                    .append("name", hotel.getName())
                    .append("city", hotel.getCity())
                    .append("state", hotel.getState())
                    .append("number_of_rooms", hotel.getRooms().size())
                    .append("number_of_available_rooms", hotel.getNumOfAvailableRooms())
                    .append("room_references", roomReferences)
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
                //Projections.include("name" , "city", "state"),
                Projections.excludeId()
        );

        //Execute the query
       Document searchResult = collection.find(eq("name", hotelName)).projection(projection).first();
            try {
            String name = searchResult.getString("name");
            String city = searchResult.getString("city");
            String state = searchResult.getString("state");
            int number_of_available_rooms = searchResult.getInteger("number_of_available_rooms"); //check for null values
            List<Document> roomReferences = searchResult.getList("room_references", Document.class);
            List<Room> resultRooms = new ArrayList<>();

            //Use Room references to query room collection
            for (Document roomReference : roomReferences) {
                String roomRefID = roomReference.getObjectId("RoomObjectID").toString();
                resultRooms.add(roomDAO.get(roomRefID));

            }

            return new Hotel(name, city, state, number_of_available_rooms, resultRooms);
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
     * @return Object
     */
    public Object getID(String hotelName) {
        //Query using passed parameter
        Document queryDoc = collection.find(eq("name", hotelName)).first();

        // Check if null
        if (queryDoc == null) {
            return null;
        }
        // return resulting Object
            Object hotelID = queryDoc.get("_id");
            return hotelID;



    }

    /**
     * Updates database entry matching the name of the passed Hotel object, with the passed Hotel object.
     * @param hotel
     * @return void
     */
    @Override
    public void update(Hotel hotel) {
        //Create updated Document from Hotel Object
        //Eventually create another update class so individual fields can be edited instead of the whole doc (take in an ID)
        Document doc = new Document()
                .append("name", hotel.getName())
                .append("city", hotel.getCity())
                .append("state", hotel.getState())
                .append("number_of_rooms", hotel.getRooms().size())
                .append("number_of_available_rooms", hotel.getNumOfAvailableRooms())
                .append("room_types", hotel.getRooms()
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
