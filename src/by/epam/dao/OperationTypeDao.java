package by.epam.dao;

import by.epam.interfacesDao.DAOOperationType;
import by.epam.payments.OperationType;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationTypeDao implements DAOOperationType {
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private static final Logger logger = Logger.getLogger(OperationTypeDao.class);

    public List<OperationType> findAll() {
        List<OperationType> operationTypes = new ArrayList<>();
        Connection connection = SQLDaoFactory.createConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_TYPES_OF_PAYMENTS"));
            while (resultSet.next()) {
                OperationType operationType = new OperationType();
                operationType.setOperationType(resultSet.getString("OperationType"));
                operationTypes.add(operationType);
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
        return operationTypes;
    }

    public boolean insertOperationType(OperationType operationType) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_NEW_PAYMENT_TYPE"));
            preparedStatement.setString(1, operationType.getOperationType());
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

    public boolean updateOperationType(OperationType operationType, String oldOperationType) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_PAYMENT_TYPE"));
            preparedStatement.setString(1, operationType.getOperationType());
            preparedStatement.setString(2, oldOperationType);
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

    public boolean deleteOperationType(OperationType operationType) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_PAYMENT_TYPE"));
            preparedStatement.setString(1, operationType.getOperationType());
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