package com.corsosiam.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.corsosiam.entities.Libro;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;

public class LibroBI {
    public List<Libro> libri;
  
    public List<Libro> loadLibri(){
        libri = new ArrayList<Libro>();
        //     libri.add(new Libro("Il signore degli anelli","Tolkien","12345"));
        //     libri.add(new Libro("Il signore degli anelli","Tolkien","12345"));
        //     libri.add(new Libro("Il signore degli anelli","Tolkien","12345"));
        // }
            MongoDBConnector mongodb = new MongoDBConnector();
            mongodb.setConnection();
            mongodb.setDatabase("caffetteria");
            MongoCollection<Document> documents = mongodb.load("libri");
            for(Document doc : documents.find()){
                    Gson gson = new Gson();
                    Libro libro = gson.fromJson(doc.toJson(), Libro.class);
                    libri.add(libro);
                }
        
        return libri;
    }
}
