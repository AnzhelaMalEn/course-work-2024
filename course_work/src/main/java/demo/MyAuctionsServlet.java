package demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

public class MyAuctionsServlet extends HttpServlet {
    private AuctionStorage auctionStorage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionStorage = new AuctionStorage();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<Auction> myAuctions = auctionStorage.getAdminAuctions(); // Отримати всі лоти адміністратора

        out.println("<html><body>");
        out.println("<h2>Мої лоти</h2>");
        out.println("<br><a href=\"logged_main.jsp\">Назад до головної сторінки</a>");
        for (Auction auction : myAuctions) {
            out.println("<h3>" + auction.getTitle() + "</h3>");
            out.println("<p>Стартова ціна: $" + auction.getStartingPrice() + "</p>");
            out.println("<p>Створено: " + auction.getCreatedAt() + "</p>");
            out.println("<p>Опис: " + auction.getDescription() + "</p>");
            out.println("<p>Дата і час закінчення аукціону: " + auction.getEndTime() + "</p>");
            out.println("<input type=\"submit\" value=\"Редагувати лот\"><br>");
            out.println("<form action=\"delete_auction\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"id\" value=\"" + auction.getId() + "\">");
            out.println("<br><input type=\"submit\" value=\"Видалити лот\">");
            out.println("</form>");
            out.println("<hr>");
        }

        out.println("</body></html>");
        out.close();
    }
}
