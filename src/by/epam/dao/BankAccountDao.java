package by.epam.dao;

import by.epam.interfacesDao.DAOBankAccount;
import by.epam.payments.BankAccount;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDao implements DAOBankAccount {
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private static final Logger logger = Logger.getLogger(BankAccountDao.class);

    @Override
    public List<BankAccount> findAll() {
        List<BankAccount> bankAccounts = new ArrayList<>();
        Connection connection = SQLDaoFactory.createConnection();
        Statement statementAccount = null;
        Statement statementCards = null;
        ArrayList<Long> cards;
        try {
            statementAccount = connection.createStatement();
            ResultSet resultSetAccounts = statementAccount.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_BANK_ACCOUNTS"));
            while (resultSetAccounts.next()) {
                statementCards = connection.createStatement();
                ResultSet resultSetCards = statementCards.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_CARDS_FOR_ACCOUNT") + resultSetAccounts.getInt("idAccount"));

                cards = new ArrayList<>();
                BankAccount bankAccount = new BankAccount();
                bankAccount.setIdAccount(resultSetAccounts.getInt("idAccount"));
                bankAccount.setAccountNumber(resultSetAccounts.getLong("accNumber"));
                bankAccount.setBlocked(resultSetAccounts.getBoolean("isBlocked"));
                bankAccount.setOwnerId(resultSetAccounts.getInt("ownerID"));
                bankAccount.setBalance(resultSetAccounts.getInt("balance"));

                while (resultSetCards.next()) {
                    cards.add(resultSetCards.getLong("cardNumber"));
                }
                bankAccount.setCreditCards(cards);
                bankAccounts.add(bankAccount);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            try {
                statementAccount.close();
                statementCards.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }

        return bankAccounts;
    }

    @Override
    public boolean insertBankAccount(BankAccount bankAccount) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_NEW_ACCOUNT"));
            preparedStatement.setInt(1, bankAccount.getIdAccount());
            preparedStatement.setLong(2, bankAccount.getAccountNumber());
            preparedStatement.setBoolean(3, bankAccount.getBlocked());
            preparedStatement.setInt(4, bankAccount.getOwnerId());
            preparedStatement.setInt(5, bankAccount.getBalance());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return flag;
    }

    @Override
    public boolean updateBankAccount(BankAccount bankAccount) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_ACCOUNT"));
            preparedStatement.setLong(1, bankAccount.getAccountNumber());
            preparedStatement.setBoolean(2, bankAccount.getBlocked());
            preparedStatement.setInt(3, bankAccount.getOwnerId());
            preparedStatement.setInt(4, bankAccount.getBalance());
            preparedStatement.setInt(5, bankAccount.getIdAccount());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return flag;
    }

    @Override
    public boolean deleteBankAccount(BankAccount bankAccount) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_ACCOUNT"));
            preparedStatement.setInt(1, bankAccount.getIdAccount());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return flag;
    }

    @Override
    public boolean blockBankAccount(BankAccount bankAccount) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_BLOCK_ACCOUNT"));
            preparedStatement.setLong(1, bankAccount.getAccountNumber());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return flag;
    }

    @Override
    public BankAccount findByNumber(long accountNumber) {
        Connection connection = SQLDaoFactory.createConnection();
        BankAccount bankAccount = new BankAccount();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_FIND_ACCOUNT"));
            preparedStatement.setLong(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bankAccount.setBalance(resultSet.getInt(1));
                bankAccount.setBlocked(resultSet.getBoolean(2));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return bankAccount;
    }

    @Override
    public boolean changeBalance(long accNumber, int balance) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_CHANGE_BALANCE"));
            preparedStatement.setLong(1, balance);
            preparedStatement.setLong(2, accNumber);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return flag;

    }

    @Override
    public boolean unblock(BankAccount bankAccount) {
        boolean flag = false;
        Connection connection = SQLDaoFactory.createConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(configurationManager.getPropertySQL("SQL_UNBLOCK_ACCOUNT"));
            preparedStatement.setLong(1, bankAccount.getAccountNumber());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
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
