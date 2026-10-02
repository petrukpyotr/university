<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.lab5.Movie, java.util.*" %>

<table border="1" cellpadding="5">
    <tr><th>Режиссёр</th><th>Название</th></tr>
    <%
        List<Movie> movies = (List<Movie>) request.getAttribute("movies");
        for (Movie m : movies) {
    %>
    <tr><td><%= m.getDirector() %></td><td><%= m.getTitle() %></td></tr>
    <% } %>
</table>
