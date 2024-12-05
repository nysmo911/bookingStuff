package booking.model;

/**
 * The ProfileSettings class represents user profile data.
 * It is used to store and manipulate user information.
 */
public class ProfileSettings {

    private String fName;
    private String lName;
    private String address;
    private String password;

    // Constructor
    public ProfileSettings(String fName, String lName, String address, String password) {
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.password = password;
    }

    // Getters and Setters
    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
