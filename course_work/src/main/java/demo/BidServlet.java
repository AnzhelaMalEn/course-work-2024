package demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

public class BidServlet extends HttpServlet {

    private AuctionStorage auctionStorage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionStorage = new AuctionStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String auctionIdParam = request.getParameter("auctionId");
        String bidAmountParam = request.getParameter("bidAmount");

        System.out.println("auctionIdParam: " + auctionIdParam);
        System.out.println("bidAmountParam: " + bidAmountParam);

        // Перевірка, чи було передане значення "auctionId"
        if (auctionIdParam != null && !auctionIdParam.isEmpty() && bidAmountParam != null && !bidAmountParam.isEmpty()) {
            try {
                int auctionId = Integer.parseInt(auctionIdParam);
                double bidAmount = Double.parseDouble(bidAmountParam);
                Date bidTime = new Date();

                boolean bidPlaced = auctionStorage.placeBid(auctionId, bidAmount, bidTime);

                if (bidPlaced) {
                    response.sendRedirect("success_bid_price.jsp");
                } else {
                    response.sendRedirect("failure_bid_price.jsp");
                }
            } catch (NumberFormatException e) {
                PrintWriter out = response.getWriter();
                out.println("<html><head><title>Error</title></head><body>");
                out.println("<h2>Error: Invalid number format for auction ID or bid amount</h2>");
                out.println("<a href=\"index.jsp\">Back to Home</a>");
                out.println("</body></html>");
                e.printStackTrace();
            }
        } else {
            // Якщо значення "auctionId" не було передане, вивести повідомлення про помилку
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h2>Error: Auction ID or bid amount is missing</h2>");
            out.println("<a href=\"index.jsp\">Back to Home</a>");
            out.println("</body></html>");
        }
    }
}
