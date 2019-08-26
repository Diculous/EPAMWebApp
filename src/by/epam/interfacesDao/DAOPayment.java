package by.epam.interfacesDao;

import by.epam.payments.Payment;

import java.util.List;

public interface DAOPayment {
    List<Payment> findAll();
    boolean insertPayment(Payment payment);
    boolean updatePayment(Payment payment, Integer id);
    boolean deletePayment(Integer id);
}
