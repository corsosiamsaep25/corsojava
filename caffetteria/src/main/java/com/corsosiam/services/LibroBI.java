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
    public List<Libro> libri  = new ArrayList<Libro>();

    public List<Libro> loadLibri() {
        if(libri.isEmpty()){
            resetCache();
        }

        return libri;
    }

    private void resetCache() {
        libri.clear();
        MongoCollection<Document> documents = this.getCollection();
        for (Document doc : documents.find()) {
            Gson gson = new Gson();
            Libro libro = gson.fromJson(doc.toJson(), Libro.class);
            libri.add(libro);
        }
    }

    public Libro save(Libro libro){
        MongoCollection<Document> documents = this.getCollection();
        Gson gson = new Gson();
        try {
            libro.setId(UUID.randomUUID().toString());
            InsertOneResult resp = documents.insertOne(Document.parse(gson.toJson(libro)));
            if(!libro.getId().equals(resp.getInsertedId().toString())){
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        // libri.add(libro); 
        // A livello sequenziale se arrivo qui ho salvato il libro nel db.
        // ma non ho la certezza di averlo effettivamente caricato
        // Se ricarico la lista leggendo dal db (con il metodo resetCache())
        // sicuramente il dato sarà caricato anche su mongoDB
        this.resetCache();
        return libro;
    }

    private  MongoCollection<Document> getCollection() {
        MongoDBConnector mongodb = new MongoDBConnector();
        mongodb.setConnection();
        mongodb.setDatabase("caffetteria");

        MongoCollection<Document> documents = mongodb.load("libri");
        return documents;
    }

}
