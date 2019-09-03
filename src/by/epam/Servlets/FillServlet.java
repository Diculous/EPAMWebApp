package by.epam.Servlets;

import by.epam.dao.BankAccountDao;
import by.epam.dao.PaymentDao;
import by.epam.payments.BankAccount;
import by.epam.payments.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FillServlet", urlPatterns = "/fill")
public class FillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if (!request.getParameter("fill").equals("") && Integer.parseInt(request.getParameter("fill")) > 0) {
            BankAccountDao bankAccountDao = new BankAccountDao();
            if (!bankAccountDao.findByNumber(Long.parseLong(request.getParameter("accountNumber"))).getBlocked()) {
                Payment payment = new Payment();
                PaymentDao paymentDao = new PaymentDao();
                payment.setPaymentType("fill");
                payment.setBankAccount(Long.parseLong(request.getParameter("accountNumber")));
                payment.setPaymentValue(Integer.parseInt(request.getParameter("fill")));
                paymentDao.insertPayment(payment);
                bankAccountDao.changeBalance(Long.parseLong(request.getParameter("accountNumber")),
                        bankAccountDao.findByNumber(Long.parseLong(request.getParameter("accountNumber"))).getBalance() +
                                Integer.parseInt(request.getParameter("fill")));
            }
        }
        request.getRequestDispatcher("/clientpage.html").forward(request, response);
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
