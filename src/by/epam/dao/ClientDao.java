package by.epam.dao;

import by.epam.interfacesDao.DAOClient;
import by.epam.payments.Client;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements DAOClient {
    ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        Connection connection = SQLDaoFactory.createConnection();
        Statement statementClients = null;
        Statement statementAccounts = null;
        ArrayList<Long> accounts;
        try {
            statementClients = connection.createStatement();
            ResultSet resultSet = statementClients.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_ABONENTS"));
            while (resultSet.next()) {

                statementAccounts = connection.createStatement();
                ResultSet resultSetAccounts = statementAccounts.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ACCOUNTS_FOR_CLIENT") + resultSet.getInt("idClient"));

                accounts = new ArrayList<>();
                Client client = new Client();
                client.setId(resultSet.getInt("idClient"));
                client.setName(resultSet.getString("name"));
                client.setAddress(resultSet.getString("address"));
                client.setPassport(resultSet.getString("passportNumber"));
                client.setDateOfBirth(resultSet.getString("dateOfBirth"));

                while (resultSetAccounts.next()) {
                    accounts.add(resultSetAccounts.getLong("AccNumber"));
                }

                client.setAccounts(accounts);
                clients.add(client);

            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                statementClients.close();
                statementAccounts.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return clients;
    }

    public boolean insertClient(Client client) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_NEW_CLIENT"));
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setString(4, client.getPassport());
            preparedStatement.setString(5, client.getDateOfBirth());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean updateClient(Client client) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_CLIENT"));
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setString(4, client.getPassport());
            preparedStatement.setString(5, client.getDateOfBirth());
            preparedStatement.setInt(6, client.getId());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean deleteClient(Client client) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_CLIENT"));
            preparedStatement.setInt(1, client.getId());
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