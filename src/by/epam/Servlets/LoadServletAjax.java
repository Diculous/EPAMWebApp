package by.epam.Servlets;

import by.epam.dao.BankAccountDao;
import by.epam.dao.ClientDao;
import by.epam.payments.BankAccount;
import by.epam.payments.Client;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoadServlet", urlPatterns = "/loadAJAX")
public class LoadServletAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        BankAccountDao bankAccountDao = new BankAccountDao();
        List<BankAccount> bankAccounts = bankAccountDao.findAll();
        ClientDao clientDao = new ClientDao();
        List<Client> clients = clientDao.findAll();
        List<Ajaxloader> ajaxloaders = new ArrayList<>();

        for (int i = 0; i < bankAccounts.size(); i++) {
            ajaxloaders.add(new Ajaxloader(bankAccounts.get(i).getIdAccount(),
                                           bankAccounts.get(i).getAccountNumber(),
                                           bankAccounts.get(i).getBlocked(),
                                           clients.get((bankAccounts.get(i).getOwnerId())-1).getName(),
                                           bankAccounts.get(i).getBalance()));

        }

        String jsonReformatted = gson.toJson(ajaxloaders);
        String sjson = gson.toJson(bankAccounts);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonReformatted);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    class Ajaxloader{
        private int idAccount;
        private Long accountNumber;
        private Boolean isBlocked = false;
        private String ownerId;
        private int balance;

        public Ajaxloader() {
        }

        public Ajaxloader(int idAccount, Long accountNumber, Boolean isBlocked, String ownerId, int balance) {
            this.idAccount = idAccount;
            this.accountNumber = accountNumber;
            this.isBlocked = isBlocked;
            this.ownerId = ownerId;
            this.balance = balance;
        }

        public int getIdAccount() {
            return idAccount;
        }

        public void setIdAccount(int idAccount) {
            this.idAccount = idAccount;
        }

        public Long getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(Long accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Boolean getBlocked() {
            return isBlocked;
        }

        public void setBlocked(Boolean blocked) {
            isBlocked = blocked;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}
