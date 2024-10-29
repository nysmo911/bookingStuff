package bookingStuff;
import java.util.*;
public class userTest {
	
	public static void main(String[] args) {
		
		userProfile Joseph = new userProfile("Joseph","Salama","joseph.salama.445@my.csun.edu", "joe.salama", "123", 456);
		Joseph.printInfo();
		Joseph.updateFName("Brandon");
		System.out.println(Joseph.getFName());
	}


}
