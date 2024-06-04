package demo;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;


public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Отримання введених даних з форми
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Перевірка логіна та паролю
        if ("root".equals(username) && "root".equals(password)) {
            // Якщо логін та пароль вірні, перенаправлення на головну сторінку
            response.sendRedirect("admin.jsp");
        } else {
            // Якщо логін та пароль не вірні, виведення повідомлення про помилку
            PrintWriter out = response.getWriter();
            out.println("<html><body><h2>Помилка: Невірний логін або пароль <a href=\"login.jsp\">Повернутися назад</a></h2></body></html>");

        }

    }
}
