package com.corsosiam.services;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;

import com.corsosiam.entities.Libro;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

public class LibroBI {

    // CACHE
    private List<Libro> libri = new ArrayList<>();

    public List<Libro> loadLibri() {
        if (libri.isEmpty()) {
            resetCache();
        }
        return new ArrayList<>(this.libri); // Restituisce una copia della lista per evitare modifiche esterne
    }

    public void resetCache() {
        this.libri.clear(); // Usa clear() invece di creare una nuova lista
        MongoCollection<Document> documents = getCollection();
        System.out.println("Numero di documenti trovati: " + documents.countDocuments());
        for (Document doc : documents.find()) {
            Gson gson = new Gson();
            Libro libro = gson.fromJson(doc.toJson(), Libro.class);
            this.libri.add(libro);
        }
    }

    public Libro save(Libro libro) {
        MongoCollection<Document> documents = getCollection();
        Gson gson = new Gson();
        try {
            libro.setId(UUID.randomUUID().toString());
            InsertOneResult result = documents.insertOne(Document.parse(gson.toJson(libro)));
            if (result.getInsertedId() != null) {
                libri.add(libro);
                return libro;
            } else {
                throw new Exception("Inserimento fallito");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Stampa l'errore per il debug
            return null;
        }
    }

    private MongoCollection<Document> getCollection() {
        MongoDBConnector mongodb = new MongoDBConnector();
        mongodb.setConnection();
        mongodb.setDatabase("caffetteria");
        return mongodb.load("libri");
    }
}