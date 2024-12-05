package booking.dao;

import booking.model.Hotel;
import booking.model.Room;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import static booking.util.DbConnection.getInstance;
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
    //Private collection
    final private MongoCollection<Document> collection = db.getCollection("rooms");

    /**
     * Translates a Room object into an acceptable format and inserts that into the database
     * @param room
     */
    @Override
    public void add(Room room) {
        //Insert Room into Database
        try {
            collection.insertOne(new Document()
                    .append("name", room.getTypeName())
                    .append("price", room.getPrice())
                    .append("description", room.getDescription())
                    .append("capacity", room.getCapacity())
                    .append("isAvailable", room.isAvailable())

            );
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for a Room document with a matching name and returns the ID in the form of an object
     * @param roomName
     * @return Object
     */
    public Object getRoomID(String roomName) {
        //Query using passed parameter
        Document queryDoc = collection.find(eq("name", roomName)).first();

        //Check if null
        if (queryDoc == null) {
            return null;
        }
        //Return resulting Object
        Object result = queryDoc.get("_id");
        return result;


    }

    /**
     * Queries the database for the first item with a name matching the passed parameter and returns a Room Object of that item
     * @param roomName
     * @return Room
     */
    @Override
    public Room get(String roomName) {
        //Create Projections
        Bson projection = Projections.fields(
                Projections.include("name" , "description", "price", "capacity", "isAvailable"),
                Projections.excludeId()
               );

        //Execute the query
        Document searchResult = collection.find(eq("name", roomName)).projection(projection).first();

                if (searchResult == null) {
                    return null;
                }
                try {
                    String name = searchResult.getString("name");
                    String description = searchResult.getString("description");
                    Boolean availability = searchResult.getBoolean("isAvailable");
                    int capacity = searchResult.getInteger("capacity");
                    Double price = searchResult.getDouble("price");
                    return new Room(name, description, availability, price, capacity);
                    }
                catch (Exception e) {
                       e.printStackTrace();
                }
                return null;
    }

    /**
     * Updates a single field, specified by the fieldName parameter, with the fieldValue parameter
     * fieldName (fieldValue type) must be one of the following:(String) name, (Double) price, (String) description,(int) capacity, or (Boolean) isAvailable
     * @param room
     * @param fieldName
     * @param fieldValue
     */
    @Override
    public <Thing> void update(Room room, String fieldName, Thing fieldValue) throws IllegalArgumentException {
        //Ensure fieldName is a valid field
        if (fieldName != "name" && fieldName != "description" && fieldName != "price" && fieldName != "capacity" && fieldName != "isAvailable") {
            throw new IllegalArgumentException("Invalid Input for fieldName. Please pass one of the following: name, name, price, description, capacity, or isAvailable");
        }

        //Get roomID
        Object roomID = this.getRoomID(room.getTypeName());

        //Create filter and update
        Document filter = new Document("_id", roomID);
        Document update = new Document("$set", new Document(fieldName,fieldValue));

        //Execute update
        try{
            collection.updateOne(filter, update);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Check if availability field was affected and update the associated hotel
       // if (fieldName == "isAvailable"){
            //Update Room Reference field in respective Hotel Document
            //collection.updateOne()
        }

    /**
     * Replaces database entry matching the name of the passed Room object, with the passed Hotel object.
     * 'typeName' in Room must match the name of an existing database item
     *
     * @param room
     */
    public void replace(Room room) {
        //Create updated Document to replace current room
        Document updatedDoc = new Document()
                .append("name", room.getTypeName())
                .append("price", room.getPrice())
                .append("description", room.getDescription())
                .append("capacity", room.getCapacity())
                .append("isAvailable", room.isAvailable()
                );

        //Execute Query
        try {
            collection.replaceOne(eq("name", room.getTypeName()), updatedDoc);
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
        //Execute deletion
        try {
            collection.deleteOne(eq("name", typeName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

