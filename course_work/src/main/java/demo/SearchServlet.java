package demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

public class SearchServlet extends HttpServlet {

    private AuctionStorage auctionStorage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionStorage = new AuctionStorage();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String keywords = request.getParameter("keywords");

        List<Auction> searchResults = auctionStorage.searchAuctions(keywords);

        out.println("<html><head><title>Результати пошуку</title></head><body>");
        out.println("<h2>Результати пошуку</h2>");
        out.println("<a href=\"index.jsp\">На головну</a>");

        if (searchResults.isEmpty()) {
            out.println("<p>Нічого не знайдено</p>");
        } else {
            for (Auction auction : searchResults) {
                out.println("<div>");
                out.println("<h3>" + auction.getTitle() + "</h3>");
                out.println("<p>Стартова ціна: " + auction.getStartingPrice() + "</p>");
                out.println("<p>Створено: " + auction.getCreatedAt() + "</p>");
                out.println("<p>Опис: " + auction.getDescription() + "</p>");
                out.println("<form action=\"bid.jsp\" method=\"GET\">");
                out.println("<input type=\"hidden\" name=\"auctionId\" value=\"" + auction.getId() + "\">");
                out.println("<input type=\"submit\" value=\"Place Bid\">");
                out.println("</form>");
                out.println("<hr>");
                out.println("</div>");
            }
        }

        out.println("</body></html>");
        out.close();
    }
}
