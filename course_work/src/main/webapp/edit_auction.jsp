<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit Auction</title>
</head>
<body>
<h2>Edit Auction</h2>
<form action="EditAuctionServlet" method="POST">
    <input type="hidden" name="id" value="${auction.id}">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="${auction.title}"><br><br>
    <label for="startingPrice">Starting Price:</label>
    <input type="text" id="startingPrice" name="startingPrice" value="${auction.startingPrice}"><br><br>
    <label for="description">Description:</label>
    <textarea id="description" name="description">${auction.description}</textarea><br><br>
    <input type="submit" value="Update Auction">
</form>
<br>
<form action="my_auctions.jsp">
    <input type="submit" value="Cancel">
</form>
</body>
</html>
