package booking.util;

public class HotelSearchModel {
    String HotelName;
    String HotelCity;
    String HotelState;

    public HotelSearchModel(String hotelName, String hotelCity, String hotelState) {
        this.HotelName = hotelName;
        this.HotelCity = hotelCity;
        this.HotelState = hotelState;
    }

    public String getHotelName() {
        return HotelName;
    }

    public String getHotelCity() {
        return HotelCity;
    }

    public String getHotelState() {
        return HotelState;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public void setHotelCity(String hotelCity) {
        HotelCity = hotelCity;
    }

    public void setHotelState(String hotelState) {
        HotelState = hotelState;
    }
}
