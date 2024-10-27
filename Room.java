import java.util.*;

public class Room {
    private double price;
    private boolean isAvailable;
    private RoomType roomType; 

    public Room(double price, boolean isAvailable, RoomType roomType) {
        this.price = price;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
    }


    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String toString() {
        return "Price: $" + price + ", Available: " + isAvailable + ", Room Type: " + roomType;
    }
}
