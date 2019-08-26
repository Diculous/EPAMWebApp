package by.epam.dao;

import by.epam.interfacesDao.DAOOperationType;
import by.epam.payments.OperationType;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationTypeDao implements DAOOperationType {
    ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    public List<OperationType> findAll() {
        List<OperationType> operationTypes = new ArrayList<>();
        Connection cn = SQLDaoFactory.createConnection();
        Statement st = null;
        try {
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_TYPES_OF_PAYMENTS"));
            while (resultSet.next()) {
                OperationType operationType = new OperationType();
                operationType.setOperationType(resultSet.getString("OperationType"));
                operationTypes.add(operationType);
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
        return operationTypes;
    }

    public boolean insertOperationType(OperationType operationType) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_NEW_PAYMENT_TYPE"));
            st.setString(1, operationType.getOperationType());
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

    public boolean updateOperationType(OperationType operationType, String oldOperationType) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_PAYMENT_TYPE"));
            st.setString(1, operationType.getOperationType());
            st.setString(2, oldOperationType);
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

    public boolean deleteOperationType(OperationType operationType) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_PAYMENT_TYPE"));
            st.setString(1, operationType.getOperationType());
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