package booking.model;
import org.bson.Document;
import java.util.List;

/** booking.model.userProfile.java
 * @author Joseph Salama
 * @version 2.0
 */

public class userProfile {

	private String fName;
	private String lName;
	private String email;
	private String userName;
	private String password;
	private List<String> reservationHistory;
	
	
	/**
	 * Constructor Method
	 * @param fName User's First Name
	 * @param lName User's Last Name
	 * @param email User's Email
	 * @param userName User's Username
	 * @param password User's Password
	 */
	public userProfile(String fName, String lName, String email, String userName, String password, List<String> reservationHistory)
	{
	    this.fName = fName;
	    this.lName = lName;
	    this.email = email;
	    this.userName = userName;
	    this.password = password;
		this.reservationHistory = reservationHistory;
	    System.out.println("New User has been created.");
	}
	
	/**
	 * Method used for getting fname
	 * @return fname
	 */
	public String getFName() {
		return fName;
	}

	/**
	 * Method used for setting fname
	 * @param fName User's First Name
	 */
	public void setFName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * Method used for getting lname
	 * @return lname
	 */
	public String getLName() {
		return lName;
	}

	/**
	 * Method used for setting lname
	 * @param lName User's Last Name
	 */
	public void setLName(String lName) {
		this.lName = lName;
	}
	
	/**
	 * Method used for getting email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method used for setting email
	 * @param email User's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Method used for getting userName
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Method used for setting userName
	 * @param userName User's username
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Method used for getting password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method used for setting userName
	 * @param password User's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method used for getting Reservation History
	 * @return reservationHistory
	 */
	public List<String> getReservationHistory() {
		return reservationHistory;
	}

	/**
	 * Method used for setting userName
	 * @param reservationHistory User's reservationHistory
	 */
	public void setReservationHistory(List<String> reservationHistory) {
		this.reservationHistory = reservationHistory;
	}

	// Convert User object to MongoDB Document
	public Document toDocument() {
		return new Document("user_id", this.userName)
				.append("firstname", this.fName)
				.append("lastname", this.lName)
				.append("username", this.userName)
				.append("password", this.password)
				.append("email", this.email)
				.append("reservation_history", this.reservationHistory);
	}

	// Convert MongoDB Document to User object
	public static userProfile fromDocument(Document doc) {
		return new userProfile(
				doc.getString("fName"),
				doc.getString("lName"),
				doc.getString("email"),
				doc.getString("userName"),
				doc.getString("password"),
				(List<String>) doc.get("reservation_history")
		);
	}
	
}
