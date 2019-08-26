package by.epam.Servlets;

import by.epam.dao.BankAccountDao;
import by.epam.payments.BankAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (!id.equals("")) {
            BankAccountDao bankAccountDao = new BankAccountDao();
            BankAccount bankAccount = new BankAccount();
            bankAccount.setIdAccount(Integer.parseInt(id));
            bankAccountDao.deleteBankAccount(bankAccount);
        } else {
            id = "-1";
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
