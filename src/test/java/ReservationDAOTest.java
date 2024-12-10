import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import booking.dao.*;
import booking.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ReservationDAOTest {
    ReservationDAO reservationDAO = new ReservationDAO();
    UserDAO userDAO = new UserDAO();
    HotelDAO hotelDAO = new HotelDAO();

    @Test
    public void addToDatabaseTest(){

        //Add to db
        UserProfile user = userDAO.get("brandy");
        Hotel hotel = hotelDAO.get("Hotel Nikko San Francisco");
        Room room = hotel.getRooms().getFirst();
        LocalDate startDate = LocalDate.of(2024, 12, 15);
        LocalDate endDate = LocalDate.of(2024, 12, 18);
        Reservation reservation = new Reservation(startDate, endDate, user, hotel, room);
        reservationDAO.add(reservation);

        //Assert
        Reservation resultResy = reservationDAO.get("1");
        Assertions.assertEquals(resultResy.getStartDate(), reservation.getStartDate() );
    }
}
