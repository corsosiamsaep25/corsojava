package com.corsosiam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.corsosiam.entities.Libro;
import com.corsosiam.services.LibroBI;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DettaglioLibroServlet extends HttpServlet {


    LibroBI libreria = new LibroBI();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        String id = request.getParameter("id");
        if(!id.isEmpty()){
            List<Libro> libri = libreria.loadLibri();
            for(Libro libro : libri){
                if(libro.getId().equalsIgnoreCase(id)){
                    request.setAttribute("libro", libro);
                    request.getRequestDispatcher("/libro.jsp").forward(request, response);
                }
            }
        }else{
            PrintWriter out = response.getWriter();
            out.println("Mi spiace id non trovato");
        }
    }
}
