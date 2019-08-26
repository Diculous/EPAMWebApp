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

      /*  BankAccountDao bankAccountDao = new BankAccountDao();

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
        }
        System.out.println(json);

        Gson gson = new Gson();
        BankAccount bankAccount = gson.fromJson(json, BankAccount.class);
        System.out.println(bankAccount.getIdAccount());
        System.out.println(bankAccount.getAccountNumber());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getBlocked());
        System.out.println(bankAccount.getOwnerId());
        bankAccount.setBlocked(true);
        bankAccountDao.blockBankAccount(bankAccount);
*/
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
