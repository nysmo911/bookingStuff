import java.util.*;

public class RoomType {
    private String typeName;
    private int capacity;

    public RoomType(String typeName, int capacity) {
        this.typeName = typeName;
        this.capacity = capacity;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getCapacity() {
        return capacity;
    }
    
    public String toString() {
        return typeName + ", Capacity: " + capacity + " ";
    }
}
