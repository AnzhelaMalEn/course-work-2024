package demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddLotServlet extends HttpServlet {

    private AuctionStorage auctionStorage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionStorage = (AuctionStorage) getServletContext().getAttribute("auctionStorage");
        if (auctionStorage == null) {
            auctionStorage = new AuctionStorage();
            getServletContext().setAttribute("auctionStorage", auctionStorage);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        double startingPrice = Double.parseDouble(request.getParameter("startingPrice"));
        String endTimeString = request.getParameter("endTime");
        String description = request.getParameter("description");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date endTime = null;
        try {
            endTime = formatter.parse(endTimeString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        auctionStorage.addAuction(title, startingPrice, endTime, description);

        // Після додавання лота перенаправте користувача на сторінку всіх лотів
        response.sendRedirect("auctions");
    }
}
