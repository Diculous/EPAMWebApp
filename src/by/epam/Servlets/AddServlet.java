package by.epam.Servlets;

import by.epam.dao.BankAccountDao;
import by.epam.dao.ClientDao;
import by.epam.payments.BankAccount;
import by.epam.payments.Client;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddServlet", urlPatterns = "/add")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String accNumber = request.getParameter("accNumber");
        String isBlocked = request.getParameter("isBlocked");
        String balance = request.getParameter("balance");

        if (!(id.equals("")|| owner.equals("")|| accNumber.equals("")|| isBlocked.equals(""))) {
            BankAccountDao bankAccountDao = new BankAccountDao();
            ClientDao clientDao = new ClientDao();
            List<Client> clients = clientDao.findAll();
            int ownerId = clients.size()+1;
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).getName().equals(owner)) {
                    ownerId = clients.get(i).getId();
                    break;
                }
            }
            if (ownerId == clients.size() + 1) {
                Client client = new Client();
                client.setId(clients.size() + 1);
                client.setName(owner);
                clientDao.insertClient(client);
            }
            BankAccount bankAccount = new BankAccount(Integer.parseInt(id), Long.parseLong(accNumber), Boolean.parseBoolean(isBlocked), ownerId, Integer.parseInt(balance));
            bankAccountDao.insertBankAccount(bankAccount);
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
