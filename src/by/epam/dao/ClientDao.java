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
        Connection cn = SQLDaoFactory.createConnection();
        Statement st = null;
        Statement st2 = null;
        ArrayList<Long> accounts;
        try {
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_ABONENTS"));
            while (resultSet.next()) {

                st2 = cn.createStatement();
                ResultSet resultSet2 = st2.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ACCOUNTS_FOR_CLIENT") + resultSet.getInt("idClient"));

                accounts = new ArrayList<>();
                Client client = new Client();
                client.setId(resultSet.getInt("idClient"));
                client.setName(resultSet.getString("name"));
                client.setAddress(resultSet.getString("address"));
                client.setPassport(resultSet.getString("passportNumber"));
                client.setDateOfBirth(resultSet.getString("dateOfBirth"));

                while (resultSet2.next()) {
                    accounts.add(resultSet2.getLong("AccNumber"));
                }

                client.setAccounts(accounts);
                clients.add(client);

            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                st.close();
                st2.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return clients;
    }

    public boolean insertClient(Client client) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_NEW_CLIENT"));
            st.setInt(1, client.getId());
            st.setString(2, client.getName());
            st.setString(3, client.getAddress());
            st.setString(4, client.getPassport());
            st.setString(5, client.getDateOfBirth());
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean updateClient(Client client) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_CLIENT"));
            st.setInt(1, client.getId());
            st.setString(2, client.getName());
            st.setString(3, client.getAddress());
            st.setString(4, client.getPassport());
            st.setString(5, client.getDateOfBirth());
            st.setInt(6, client.getId());
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean deleteClient(Client client) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_CLIENT"));
            st.setInt(1, client.getId());
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