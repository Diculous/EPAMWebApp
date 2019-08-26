package by.epam.interfacesDao;

import by.epam.payments.CardType;

import java.util.List;

public interface DAOCardType {
    List<CardType> findAll();
    boolean insertCardType(CardType cardType);
    boolean updateCardType(CardType cardType, String oldCardType);
    boolean deleteCardType(CardType cardType);

}
