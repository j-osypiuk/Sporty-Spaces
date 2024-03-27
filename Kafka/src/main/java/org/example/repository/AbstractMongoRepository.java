package org.example.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.validationOption.ClientValidationOptions;
import org.example.validationOption.CourtValidationOptions;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public abstract class AbstractMongoRepository implements AutoCloseable {
    private final ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017,localhost:27018,localhost:27019/?replicaSet=replica_set_single");
    private final MongoCredential credential = MongoCredential.createCredential(
            "admin",
            "admin",
            "adminpassword".toCharArray()
    );
    private final CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder()
                            .automatic(true)
                            .build()));
    private MongoClient mongoClient;
    private MongoDatabase db;

    public AbstractMongoRepository() {
        initDbConnection();
    }

    private void initDbConnection() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .codecRegistry(CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        codecRegistry))
                .build();

        mongoClient = MongoClients.create(settings);

        db = mongoClient.getDatabase("rentCourtDb");

        if (!collectionExist("clients"))
            db.createCollection("clients", new CreateCollectionOptions().validationOptions(ClientValidationOptions.options));
        if (!collectionExist("courts"))
            db.createCollection("courts", new CreateCollectionOptions().validationOptions(CourtValidationOptions.options));
    }

    public MongoDatabase getDatabase() {
        return db;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public boolean collectionExist(String collectionName) {
        for (String existingCollectionName : db.listCollectionNames()) {
            if (existingCollectionName.equals(collectionName))
                return true;
        }
        return false;
    }
    @Override
    public void close() {
        mongoClient.close();
    }

}
