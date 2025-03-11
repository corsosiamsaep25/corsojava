package com.corsosiam;

import java.io.IOException;

import com.corsosiam.entities.Libro;
import com.corsosiam.services.LibroBI;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LibroServlet extends HttpServlet {

    LibroBI libreria = new LibroBI();
    // Il metodo doGet si aspetta di ricevere la richiesta di visualizzare l'elenco intero dei libri
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException{
        // uso il metodo per caricare la lista dei libri, se è già stata caricata una volta non la ricaricherà
        // ma mi restituirà direttamente la lista dei libri
        // List libri = libreria.loadLibri();

        // preparo già la Request fornendo l'attributo libri con l'elenco dei libri
        request.setAttribute("libri", libreria.loadLibri());

        // SEGNALO CHE LA REQUEST VIENE GESTITA DIRETTAMENTE DALLA JSP -> libri.jsp
        // E RICEVERA' LEI REQUEST E INVIERA' LA RESPONSE
        request.getRequestDispatcher("/libri.jsp").forward(request, resp);


    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String titolo = request.getParameter("titolo");
        String autore = request.getParameter("autore");
        String isbn = request.getParameter("isbn");
        Libro nuovoLibro = new Libro();
        nuovoLibro.setTitolo(titolo);
        nuovoLibro.setAutore(autore);
        nuovoLibro.setIsbn(isbn);
        libreria.save(nuovoLibro);
        response.sendRedirect("/caffetteria/libri");
    }
}