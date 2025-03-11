<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.corsosiam.entities.Libro" %>

<!DOCUMENT html>
<html>
    <head>
        <title>Caffetteria...Nuovo Libro</title>
    </head>
    <body>
        <h1>Nuovo Libro</h1>
        <form action="inserisciLibro" method="post">

            <input type="text" placeholder="Inserisci Titolo" id="titolo" name="titolo" required>
            <input type="text" placeholder="Inserisci Autore" id="autore" name="autore">
            <input type="text" placeholder="Inserisci Codice ISBN" id="isbn" name="isbn">

            <input type="submit" value="Inserisci Libro">

        </form>
    </body>

</html>