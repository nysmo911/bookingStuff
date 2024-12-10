package booking.dao;

import booking.model.Hotel;
import booking.model.Room;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

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

    private MongoDatabase db = getInstance().getDatabase();
    final private  MongoCollection<Document> collection = db.getCollection("hotels");
    private RoomDAO roomDAO = new RoomDAO();

    /**
     * Receives a Hotel object as an argument and adds to the database if duplicate isn't found.
     * @param hotel
     */
    @Override
    public void add(Hotel hotel){
        //Initialize List of Room Objects, and List of Document Objects
        List<Room> hotelRooms = hotel.getRooms();
        List<Document> roomReferences = new ArrayList<>();

        //Create ID
        long objectIDCount = collection.countDocuments();
        objectIDCount++;

        //Using RoomId, create reference object for each room
        for (Room room : hotelRooms) {
            roomDAO.add(room, objectIDCount);
            Object roomRefID = roomDAO.getID(room.getTypeName(),objectIDCount);
            roomReferences.add(new Document("RoomID", roomRefID)
                    .append("name", room.getTypeName()));
        }

        //Create new Hotel document and add to database
        try {
            collection.insertOne( new Document()
                    .append("_id", objectIDCount)
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
     * Searches the Database for the first document matching the passed string. Returns the found document in the form of a Hotel object.
     * @param hotelName
     * @return Hotel
     */
    @Override
    public Hotel get(String hotelName) {
       //Execute the query
       Document searchResult = collection.find(eq("name", hotelName)).first();

            try {
            String name = searchResult.getString("name");
            String city = searchResult.getString("city");
            String state = searchResult.getString("state");
            int number_of_available_rooms = searchResult.getInteger("number_of_available_rooms"); //need to check for null values
            List<Document> roomReferences = searchResult.getList("room_references", Document.class);
            List<Room> resultRooms = new ArrayList<>();

            //Use Room references to query room collection
            for (Document roomReference : roomReferences) {
                String roomRefID = roomReference.getLong("RoomID").toString();
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
     * Retrieves all hotel documents as a List of Hotel objects. Parameter determines if rooms will be returned as well
     * @param returnRooms
     * @return List
     */
    public List<Hotel> getAll(Boolean returnRooms) {
        //Query for all documents
        FindIterable<Document> hotelsDocuments = collection.find();
        List<Hotel> hotelObjects = new ArrayList<>();

       //Get hotelName, city, and state
        for (Document hotelDocument : hotelsDocuments) {
            String name = hotelDocument.getString("name");
            String city = hotelDocument.getString("city");
            String state = hotelDocument.getString("state");


            //Return list of
            if (returnRooms) {
                List<Room> resultRooms = new ArrayList<>();
                int number_of_available_rooms = hotelDocument.getInteger("number_of_available_rooms"); //need to check for null values
                List<Document> roomReferences = hotelDocument.getList("room_references", Document.class);
                 //Add rooms to List
                for (Document roomReference : roomReferences) {
                     Long roomRefID = roomReference.getLong("RoomID");
                     String roomRefIDString = roomRefID.toString();
                     Room room = roomDAO.get(roomRefIDString);
                     resultRooms.add(roomDAO.get(roomRefIDString)); }
                 //Add to hotelObjects list with rooms
                hotelObjects.add(new Hotel(name, city, state, number_of_available_rooms, resultRooms));
             } else {
                //Add to the list of objects without rooms
                hotelObjects.add(new Hotel(name, city, state));
            }
        }

        return hotelObjects;
    }

    /**
     * Searches for a hotel document with a matching name and returns the ID
     * @param hotelName
     * @return Object
     */
    public Object getID(String hotelName) {

        Document queryDoc = collection.find(eq("name", hotelName)).first();

        if (queryDoc != null) {
            Object hotelID = queryDoc.get("_id");
            return hotelID;
        } else {
            return null;
        }
    }

    /**
     * Queries the database for a document with a matching parameter and returns the name of it
     * @param fieldName
     * @param fieldValue
     * @return String
     * @throws IllegalArgumentException
     */
    public String getMatch(String fieldName, String fieldValue) throws IllegalArgumentException {
        //Ensure fieldName is valid
        if (fieldName != "city" && fieldName != "state" && fieldName != "room_references" && fieldName != "number_of_available_rooms") {
            throw new IllegalArgumentException("Invalid Input for fieldName. Please pass one of the following: city, room_references, or number_of_available_rooms");
        }

        //Execute query and return
        Document queryDoc;
        String hotelName;
        switch (fieldName) {
            case null:
                return null;
            case "room_references":
                long fieldValueLong = Long.parseLong(fieldValue);
                Document query = new Document("room_references",
                        new Document("$elemMatch",
                                new Document("RoomID",
                                        (fieldValueLong))
                                )
                );

                // Find the first matching document
                Document resultDoc = collection.find(query).first();
                hotelName = resultDoc.getString("name");
                break;
            default:
                queryDoc = collection.find(eq(fieldName, fieldValue)).first();
                hotelName = queryDoc.getString("name");
        }
        return hotelName;

    }


    /**
     * Queries the database, by "name" for a specified field and returns the value
     * @param name
     * @param fieldName
     * @return Generic
     */
    public <Thing> Thing getValue(String name, String fieldName) throws IllegalArgumentException {
        //Ensure fieldName is valid
        if (fieldName != "city" && fieldName != "state" && fieldName != "room_references" && fieldName != "number_of_available_rooms") {
            throw new IllegalArgumentException("Invalid Input for fieldName. Please pass one of the following: city, room_references, or number_of_available_rooms");
        }

        //Execute query and return
        Document queryDoc = collection.find(eq("name", name)).first();
        if (queryDoc == null) {
            return null;
        }
        switch (fieldName) {
            case "city":
                return (Thing) queryDoc.getString("city");
            case "state":
                return (Thing) queryDoc.getString("state");
            case "room_references":
                List<Object> room_references = queryDoc.getList("room_references", Object.class);
                return (Thing) room_references;
            case "number_of_available_rooms":
                return (Thing) queryDoc.getInteger("number_of_available_rooms");
            default:
                return null;
        }
    }



    /**
     * Updates a single field, specified by the fieldName parameter, with the fieldValue parameter
     * fieldName (fieldValue type) must be one of the following: (String) name, (String) city, (String) state, (Integer) number_of_rooms, (List) room_references, or (Integer) number_of_available_rooms
     * @param hotelName
     * @param fieldName
     * @param fieldValue
     * @throws IllegalArgumentException
     */
    @Override
    public <Thing> void update(String hotelName, String fieldName, Thing fieldValue) throws IllegalArgumentException {
        //Ensure fieldName is a valid field
        if (fieldName != "name" && fieldName != "city" && fieldName != "state" && fieldName != "room_references" && fieldName != "number_of_available_rooms") {
            throw new IllegalArgumentException("Invalid Input for fieldName. Please pass one of the following: name, city, description, capacity, or isAvailable");
        }
        //Get roomID
        Object hotelID = this.getID(hotelName);


        //Create filter and update
        Document filter = new Document("_id", hotelID);
        Document update;
        if (fieldValue instanceof Number && (fieldName.equals("number_of_available_rooms"))) {
            update = new Document("$inc", new Document(fieldName, fieldValue));
        } else {
            update = new Document("$set", new Document(fieldName, fieldValue));
        }

        //Execute update
        try{
            collection.updateOne(filter, update);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * Replaces database entry matching the name of the passed Hotel object, with the passed Hotel object.
     * @param hotel
     * @return void
     */
    public void replace(Hotel hotel) {
        //Create updated Document from Hotel Object
        Document doc = new Document()
                .append("name", hotel.getName())
                .append("city", hotel.getCity())
                .append("state", hotel.getState())
                .append("number_of_rooms", hotel.getRooms().size())
                .append("number_of_available_rooms", hotel.getNumOfAvailableRooms())
                .append("room_references", hotel.getRooms()
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
