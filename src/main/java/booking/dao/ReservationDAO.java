package booking.dao;

import booking.model.*;


public class ReservationDAO<Reservation> implements GenericDAO<Reservation> {

    @Override
    public void add(Reservation reservation){

    }

    @Override
    public Reservation get(String reservationID) {
        return Reservation;
    }

    @Override
    public <Thing> void update(String reservationID, String fieldName, Thing fieldValue){
        return Thing;
    }

    @Override
    public void delete(String reservationID) {

    }




}
