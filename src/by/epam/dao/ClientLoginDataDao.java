package by.epam.dao;

import by.epam.interfacesDao.DAOClientLoginData;
import by.epam.payments.ClientLoginData;
import by.epam.util.AjaxClientLoader;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientLoginDataDao implements DAOClientLoginData {
    ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public ClientLoginData findByLoginAndPassword(String login, String password) {
        Connection cn = SQLDaoFactory.createConnection();
        ClientLoginData clientLoginData = new ClientLoginData();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_FIND_CLIENT_LOGIN_DATA"));
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                clientLoginData.setId(rs.getInt(1));
                clientLoginData.setLogin(rs.getString(2));
                clientLoginData.setPassword(rs.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return clientLoginData;
    }

    @Override
    public ClientLoginData findClientName(String login) {
        Connection cn = SQLDaoFactory.createConnection();
        ClientLoginData clientLoginData = new ClientLoginData();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_FIND_CLIENT_NAME"));
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                clientLoginData.setLogin(rs.getString(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return clientLoginData;
    }

    public List<AjaxClientLoader> findClientData (String name) {
        List<AjaxClientLoader> loadClientInfoAjaxes = new ArrayList<>();
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;
        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_FIND_CLIENT_DATA"));
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // name, address, passportNumber, dateOfBirth, accNumber, balance, isBlocked
                AjaxClientLoader ajaxClientLoader = new AjaxClientLoader();
                ajaxClientLoader.setName(rs.getString("name"));
                ajaxClientLoader.setAddress(rs.getString("address"));
                ajaxClientLoader.setPassportNumber(rs.getString("passportNumber"));
                ajaxClientLoader.setDateOfBirth(rs.getString("dateOfBirth"));
                ajaxClientLoader.setAccountNumber(rs.getLong("accNumber"));
                ajaxClientLoader.setBalance(rs.getInt("balance"));
                ajaxClientLoader.setBlocked(rs.getBoolean("isBlocked"));
                loadClientInfoAjaxes.add(ajaxClientLoader);
            }
        }
        catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return loadClientInfoAjaxes;
    }

}


