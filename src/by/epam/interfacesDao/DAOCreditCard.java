package by.epam.interfacesDao;

import by.epam.payments.CreditCard;

import java.util.List;

public interface DAOCreditCard {
    List<CreditCard> findAll();
    boolean insertCard(CreditCard creditCard);
    boolean updateCardType(CreditCard creditCard, Long oldCreditCard);
    boolean deleteCard(CreditCard creditCard);
}
