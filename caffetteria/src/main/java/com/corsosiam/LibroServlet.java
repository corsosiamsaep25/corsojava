package com.corsosiam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.corsosiam.entities.Libro;
import com.corsosiam.services.MongoDBConnector;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LibroServlet extends HttpServlet {
    
    // CACHE
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
    // Il metodo doGet si aspetta di ricevere la richiesta di visualizzare l'elenco intero dei libri
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException{
        // uso il metodo per caricare la lista dei libri, se è già stata caricata una volta non la ricaricherà
        // ma mi restituirà direttamente la lista dei libri
        loadLibri();

        // preparo già la Request fornendo l'attributo libri con l'elenco dei libri
        request.setAttribute("libri", libri);

        // SEGNALO CHE LA REQUEST VIENE GESTITA DIRETTAMENTE DALLA JSP -> libri.jsp
        // E RICEVERA' LEI REQUEST E INVIERA' LA RESPONSE
        request.getRequestDispatcher("/libri.jsp").forward(request, resp);


    }
}