<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My Auctions</title>
</head>
<body>
<h2>My Auctions</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Starting Price</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="auction" items="${auctions}">
        <tr>
            <td>${auction.id}</td>
            <td>${auction.title}</td>
            <td>${auction.startingPrice}</td>
            <td>${auction.description}</td>
            <td>
                <form action="DeleteAuctionServlet" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${auction.id}" />
                    <input type="submit" value="Delete" />
                </form>
                <form action="EditAuctionServlet" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="${auction.id}" />
                    <input type="submit" value="Edit" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
