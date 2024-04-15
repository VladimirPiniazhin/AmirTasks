package com.example.exercise4;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;


import static com.mongodb.client.model.Filters.eq;

public class DBConnector {

    private static final String DB_URL = "mongodb+srv://vladimirpiniazhin:cWHaPUvcw2RekFQm@cluster0.xrdatkq.mongodb.net/?retryWrites=true&w=majority";
    private static final String DB_NAME = "amir";
    private static final String COLLECTION_NAME = "user";

    public String write (String name, int age, String city) {

        try (MongoClient mongoClient = MongoClients.create(DB_URL)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document doc = new Document("name", name).append("age", age).append("city", city);
            collection.insertOne(doc);

            return "200";

        } catch (MongoException e) {
            e.printStackTrace();
            return "400";
        }
    }

    public String read(String idString) {
        try (MongoClient mongoClient = MongoClients.create(DB_URL)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            // Transforming the string id into an ObjectId
            ObjectId id = new ObjectId(idString);

            // Finding the document by its id
            Document doc = collection.find(eq("_id", id)).first();
            // Returning the document as a JSON string
            return doc.toJson(JsonWriterSettings.builder().indent(true).build());
        } catch (MongoException e) {
            e.printStackTrace();
            return "400";
        }
    }
    public String delete(String idString) {
        try (MongoClient mongoClient = MongoClients.create(DB_URL)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            // Transforming the string id into an ObjectId
            ObjectId id = new ObjectId(idString);

            // Finding and deleting the document by its id
            DeleteResult deleteResult = collection.deleteOne(eq("_id", id));

            // Returning the document as a JSON string
            return "200";
        } catch (MongoException e) {
            e.printStackTrace();
            return "400";
        }
    }
    public String update(String idString, String name, int age, String city) {
        try (MongoClient mongoClient = MongoClients.create(DB_URL)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            // Transforming the string id into an ObjectId
            ObjectId id = new ObjectId(idString);

            // Finding the document by its id
            Document doc = collection.find(eq("_id", id)).first();

            // Updating the document
            doc.put("name", name != null ? name : doc.getString("name"));
            doc.put("age", age);
            doc.put("city", city);

            // Replacing the old document with the updated one
            collection.replaceOne(eq("_id", id), doc);

            // Returning the document as a JSON string
            return "200";
        } catch (MongoException e) {
            e.printStackTrace();
            return "400";
        }
    }

    }






