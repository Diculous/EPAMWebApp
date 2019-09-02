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
        Connection connection = SQLDaoFactory.createConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_TYPES_OF_CARDS"));
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
                statement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return cardTypes;
    }

    public boolean insertCardType(CardType cardType) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_NEW_CARD_TYPE"));
            preparedStatement.setString(1, cardType.getCardType());
            preparedStatement.setInt(2, cardType.getCashBack());
            preparedStatement.executeUpdate();
            flag = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean updateCardType(CardType cardType, String oldCardType) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_CURRENT_CARD_TYPE"));
            preparedStatement.setString(1, cardType.getCardType());
            preparedStatement.setInt(2, cardType.getCashBack());
            preparedStatement.setString(3, oldCardType);
            preparedStatement.executeUpdate();
            flag = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean deleteCardType(CardType cardType) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_CURRENT_CARD_TYPE"));
            preparedStatement.setString(1, cardType.getCardType());
            preparedStatement.executeUpdate();
            flag = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

}