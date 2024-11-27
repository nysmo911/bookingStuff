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
 * @version 1.0
 **/
public class HotelDAO implements GenericDAO<Hotel> {

    //Private instance of db (from DbConnection)
    private MongoDatabase db = getInstance().getDatabase();
    //Private collection
    private MongoCollection<Document> collection = db.getCollection("hotels");


    @Override
    public void add(Hotel hotel) {
        //Check to see proposed addition to database is already in the database by calling getMethod
        if (this.get(hotel.getName()) != null) {
            System.out.println("Hotel already exists");
        }

        //Otherwise, add hotel to the database
        collection.insertOne( new Document()
                .append("id", new Object())
                .append("name", hotel.getName())
                .append("city", hotel.getCity())
                .append("state", hotel.getState())
                .append("number_of_rooms", hotel.getNumOfAvailableRooms())
                .append("room_types", hotel.getRooms())
        );


    }

    @Override
    public Hotel get(String hotelName) {;
        //Create Projections
        Bson projection = Projections.fields(
                Projections.include("name" , "city", "state"),
                Projections.excludeId()
        );

        //Execute the query
       Document searchResult = collection.find(eq("name", hotelName)).projection(projection).first();

        //return null if no Hotel was found
        if (searchResult == null) {
          return null;
        }
         //else return a new hotel object
        else {
            String name = searchResult.getString("name");
            String city = searchResult.getString("city");
            String state = searchResult.getString("state");
            int number_of_rooms = searchResult.getInteger("number_of_rooms");
            List<Room> rooms = new ArrayList<>(); //adjust this later;
            return new Hotel(name, city, state, number_of_rooms, rooms);
        }

    }

   // @Override
    //public List<Hotel> getAll() {
        //complete later
   // }

    @Override
    public void update(Hotel hotel) {
        //find hotel by name in database using this.getMethod
        //replace with new hotel

    }

    @Override
    public void delete(String name) {
        //find hotel by name using this.getMethod
        //replace hotel

    }
}
