package by.epam.payments;

import java.util.ArrayList;
import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private String address;
    private String passport;
    private String dateOfBirth;
    private ArrayList<Long> accounts;

    public Client() {
        this.accounts = new ArrayList<>();
    }

    public Client(int id, String name, String address, String passport, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.passport = passport;
        this.dateOfBirth = dateOfBirth;
    }

  /*  public Client(String name, BankAccount bankAccount) {
        this.name = name;
        this.accounts = new ArrayList<>();
        accounts.add(bankAccount.getAccountNumber());
    }
*/

    public ArrayList<Long> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Long> accounts) {
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


  /*

    void fill() {}                                  //method to replenish bank account

    void block(BankAccount bankAccount) {
        bankAccount.setBlocked(true);
    }   //block bank account

    Payment payment(BankAccount bankAccount, Integer paymentValue) {        //create a payment using bank account and amount of money
                                                                            //realization of payment method is created only for tests
        Payment payment = new Payment(bankAccount, paymentValue);
        System.out.println("Payment Created");

        return bankAccount.isBlocked ? null : payment;
    }

                     */                           //override hashCode and toString methods
    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public String toString() {
        return "Client: " +
                "id - " + id +
                ", name - " + name +
                ", address - " + address +
                ", passport - " + passport +
                ", date - " + dateOfBirth;
    }
}