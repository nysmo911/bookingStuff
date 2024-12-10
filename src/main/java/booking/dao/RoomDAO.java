package booking.dao;

import booking.model.Room;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static booking.util.DbConnection.getInstance;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * RoomDAO implements GenericDAO as an abstraction between the Room Class and database operations
 *
 * @author Brandon Brenes
 * @date   12/2/2024
 * @version 1.0
 **/
public class RoomDAO implements GenericDAO<Room> {

    //Private instance of db (from DbConnection)
    private MongoDatabase db = getInstance().getDatabase();
    //Private room collection
    final private MongoCollection<Document> collection = db.getCollection("rooms");
    private static final Set<String> validFieldNames = Set.of("name", "price", "description", "capacity", "isAvailable");


    /**
     * Translates a Room object into an acceptable format and inserts that into the database. This add method will not reference a hotel.
     * @param room
     */
    @Override
    public void add(Room room) {
        //Create ID
        long ObjectIDCount = collection.countDocuments();
        long nextObjectID = ObjectIDCount + 1;

        //Add Room to collection
        try {
            collection.insertOne(new Document()
                    .append("_id", new Document("_id", nextObjectID))
                    .append("name", room.getTypeName())
                    .append("price", room.getPrice())
                    .append("description", room.getDescription())
                    .append("capacity", room.getCapacity())
                    .append("isAvailable", room.isAvailable())
                    .append("hotelID", null)

            );
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Overloaded method translates a Room object into an acceptable format and inserts that into the database with a reference to hotel.
     * @param room
     * @param hotelID
     */
    public <Thing> void add(Room room, Thing hotelID){
        //Create ID
        long ObjectIDCount = collection.countDocuments();
        ObjectIDCount++;

        //Add to collection
        try {
            collection.insertOne(new Document()
                    .append("_id", ObjectIDCount)
                    .append("name", room.getTypeName())
                    .append("price", room.getPrice())
                    .append("description", room.getDescription())
                    .append("capacity", room.getCapacity())
                    .append("isAvailable", room.isAvailable())
                    .append("hotelID", hotelID)

            );
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Queries the database for the document with the passed parameter and returns a Room Object of that item
     * @param id
     * @return Room
     */
    @Override
    public Room get(String id) {
        //Convert to Long
        long roomID = Long.parseLong(id);

        //Execute the query
        Document searchResult = collection.find(eq("_id", roomID)).first();

                if (searchResult == null) { return null; }

                try {
                    String name = searchResult.getString("name");
                    String description = searchResult.getString("description");
                    Boolean availability = searchResult.getBoolean("isAvailable");
                    int capacity = searchResult.getInteger("capacity");
                    Double price = searchResult.getDouble("price");
                    return new Room(name, description, availability, price, capacity);

                } catch (Exception e) {
                       e.printStackTrace();
                }
                return null;
    }

    /**
     * Retrieve all Room documents in the form of a List of Room objects
     * @return List
     */
    public List<Room> getAll() {
        //Get all documents
        FindIterable<Document> roomDocuments = collection.find();

        //Convert documents into Room objects
        List<Room> roomObjects = new ArrayList<>();
        try {
            for (Document roomDocument : roomDocuments) {
                String name = roomDocument.getString("name");
                String description = roomDocument.getString("description");
                Boolean availability = roomDocument.getBoolean("isAvailable");
                int capacity = roomDocument.getInteger("capacity");
                Double price = roomDocument.getDouble("price");
                roomObjects.add(new Room(name, description, availability, price, capacity));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return roomObjects;
    }


    /**
     * Searches for a Room document with a matching name and returns the ID in the form of an object
     * @param roomName
     * @param hotelID
     * @return Thing
     */
    public <Thing> Thing getID(String roomName, Long hotelID) {
        //Query using passed parameter
        Document queryDoc = collection.find(and(eq("name", roomName), eq("hotelID", hotelID))).first();

        System.out.println("queryDoc variable from getIDqueryDoc " + queryDoc);
        //Check if null
        if (queryDoc == null) {
            return null;
        }
        //Return resulting Object
        Object result = queryDoc.get("_id");
        return (Thing) result;


    }


    /**
     * Updates a single field, specified by the fieldName parameter, with the fieldValue parameter
     * fieldName (fieldValue type) must be one of the following:(String) name, (Double) price, (String) description,(int) capacity, or (Boolean) isAvailable
     * @param id
     * @param fieldName
     * @param fieldValue
     */
    @Override
    public <Thing> void update(String id, String fieldName, Thing fieldValue) throws IllegalArgumentException {
        //Validate Input
        if (!validFieldNames.contains(fieldName)) {
            throw new IllegalArgumentException("Invalid fieldName. Please pass one of the following: " + String.join(", ", validFieldNames));
        }
        //Convert to long
        long longID = Long.parseLong(id);

        //Create filter and Update
        Document filter = new Document("_id", longID);
        Document update = new Document("$set", new Document(fieldName, fieldValue));
        try {
            collection.updateOne(filter, update);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Check if availability field was affected and update the associated hotel
        if (Objects.equals(fieldName, "isAvailable")) {
            //Get hotel entry that contains this room
            HotelDAO hotelDAO = new HotelDAO();
            String strID = id.toString();
            String hotelName = hotelDAO.getMatch("room_references", strID);

            //Update Hotel availability field
            int newAvailability = (Boolean) fieldValue ? 1 : -1;
            hotelDAO.update(hotelName, "number_of_available_rooms", newAvailability);


        }
    }

    /**
     * Replaces database entry matching the name of the passed Room object, with the passed Hotel object.
     * 'typeName' in Room must match the name of an existing database item
     * @param room
     * @param hotelID
     */
    public <Thing> void replace(Room room, Thing hotelID) {
        //Create ID
        long ObjectIDCount = collection.countDocuments();
        long nextObjectID = ObjectIDCount + 1;
        Document docRoomID = new Document("_id", nextObjectID);

        //Create updated Document to replace current room
        Document updatedDoc = new Document()
                .append("_id", docRoomID)
                .append("name", room.getTypeName())
                .append("price", room.getPrice())
                .append("description", room.getDescription())
                .append("capacity", room.getCapacity())
                .append("isAvailable", room.isAvailable())
                .append("hotelID", hotelID)
        ;

        //Execute Query
        try {
            collection.replaceOne(eq("_id", docRoomID), updatedDoc);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes first database entry with a name that matches the string parameter
     * @param typeName
     */
    @Override
    public void delete(String typeName) {
        try {
            collection.deleteOne(eq("name", typeName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

