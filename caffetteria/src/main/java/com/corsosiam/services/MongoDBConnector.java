package com.corsosiam.services;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnector {

    private MongoClient connection;
    private MongoDatabase database;
    // http://mongodb.com/docs/drivers/java/sync/v5.2/quick-start/
    public void setConnection(){
        if(connection == null){
            try {
                // Connessione a MongoDB
                connection = MongoClients.create("mongodb://localhost:27017");
            } catch (Exception e) {
                System.err.println("Errore durante la connessione a MongoDB: " + e.getMessage());
            }
        }else{
            connection.startSession();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println("Errore durante la chiusura connessione a MongoDB: " + e.getMessage());
        }
    }

    public void setDatabase(String nomeDB){
        if(connection == null){
             this.setConnection();
        }
        try {
            database = connection.getDatabase(nomeDB);
        } catch (Exception e) {
            System.err.println("Errore durante la chiusura connessione a MongoDB: " + e.getMessage());
        
        }
    }

    public MongoCollection<Document> load(String nomeCollection){
        return database.getCollection(nomeCollection);
    }

}
