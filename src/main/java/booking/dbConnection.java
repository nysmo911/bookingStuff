package booking;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * booking.dbConnection.java
 *  @author Brandon Brenes
 *  @version 1.0
 */
public class dbConnection {


    /**
     *
     * connection string, database name, and collection(s) name
     */
    private static final String connectionString = "mongodb+srv://brandonmbrenes2:9xBfegUvvKpdNN73@cluster0.nx05m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"; //Will need to replace this later
    private static final String databaseName = "bookingStuff";

    /**
     * server api version
     */
    private final ServerApi serverApi;
    {
        serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
    }

    /**
     * MongoClient Settings
     */
    private final MongoClientSettings settings;
    {
        settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                //  .serverApi(serverApi)
                .build();
    }

    private static dbConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    /**
     * Private constructor
     */
    private dbConnection() {
        connect();
    }

    /**
     * Gets the instance
     * @return instance
     */
    public static dbConnection getInstance() {
        if (instance == null) {
          instance = new dbConnection();
        }
        return instance;
    }

    /**
     * Connection Setup
     */
    private void connect() {
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(databaseName);
    }

    /**
     * Gets the database
     * @return database
     */
    public MongoDatabase getDatabase() {
        return database;
    }

}


