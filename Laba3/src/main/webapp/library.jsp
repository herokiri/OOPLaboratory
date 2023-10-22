<%--
  Created by IntelliJ IDEA.
  User: herok
  Date: 21.10.2023
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Список книг</title>
</head>
<body>
<h1>Список книг</h1>

<!-- Форма для фильтрации по жанру -->
<form method="get" action="LibraryServlet">
    <label for="genreFilter">Фильтр по жанру:</label>
    <select name="genre" id="genreFilter">
        <option value="all">Все жанры</option>
        <option value="фантастика">Фантастика</option>
        <option value="детектив">Детектив</option>
    </select>
    <input type="submit" value="Фильтровать">
</form>
<form method="post" action="LibraryServlet">
    <input type="hidden" name="action" value="add">
    <label for="title">Название:</label>
    <input type="text" name="title" id="title">
    <label for="author">Автор:</label>
    <input type="text" name="author" id="author">
    <label for="genre">Жанр:</label>
    <input type="text" name="genre" id="genre">
    <input type="submit" value="Add Book">
</form>

<form method="post" action="LibraryServlet">
    <input type="hidden" name="action" value="remove">
    <label for="titleToRemove">Удалить по названию:</label>
    <input type="text" name="titleToRemove" id="titleToRemove">
    <input type="submit" value="Remove Book">
</form>


<ul>
    <c:forEach var="book" items="${books}">
        <li>${book.title} - ${book.author} (${book.genre})
        </li>
    </c:forEach>
</ul>

</body>
</html>




