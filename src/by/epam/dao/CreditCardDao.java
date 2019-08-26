package by.epam.dao;

import by.epam.interfacesDao.DAOCreditCard;
import by.epam.payments.CreditCard;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDao implements DAOCreditCard {
    ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    public List<CreditCard> findAll() {
        List<CreditCard> cardTypes = new ArrayList<>();
        Connection cn = SQLDaoFactory.createConnection();
        Statement st = null;
        try {
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_CARDS"));
            while (resultSet.next()) {
                CreditCard creditCard = new CreditCard();
                creditCard.setCardNumber(resultSet.getLong("cardNumber"));
                creditCard.setAccount(resultSet.getLong("accNumber"));
                creditCard.setCardType(resultSet.getString("cardType"));
                cardTypes.add(creditCard);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return cardTypes;
    }

    public boolean insertCard(CreditCard creditCard) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();;
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_CARD"));
            st.setLong(1, creditCard.getCardNumber());
            st.setString(2, creditCard.getCardType());
            st.setLong(3, creditCard.getAccount());
            st.executeUpdate();
            flag = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean updateCardType(CreditCard creditCard, Long oldCreditCard) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();;
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_CURRENT_CARD"));
            st.setLong(1, creditCard.getCardNumber());
            st.setString(2, creditCard.getCardType());
            st.setLong(3, creditCard.getAccount());
            st.setLong(4, oldCreditCard);
            st.executeUpdate();
            flag = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean deleteCard(CreditCard creditCard) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();;
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_CURRENT_CARD"));
            st.setLong(1, creditCard.getCardNumber());
            st.executeUpdate();
            flag = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
