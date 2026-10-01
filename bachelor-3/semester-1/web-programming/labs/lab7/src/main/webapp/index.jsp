<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Cookie Example</title>
</head>
<body>
<h2>Введите имя:</h2>
<%--
    Форма отправляет POST-запрос на CookieServlet для сохранения имени в Cookie.
    Поле username обязательно для заполнения (атрибут required).
--%>
<form action="${pageContext.request.contextPath}/CookieServlet" method="post">
    <input type="text" name="username" required>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
