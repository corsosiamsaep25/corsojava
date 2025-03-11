<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="java.io.*, corsosiam.*, corsosiam.entities.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCUMENT html>

<html>
    <head>
        <title>Caffetteria... Sezione libri</title>
    </head>
    <body>
        <h1>Lista dei libri</h1>
        <p>Benvenuti nella sezione dedicata ai libri,
         da questa pagina potrete arrivare a 
         tutte le funzionalità dei libri</p>
        <ul>
            <c:forEach var="libro" items="${libri}" varStatus="loop">
                
                <li>
                    <div>Titolo:<a href="<c:url value='/libro'><c:param name='id' value='${libro.getId()}'/></c:url>"> ${libro.titolo}</a></div>
                    <div>Autore: ${libro.autore}</div> 
                    <div>ISBN: ${libro.isbn}</div> 
                </li>
            </c:forEach>
        </ul>
        <form action="inserisciLibro" method="post">

            <input type="text" placeholder="Inserisci Titolo" id="titolo" name="titolo" required>
            <input type="text" placeholder="Inserisci Autore" id="autore" name="autore">
            <input type="text" placeholder="Inserisci Codice ISBN" id="isbn" name="isbn">

            <input type="submit" value="Inserisci Libro">

        </form>
    </body>
</html>