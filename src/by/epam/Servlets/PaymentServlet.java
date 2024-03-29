package by.epam.Servlets;

import by.epam.dao.BankAccountDao;
import by.epam.dao.PaymentDao;
import by.epam.payments.Payment;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PaymentServlet", urlPatterns = "/payment")
public class PaymentServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PaymentServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!request.getParameter("pay").equals("")) {
            BankAccountDao bankAccountDao = new BankAccountDao();
            if (!bankAccountDao.findByNumber(Long.parseLong(request.getParameter("accountNumber"))).getBlocked()) {
                PaymentDao paymentDao = new PaymentDao();
                try {
                    int paymentValue = Integer.parseInt(request.getParameter("pay"));
                    int balance = bankAccountDao.findByNumber(Long.parseLong(request.getParameter("accountNumber"))).getBalance();
                    if (balance >= paymentValue && paymentValue > 0) {
                        Payment payment = new Payment();
                        payment.setPaymentType("pay");
                        payment.setBankAccount(Long.parseLong(request.getParameter("accountNumber")));
                        payment.setPaymentValue(Integer.parseInt(request.getParameter("pay")));
                        paymentDao.insertPayment(payment);
                        bankAccountDao.changeBalance(Long.parseLong(request.getParameter("accountNumber")), balance - paymentValue);
                    }
                }
                catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        request.getRequestDispatcher("/clientpage.html").forward(request, response);
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
