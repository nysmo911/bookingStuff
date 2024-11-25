package booking;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

/**
 * The booking.dbConnection class establishes a connection to the database.
 * This allows other class & methods within booking to create instances of this database object without having to
 * establish connection each time the database needs to be accessed.
 *
 *  @author Brandon Brenes
 *  @version 1.0
 *  @date 11/09/2024
 */
public class dbConnection {


    /**
     *
     * connection string, database name, and collection(s) name
     */
    private static final String connectionString = "mongodb+srv://brandonmbrenes2:9xBfegUvvKpdNN73@cluster0.nx05m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"; //Will need to replace this later
    private static final String databaseName = "bookingStuff";
    private static final String usersCollectionName = "userProfiles";

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

    // Get the "users" collection
    public MongoCollection<Document> getUsersCollection() {
        return database.getCollection(userProfiles);
    }

    // Insert a user into MongoDB
    public void insertUser(userProfile user) {
        MongoCollection<Document> collection = getUsersCollection();
        collection.insertOne(user.toDocument());
        System.out.println("User inserted into MongoDB.");
    }

    // Retrieve a user by username
    public userProfile getUserByName(String userName) {
        MongoCollection<Document> collection = getUsersCollection();
        Document doc = collection.find(Filters.eq("userName", userName)).first();
        if (doc != null) {
            return userProfile.fromDocument(doc);
        }
        return null; // Return null if user not found
    }

    // Update a user's email
    public void updateUserEmail(String userId, String newEmail) {
        MongoCollection<Document> collection = getUsersCollection();
        collection.updateOne(
                Filters.eq("userName", userName),
                new Document("$set", new Document("email", newEmail))
        );
    }

}


