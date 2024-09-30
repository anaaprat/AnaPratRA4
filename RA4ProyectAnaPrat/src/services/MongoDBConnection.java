package services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {
	private MongoClient mongoClient;
	private MongoDatabase database;

	public MongoDBConnection(String dbName) {
		mongoClient = MongoClients.create("mongodb://localhost:27017");
		database = mongoClient.getDatabase(dbName);
	}

	public MongoCollection<Document> getCollection(String collectionName) {
		return database.getCollection(collectionName);
	}

	public void close() {
		mongoClient.close();
	}
}