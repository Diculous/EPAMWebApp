package by.epam.Servlets;

import by.epam.dao.ClientLoginDataDao;
import by.epam.payments.ClientLoginData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String name = request.getParameter("user");
       String password = request.getParameter("password");
       ClientLoginDataDao clientLoginDataDao = new ClientLoginDataDao();
       ClientLoginData clientLoginData = clientLoginDataDao.findByLoginAndPassword(name, password);

        if (name.equals("admin") && password.equals("root")) {
            HttpSession session = request.getSession();
            session.setAttribute("authuser", true);
            Cookie userNameCookie = new Cookie("user", name);
            userNameCookie.setMaxAge(-1);
            response.addCookie(userNameCookie);
            response.sendRedirect("indexAjax.html");
        } else {
            if (clientLoginData.getId() == 0) {
                response.setCharacterEncoding("UTF-8");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.html");
                PrintWriter out = response.getWriter();
                out.println("<div class=\"div2\">Login or Password is incorrect</div>");
                rd.include(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("authuser", true);
                Cookie userNameCookie = new Cookie("user", name);
                userNameCookie.setMaxAge(-1);
                response.addCookie(userNameCookie);
                response.sendRedirect("clientpage.html");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/main.html").forward(request, response);
    }
}
