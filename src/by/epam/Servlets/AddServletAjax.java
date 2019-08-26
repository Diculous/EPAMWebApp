package by.epam.Servlets;

import by.epam.dao.BankAccountDao;
import by.epam.dao.ClientDao;
import by.epam.payments.BankAccount;
import by.epam.payments.Client;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddServletAjax", urlPatterns = "/addAJAX")
public class AddServletAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BankAccountDao bankAccountDao = new BankAccountDao();

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
        }

        Gson gson = new Gson();
        BankAccount bankAccount = gson.fromJson(json, BankAccount.class);
        String testString = json;
        String regex = "(?<=owner\":\")(\\w+)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(testString);
        if (m.find()) {
            regex = m.group(1);
        }

        ClientDao clientDao = new ClientDao();
        List<Client> clients = clientDao.findAll();
        int ownerId = clients.size()+1;
        for (int i = 0; i < clients.size(); i++) {
            if (regex.equals(clients.get(i).getName())) {
                ownerId = clients.get(i).getId();
                bankAccount.setOwnerId(ownerId);
                break;
            }
        }
        if (ownerId == clients.size() + 1) {
            bankAccount.setOwnerId(-1);
        }
        bankAccountDao.insertBankAccount(bankAccount);

        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
