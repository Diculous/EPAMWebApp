package by.epam.Servlets;

import by.epam.dao.BankAccountDao;
import by.epam.payments.BankAccount;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "BlockServlet", urlPatterns = "/block")
public class BlockServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(Long.parseLong(request.getParameter("accountNumber")));
        bankAccount.setBlocked(true);
        BankAccountDao bankAccountDao = new BankAccountDao();
        bankAccountDao.blockBankAccount(bankAccount);

        request.getRequestDispatcher("/clientpage.html").forward(request, response);
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
