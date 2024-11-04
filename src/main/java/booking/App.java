package booking;

import java.util.*;

//import java.io.*;
//import com.fasterxml.jackson.core.JsonProcessingException;
//Import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.JsonNode;



public class App {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to bookingStuff!\nPlease enter a city name to view our hotel selection!");

        String cityName = userInput.nextLine();
        if (cityName.equals("Los Angeles")) {
            System.out.println("3 Hotels in Los Angeles:\n1.Marriot\n2.Grand America\n3.Motel 6\n:");
        } else {
            System.out.println("No Hotels available in your area.\n");
        }

        Hotel myHotel = new Hotel("Example Hotel", "Los Angeles");
        myHotel.displayHotelInfo();

    }

}