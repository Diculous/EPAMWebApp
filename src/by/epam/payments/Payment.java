package by.epam.payments;

import java.util.Objects;

public class Payment {
    private Long bankAccount;        //used bank account
    private Integer paymentValue;           //amount of money used in payment
    private String paymentType;

    public Payment() {
    }

    public Payment(Long bankAccount, Integer paymentValue, String paymentType) {
        this.bankAccount = bankAccount;
        this.paymentValue = paymentValue;
        this.paymentType = paymentType;
    }
                                                                        //getters and setters
    public Long getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Long bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(Integer paymentValue) {
        this.paymentValue = paymentValue;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override                                                                 //override hashCode and toString methods
    public int hashCode() {
        return Objects.hash(bankAccount, paymentValue);
    }

    @Override
    public String toString() {
        return "Payment: " +
                " bank account - " + bankAccount +
                ", payment type - " + paymentType +
                ", paymentValue - " + paymentValue;
    }
}
