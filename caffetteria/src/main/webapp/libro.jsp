<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.corsosiam.entities.Libro" %>
<%
    // Ottieni l'oggetto Libro dall'attributo della richiesta
    Libro libro = (Libro) request.getAttribute("libro");
%>
<!DOCUMENT html>
<html>

<head>
    <title>Caffetteria... Libro</title>
</head>

<body>
    
        <div>Titolo: ${libro.titolo}</div>
        <div>Autore: ${libro.autore}</div>
        <div>ISBN: ${libro.isbn}</div>

</body>

</html>