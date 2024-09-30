package services;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import models.AI;

public class IAService {

	private MongoDBConnection mongoDBConnection;

	public IAService(String dbName) {
		mongoDBConnection = new MongoDBConnection(dbName);
	}

	public List<AI> getAllAis(String collectionName) {
		List<AI> ais = new ArrayList<>();

		MongoCollection<Document> collection = mongoDBConnection.getCollection(collectionName);

		try (MongoCursor<Document> cursor = collection.find().iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();

				int code = doc.getInteger("code");
				String name = doc.getString("name");
				String type = doc.getString("type");
				int yearAp = doc.getInteger("yearAp");
				String image = doc.getString("image");

				AI ai = new AI(code, name, type, yearAp, image);

				ais.add(ai);
			}
		}

		return ais;
	}

	public void addAi(String collectionName, AI ai) {
		MongoCollection<Document> collection = mongoDBConnection.getCollection(collectionName);

		Document newAi = new Document("code", ai.getCode()).append("name", ai.getName()).append("type", ai.getType())
				.append("yearAp", ai.getYearAp()).append("image", ai.getImage());

		collection.insertOne(newAi);
	}

	public void deleteAi(String collectionName, int code) {
		MongoCollection<Document> collection = mongoDBConnection.getCollection(collectionName);

		Document query = new Document("code", code);

		collection.deleteOne(query);
	}

	public void updateAi(String collectionName, int code, AI updatedAi) {
		MongoCollection<Document> collection = mongoDBConnection.getCollection(collectionName);

		Document query = new Document("code", code);

		Document updateFields = new Document().append("name", updatedAi.getName()).append("type", updatedAi.getType())
				.append("yearAp", updatedAi.getYearAp()).append("image", updatedAi.getImage());

		collection.updateOne(query, new Document("$set", updateFields));
	}

	public void close() {
		mongoDBConnection.close();
	}
}