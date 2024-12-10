package booking.dao;

import booking.model.Hotel;
import booking.model.Reservation;
import booking.model.Room;
import booking.model.UserProfile;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import static booking.util.DbConnection.getInstance;
import static com.mongodb.client.model.Filters.eq;

/**
 * ReservationDAO implements GenericDAO as an abstraction between the Hotel Class and database operations
 *
 * @author Brandon Brenes
 * @date 12/9/2024
 * @version 1.0
 */
public class ReservationDAO implements GenericDAO<Reservation> {


    private MongoDatabase db = getInstance().getDatabase();
    final private MongoCollection<Document> collection = db.getCollection("reservations");
    private static final Set<String> validFieldNames = Set.of("name", "price", "description", "capacity", "isAvailable");
    UserDAO userDAO = new UserDAO();
    HotelDAO hotelDAO = new HotelDAO();
    RoomDAO roomDAO = new RoomDAO();

    /**
     * Receives a Reservation object as an argument and adds to the database
     * @param resy
     */
    @Override
    public void add(Reservation resy){
        //Create ID
        long objectIDCount = collection.countDocuments();
        objectIDCount++;

        //Get User ID
        UserProfile user = resy.getUser();
        Long uid = userDAO.getID(user.getUserName());

        //Get Hotel ID
        Hotel hotel = resy.getHotel();
        Long hid = hotelDAO.getID(hotel.getName());

        //Get Room
        Room room = resy.getRoom();
        Long rid = roomDAO.getID(room.getTypeName(), hid);

        //Add reservation to collection
        try {
            collection.insertOne(new Document()
                    .append("_id", objectIDCount)
                    .append("start_date", resy.getStartDate())
                    .append("end_date",  resy.getEndDate())
                    .append("UserID", uid)
                    .append("HotelID", hid)
                    .append("RoomID", rid));

        } catch(Exception e) {
            e.printStackTrace();
        }

        //Update room availability
        String strRID = String.valueOf(rid);
        roomDAO.update(strRID, "isAvailable", false);
    }

    /**
     * Searches for a reservation, by reservationID and then returns the document in the form of a Reservation Object
     * @param reservationID
     * @return Reservation object
     */
    @Override
    public Reservation get(String reservationID) {
        //Convert to long
        long resID = Long.parseLong(reservationID);
        //Execute query
        Document search = collection.find(eq("_id", resID)).first();

        if (search == null) { return null; }

        try {
            //Get Date
            Date retrievedStartDate = search.getDate("start_date");
            Date retrievedEndDate = search.getDate("end_date");

            //Convert to LocalDate
            LocalDate startDate = retrievedStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = retrievedEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            //Get IDs
            Long uid = search.getLong("UserID");
            Long hid = search.getLong("HotelID");


            //Retrieve user, hotel, room objects
            String userName = userDAO.getUsername(uid);
            String hotelName = hotelDAO.getName(hid);
            String roomIDstr = String.valueOf(search.getLong("RoomID"));
            UserProfile user = userDAO.get(userName);
            Hotel hotel = hotelDAO.get(hotelName);
            Room room = roomDAO.get(roomIDstr);

            return new Reservation(startDate, endDate, user, hotel, room);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    /**
     * Updates a single field, specified by the fieldName parameter, with the fieldValue paramter.
     * @param reservationID
     * @param fieldName
     * @param fieldValue
     */
    @Override
    public <Thing> void update(String reservationID, String fieldName, Thing fieldValue){
        //Validate fieldName input
        if(!validFieldNames.contains(fieldName)){
            throw new IllegalArgumentException("Invalid fieldName. Please pass one of the following: " + String.join(", ", validFieldNames));
        }

        Long resID = Long.parseLong(reservationID);
        Document filter = new Document("_id", resID);
        Document update = new Document("$set", new Document(fieldName, fieldValue));

        try {
            collection.updateOne(filter, update);
        } catch(Exception e) {
            e.printStackTrace();
        }

        //If
    }

    /**
     * Deletes first database entry with a name that matches the string parameter
     * @param reservationID
     */
    @Override
    public void delete(String reservationID) {
        try{
            collection.deleteOne(eq("_id", Long.parseLong(reservationID)));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }




}

