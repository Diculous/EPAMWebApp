package by.epam.dao;

import by.epam.interfacesDao.DAOBankAccount;
import by.epam.payments.BankAccount;
import by.epam.util.ConfigurationManager;
import by.epam.util.SQLDaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDao implements DAOBankAccount {
    ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public List<BankAccount> findAll() {
        List<BankAccount> bankAccounts = new ArrayList<>();
        Connection cn = SQLDaoFactory.createConnection();
        Statement st = null;
        Statement st2 = null;
        ArrayList<Long> cards;
        try {
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_ALL_BANK_ACCOUNTS"));
            while (resultSet.next()) {
                st2 = cn.createStatement();
                ResultSet resultSet2 = st2.executeQuery(configurationManager.getPropertySQL("SQL_SELECT_CARDS_FOR_ACCOUNT") + resultSet.getInt("idAccount"));

                cards = new ArrayList<>();
                BankAccount bankAccount = new BankAccount();
                bankAccount.setIdAccount(resultSet.getInt("idAccount"));
                bankAccount.setAccountNumber(resultSet.getLong("accNumber"));
                bankAccount.setBlocked(resultSet.getBoolean("isBlocked"));
                bankAccount.setOwnerId(resultSet.getInt("ownerID"));
                bankAccount.setBalance(resultSet.getInt("balance"));

                while (resultSet2.next()) {
                    cards.add(resultSet2.getLong("cardNumber"));
                }
                bankAccount.setCreditCards(cards);
                bankAccounts.add(bankAccount);
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

        return bankAccounts;
    }

    @Override
    public boolean insertBankAccount(BankAccount bankAccount) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_CREATE_NEW_ACCOUNT"));
            st.setInt(1, bankAccount.getIdAccount());
            st.setLong(2, bankAccount.getAccountNumber());
            st.setBoolean(3, bankAccount.getBlocked());
            st.setInt(4, bankAccount.getOwnerId());
            st.setInt(5, bankAccount.getBalance());
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

    @Override
    public boolean updateBankAccount(BankAccount bankAccount) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_UPDATE_ACCOUNT"));
            st.setLong(1, bankAccount.getAccountNumber());
            st.setBoolean(2, bankAccount.getBlocked());
            st.setInt(3, bankAccount.getOwnerId());
            st.setInt(4, bankAccount.getBalance());
            st.setInt(5, bankAccount.getIdAccount());
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

    @Override
    public boolean deleteBankAccount(BankAccount bankAccount) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_DELETE_ACCOUNT"));
            st.setInt(1, bankAccount.getIdAccount());
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

    @Override
    public boolean blockBankAccount(BankAccount bankAccount) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_BLOCK_ACCOUNT"));
            st.setLong(1, bankAccount.getAccountNumber());
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

    @Override
    public BankAccount findByNumber(long accountNumber) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        BankAccount bankAccount = new BankAccount();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_FIND_ACCOUNT"));
            st.setLong(1, accountNumber);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                bankAccount.setBalance(rs.getInt(1));
            }
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
        return bankAccount;
    }

    @Override
    public boolean changeBalance(long accNumber, int balance) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_CHANGE_BALANCE"));
            st.setLong(1, balance);
            st.setLong(2, accNumber);
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

    @Override
    public boolean unblock(BankAccount bankAccount) {
        boolean flag = false;
        Connection cn = SQLDaoFactory.createConnection();
        PreparedStatement st = null;

        try {
            st = cn.prepareStatement(configurationManager.getPropertySQL("SQL_UNBLOCK_ACCOUNT"));
            st.setLong(1, bankAccount.getAccountNumber());
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
}
