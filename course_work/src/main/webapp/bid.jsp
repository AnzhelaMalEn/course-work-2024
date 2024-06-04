<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bid Page</title>
</head>
<body>
<h2>Сторінка ставка</h2>

<form action="BidServlet" method="POST">
    <input type="hidden" id="auctionId" name="auctionId" value="<%= request.getParameter("id") %>">
    <label for="bidAmount">Сума ставки:</label>
    <input type="text" id="bidAmount" name="bidAmount"><br><br>
    <input type="submit" value="Поставити ставку">
</form>
<br>
<form action="logged_main.jsp">
    <input type="submit" value="Домашня сторінка">
</form>

</body>
</html>
