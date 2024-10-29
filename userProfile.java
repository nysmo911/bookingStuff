package bookingStuff;
import java.util.*;
public class userProfile {

	private String fName;
	private String lName;
	private String email;
	private String userName;
	private int cardNumber;
	private String password;
	
	
	
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
	
	public String getFName() {
		return fName;
	}
	
	public String getLName() {
		return lName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	public void updateFName(String newName) {
		this.fName = newName;
	}
	
	public void updateLName(String newName) {
		this.lName = newName;
	}
	
	public void updateCardNumber(int newNumber) {
		this.cardNumber = newNumber;
	}
	
	
	
}
