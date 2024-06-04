package demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

public class AuctionServlet extends HttpServlet {
    private AuctionStorage auctionStorage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionStorage = (AuctionStorage) getServletContext().getAttribute("auctionStorage");
        if (auctionStorage == null) {
            auctionStorage = new AuctionStorage();
            getServletContext().setAttribute("auctionStorage", auctionStorage);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><body><a href=\"index.jsp\">Домашня сторінка</a><br>");

        // Додаємо форму пошуку на сторінку "Всі лоти"
        out.println("<br><form action=\"search\" method=\"GET\">");
        out.println("<input type=\"text\" name=\"keywords\" placeholder=\"Ключові слова\">");
        out.println("<input type=\"submit\" value=\"Пошук\">");
        out.println("</form>");

        // Перевіряємо, чи є параметр пошуку
        String keywords = request.getParameter("keywords");
        List<Auction> activeAuctions;
        auctionStorage.checkAndCloseAuctions(); // Перевірити і закрити завершені лоти
        if (keywords != null && !keywords.isEmpty()) {
            activeAuctions = auctionStorage.searchAuctions(keywords);
        } else {
            activeAuctions = auctionStorage.getAllAuctions();
        }

        if (activeAuctions.isEmpty()) {
            out.println("<p>Активних лотів не знайдено.</p>");
        } else {
            out.println("<h2>Активні аукціони:</h2>");
            out.println("<ul>");
            for (Auction auction : activeAuctions) {
                out.println("<li>");
                out.println("<a href=\"" + auction.getUrl() + "\">" + auction.getTitle() + "</a>");
                out.println(" - Стартова ціна: " + auction.getStartingPrice());
                out.println(" - Триватиме до: " + auction.getEndTime());
                out.println("</li>");
            }
            out.println("</ul>");
        }

        out.println("</body></html>");
        out.close();
    }
}
