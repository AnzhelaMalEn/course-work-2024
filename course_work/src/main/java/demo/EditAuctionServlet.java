package demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

public class EditAuctionServlet extends HttpServlet {
    private AuctionStorage auctionStorage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionStorage = new AuctionStorage();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int auctionId = Integer.parseInt(request.getParameter("id"));
        Auction auction = auctionStorage.getAuctionById(auctionId);

        if (auction != null) {
            request.setAttribute("auction", auction);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit_auction.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("my_auctions.jsp");
        }
    }
}
