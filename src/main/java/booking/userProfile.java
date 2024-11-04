package booking;

/** booking.userProfile.java
 * @author Joseph Salama
 * @version 1.0
 */

public class userProfile {

	private String fName;
	private String lName;
	private String email;
	private String userName;
	private int cardNumber;
	private String password;
	
	
	/**
	 * Constructor Method
	 * @param fName User's First Name
	 * @param lName User's Last Name
	 * @param email User's Email
	 * @param userName User's Username
	 * @param password User's Password
	 * @param cardNumber User's card number
	 */
	public userProfile(String fName, String lName, String email, String userName, String password, int cardNumber)
	{
	    this.fName = fName;
	    this.lName = lName;
	    this.email = email;
	    this.userName = userName;
	    this.password = password;
	    this.cardNumber = cardNumber;
	    System.out.println("New User has been created.");
	}
	
	/**
	 * Method used to print all of User's Data
	 * @return null
	 */
	public String printInfo() {
		System.out.print("Firstname:   ");
		System.out.println(fName);
		System.out.print("Lastname:    ");
		System.out.println(lName);
		System.out.print("Username:    ");
		System.out.println(userName);
		System.out.print("Password:    ");
		System.out.println(password);
		System.out.print("Card Number: ");
		System.out.println(cardNumber);
		return null;
	}
	
	/**
	 * Method used for getting fname
	 * @return fname
	 */
	public String getFName() {
		return fName;
	}
	
	/**
	 * Method used for getting lname
	 * @return lname
	 */
	public String getLName() {
		return lName;
	}
	
	/**
	 * Method used for getting email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Method used for getting userName
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Method used for getting password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Method used for getting cardNumber
	 * @return cardNumber
	 */
	public int getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * method used to update user's fname
	 * @param newName temp variable used to update fname
	 */
	public void updateFName(String newName) {
		this.fName = newName;
	}
	
	/**
	 * method used to update user's lname
	 * @param newName temp variable used to update lname
	 */
	public void updateLName(String newName) {
		this.lName = newName;
	}
	
	/**
	 * method used to update user's cardNumber
	 * @param newNumber temp variable used to update cardNumber
	 */
	public void updateCardNumber(int newNumber) {
		this.cardNumber = newNumber;
	}
	
	
	
}
