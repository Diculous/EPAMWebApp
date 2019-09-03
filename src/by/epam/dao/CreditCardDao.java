package by.epam.dao;

import by.epam.interfacesDao.DAOCreditCard;
import by.epam.payments.CreditCard;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDao implements DAOCreditCard {
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private static final Logger logger = Logger.getLogger(CreditCardDao.class);

    public List<CreditCard> findAll() {
        List<CreditCard> cardTypes = new ArrayList<>();
        Connection connection = SQLDaoFactory.createConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_CARDS"));
            while (resultSet.next()) {
                CreditCard creditCard = new CreditCard();
                creditCard.setCardNumber(resultSet.getLong("cardNumber"));
                creditCard.setAccount(resultSet.getLong("accNumber"));
                creditCard.setCardType(resultSet.getString("cardType"));
                cardTypes.add(creditCard);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return cardTypes;
    }

    public boolean insertCard(CreditCard creditCard) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_CARD"));
            preparedStatement.setLong(1, creditCard.getCardNumber());
            preparedStatement.setString(2, creditCard.getCardType());
            preparedStatement.setLong(3, creditCard.getAccount());
            preparedStatement.executeUpdate();
            flag = true;
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return flag;
    }

    public boolean updateCardType(CreditCard creditCard, Long oldCreditCard) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_CURRENT_CARD"));
            preparedStatement.setLong(1, creditCard.getCardNumber());
            preparedStatement.setString(2, creditCard.getCardType());
            preparedStatement.setLong(3, creditCard.getAccount());
            preparedStatement.setLong(4, oldCreditCard);
            preparedStatement.executeUpdate();
            flag = true;
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return flag;
    }

    public boolean deleteCard(CreditCard creditCard) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_CURRENT_CARD"));
            preparedStatement.setLong(1, creditCard.getCardNumber());
            preparedStatement.executeUpdate();
            flag = true;
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return flag;
    }
}
