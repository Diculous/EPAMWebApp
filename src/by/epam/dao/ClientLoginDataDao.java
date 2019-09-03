package by.epam.dao;

import by.epam.interfacesDao.DAOClientLoginData;
import by.epam.payments.ClientLoginData;
import by.epam.util.AjaxClientLoader;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientLoginDataDao implements DAOClientLoginData {
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private static final Logger logger = Logger.getLogger(ClientLoginDataDao.class);

    @Override
    public ClientLoginData findByLoginAndPassword(String login, String password) {
        Connection connection = SQLDaoFactory.createConnection();
        ClientLoginData clientLoginData = new ClientLoginData();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_FIND_CLIENT_LOGIN_DATA"));
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                clientLoginData.setId(resultSet.getInt(1));
                clientLoginData.setLogin(resultSet.getString(2));
                clientLoginData.setPassword(resultSet.getString(3));
            }
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return clientLoginData;
    }

    @Override
    public ClientLoginData findClientName(String login) {
        Connection connection = SQLDaoFactory.createConnection();
        ClientLoginData clientLoginData = new ClientLoginData();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_FIND_CLIENT_NAME"));
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                clientLoginData.setLogin(resultSet.getString(1));
            }
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return clientLoginData;
    }

    public List<AjaxClientLoader> findClientData (String name) {
        List<AjaxClientLoader> loadClientInfoAjaxes = new ArrayList<>();
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_FIND_CLIENT_DATA"));
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // name, address, passportNumber, dateOfBirth, accNumber, balance, isBlocked
                AjaxClientLoader ajaxClientLoader = new AjaxClientLoader();
                ajaxClientLoader.setName(resultSet.getString("name"));
                ajaxClientLoader.setAddress(resultSet.getString("address"));
                ajaxClientLoader.setPassportNumber(resultSet.getString("passportNumber"));
                ajaxClientLoader.setDateOfBirth(resultSet.getString("dateOfBirth"));
                ajaxClientLoader.setAccountNumber(resultSet.getLong("accNumber"));
                ajaxClientLoader.setBalance(resultSet.getInt("balance"));
                ajaxClientLoader.setBlocked(resultSet.getBoolean("isBlocked"));
                loadClientInfoAjaxes.add(ajaxClientLoader);
            }
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return loadClientInfoAjaxes;
    }

}


