package by.epam.Servlets;

import by.epam.dao.BankAccountDao;
import by.epam.payments.BankAccount;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UnblockServlet", urlPatterns = "/unblock")
public class UnblockServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UnblockServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("accountNumber");
        if (!account.equals("")) {
            BankAccountDao bankAccountDao = new BankAccountDao();
            BankAccount bankAccount = new BankAccount();
            try {
                bankAccount.setAccountNumber(Long.parseLong(account));
                bankAccountDao.unblock(bankAccount);
            }
            catch (NumberFormatException e) {
                logger.error(e.getMessage(), e);
            }
        }
        request.getRequestDispatcher("/index.html").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.html").forward(request, response);
    }
}
