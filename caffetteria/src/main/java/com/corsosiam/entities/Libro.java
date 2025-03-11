package com.corsosiam.entities;

public class Libro {

    private String _id;
    private String titolo;
    private String autore;
    private String isbn;
    
    public Libro(){}
    
    public Libro(String titolo, String autore, String isbn) {
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Libro [titolo=" + titolo + ", autore=" + autore + ", isbn=" + isbn + "]";
    }
    
    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public String getAutore() {
        return autore;
    }
    public void setAutore(String autore) {
        this.autore = autore;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    
}