package by.epam.interfacesDao;

import by.epam.payments.BankAccount;

import java.util.List;

public interface DAOBankAccount {
    List<BankAccount> findAll();
    boolean insertBankAccount(BankAccount bankAccount);
    boolean updateBankAccount(BankAccount bankAccount);
    boolean deleteBankAccount(BankAccount bankAccount);
    boolean blockBankAccount(BankAccount bankAccount);
    BankAccount findByNumber(long accountNumber);
    boolean changeBalance(long accNumber , int balance);
    boolean unblock(BankAccount bankAccount);
}
