package by.epam.Servlets;

import by.epam.dao.BankAccountDao;
import by.epam.dao.ClientDao;
import by.epam.dao.ClientLoginDataDao;
import by.epam.payments.BankAccount;
import by.epam.payments.Client;
import by.epam.util.AjaxClientLoader;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoadClient", urlPatterns = "/loadClientAJAX")
public class LoadClientInfoAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookie = request.getCookies();
        String name = null;
        for (Cookie coo : cookie) {
            if (coo.getName().equals("user"))
            {  name = coo.getValue();
               break;
            }
        }

        ClientLoginDataDao clientLoginDataDao = new ClientLoginDataDao();
        List<AjaxClientLoader> ajaxloaders = clientLoginDataDao.findClientData(clientLoginDataDao.findClientName(name).getLogin());

        Gson gson = new Gson();
        String jsonReformatted = gson.toJson(ajaxloaders);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonReformatted);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
