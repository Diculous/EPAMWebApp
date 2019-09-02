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

@WebServlet(name = "DeleteServletAjax", urlPatterns = "/deleteAJAX")
public class DeleteServletAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BankAccountDao bankAccountDao = new BankAccountDao();

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
        }

        Gson gson = new Gson();
        BankAccount bankAccount = gson.fromJson(json, BankAccount.class);
        bankAccountDao.deleteBankAccount(bankAccount);

        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
