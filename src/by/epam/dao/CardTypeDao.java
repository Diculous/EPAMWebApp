package by.epam.dao;

import by.epam.payments.CardType;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardTypeDao implements by.epam.interfacesDao.DAOCardType {
    ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    public List<CardType> findAll() {
        List<CardType> cardTypes = new ArrayList<>();
        Connection cn = SQLDaoFactory.createConnection();
        Statement st = null;
        try {
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_TYPES_OF_CARDS"));
            while (resultSet.next()) {
                CardType cardType = new CardType();
                cardType.setCardType(resultSet.getString("cardType"));
                cardType.setCashBack(resultSet.getInt("cashbackBonus"));
                cardTypes.add(cardType);
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

    public boolean insertCardType(CardType cardType) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_NEW_CARD_TYPE"));
            st.setString(1, cardType.getCardType());
            st.setInt(2, cardType.getCashBack());
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

    public boolean updateCardType(CardType cardType, String oldCardType) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_CURRENT_CARD_TYPE"));
            st.setString(1, cardType.getCardType());
            st.setInt(2, cardType.getCashBack());
            st.setString(3, oldCardType);
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

    public boolean deleteCardType(CardType cardType) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_CURRENT_CARD_TYPE"));
            st.setString(1, cardType.getCardType());
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