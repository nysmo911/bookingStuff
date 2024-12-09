package booking.controller;

public class UserSession {
    // Singleton instance
    private static UserSession instance;

    // Logged-in state and user data
    private boolean isLoggedIn = false;
    private String loggedInUser = "";

    private UserSession() {
    }

    // Get the Singleton instance
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // Getter and setter for isLoggedIn
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    // Getter and setter for loggedInUser
    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String username) {
        this.loggedInUser = username;
    }

    /**
     * Clears the session data, effectively logging the user out.
     */
    public void signOut() {
        isLoggedIn = false;
        loggedInUser = "";
        System.out.println("User session has been cleared. User signed out.");
    }
}
