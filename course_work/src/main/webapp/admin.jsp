<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h2>Створення лоту</h2>
<form action="add_lot" method="POST">
    <label for="title">Назва:</label>
    <input type="text" id="title" name="title" required><br><br>

    <label for="startingPrice">Початкова ставка:</label>
    <input type="number" id="startingPrice" name="startingPrice" step="0.01" required><br><br>

    <label for="endTime">Час закінчення лоту:</label>
    <input type="datetime-local" id="endTime" name="endTime" required><br><br>

    <label for="description">Опис:</label>
    <textarea id="description" name="description" required></textarea><br><br>

    <input type="submit" value="Додати лот">
</form>
<br>
<a href="logged_main.jsp">На головну сторінку</a>
<br>
</body>
</html>
