package by.epam.dao;

import by.epam.interfacesDao.DAOPayment;
import by.epam.payments.Payment;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao implements DAOPayment {
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private static final Logger logger = Logger.getLogger(PaymentDao.class);

    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        Connection connection = SQLDaoFactory.createConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_PAYMENTS"));
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentValue(resultSet.getInt("paymentValue"));
                payment.setBankAccount(resultSet.getLong("accNumber"));
                payment.setPaymentType(resultSet.getString("operationType"));
                payments.add(payment);
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
        return payments;
    }

    public boolean insertPayment(Payment payment) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_PAYMENT"));
            preparedStatement.setInt(1, payment.getPaymentValue());
            preparedStatement.setString(2, payment.getPaymentType());
            preparedStatement.setLong(3, payment.getBankAccount());
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

    public boolean updatePayment(Payment payment, Integer id) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_PAYMENT"));
            preparedStatement.setInt(1, payment.getPaymentValue());
            preparedStatement.setString(2, payment.getPaymentType());
            preparedStatement.setLong(3, payment.getBankAccount());
            preparedStatement.setInt(4, id);
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

    public boolean deletePayment(Integer id) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_PAYMENT"));
            preparedStatement.setInt(1, id);
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