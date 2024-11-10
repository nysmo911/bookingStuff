package booking;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class dbConnection {


    //connection string, database name, and collection(s) name
    private static final String connectionString = "mongodb+srv://brandonmbrenes2:9xBfegUvvKpdNN73@cluster0.nx05m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"; //Will need to replace this later
    private static final String databaseName = "bookingStuff";

    //server api version
    private final ServerApi serverApi;

    {
        serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
    }


    //mongoclient settings
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

    //Private constructor
    private dbConnection() {
        connect();
    }

    //Instance getter
    public static dbConnection getInstance() {
        if (instance == null) {
          instance = new dbConnection();
        }
        return instance;
    }

    //Connection setup
    private void connect() {
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(databaseName);
    }

    //Getter
    public MongoDatabase getDatabase() {
        return database;
    }

}


