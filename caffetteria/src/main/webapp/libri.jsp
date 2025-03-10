<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <div>Titolo: ${libro.titolo}</div>
                    <div>Autore: ${libro.autore}</div> 
                    <div>ISBN: ${libro.isbn}</div> 
                </li>
            </c:forEach>
        </ul>

    </body>
</html>