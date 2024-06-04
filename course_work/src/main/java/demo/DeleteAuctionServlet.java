package demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class DeleteAuctionServlet extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");

        int auctionId = Integer.parseInt(request.getParameter("id"));
        auctionStorage.deleteAuction(auctionId);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Лот видалено успішно</h2>");
        out.println("<a href=\"logged_main.jsp\">Назад на головну сторінку</a>");
        out.println("</body></html>");
        out.close();
    }
}
