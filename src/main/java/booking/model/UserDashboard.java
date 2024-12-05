package booking.application;

/**
 * The UserDashboard class represents the dashboard view
 * It currently does not hold specific data.
 * @author Andres Feldstedt
 * @version 1.0
 */
public class UserDashboard {

    private String userName;

    public UserDashboard(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
