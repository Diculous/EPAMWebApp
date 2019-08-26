package by.epam.util;

public class AjaxClientLoader{
    // name, address, passportNumber, dateOfBirth, accNumber, balance, isBlocked
    private String name;
    private String address;
    private String passportNumber;
    private String dateOfBirth;
    private Long accountNumber;
    private int balance;
    private Boolean isBlocked = false;

    public AjaxClientLoader() {
    }

    public AjaxClientLoader(String name, String address, String passportNumber, String dateOfBirth, Long accountNumber, int balance, Boolean isBlocked) {
        this.name = name;
        this.address = address;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.isBlocked = isBlocked;
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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}