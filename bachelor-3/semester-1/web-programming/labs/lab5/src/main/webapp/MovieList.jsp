<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.lab5.Movie, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список фильмов</title>
</head>
<body>
    <%
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");

    if (name == null || name.isEmpty()) {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ErrorManager.jsp");
        dispatcher.forward(request, response);
        return;
    }

    List<Movie> movies = new ArrayList<>();
    movies.add(new Movie("Нолан", "Бэтмен: Начало"));
    movies.add(new Movie("Нолан", "Тёмный рыцарь"));
    movies.add(new Movie("Нолан", "Тёмный рыцарь: Возрождение легенды"));
    request.setAttribute("movies", movies);
%>
    <h2>Список любимых фильмов пользователя <%= name %></h2>
    <jsp:include page="ListData.jsp"/>
</body>
</html>

