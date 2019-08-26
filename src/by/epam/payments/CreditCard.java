package by.epam.payments;

import java.util.Objects;

public class CreditCard {
    private Long cardNumber;            //card number
    private Long account;        //bank account linked to the credit card
    private String cardType;
    public CreditCard() {
    }

    public CreditCard(Long cardNumber, Long account, String cardType) {
        this.cardNumber = cardNumber;
        this.account = account;
        this.cardType = cardType;
    }

    // getters and setters
    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override                                               //override hashCode and toString methods
    public int hashCode() {
        return Objects.hash(cardNumber);
    }

    @Override
    public String toString() {
        return "CreditCard: " +
                "cardNumber - " + cardNumber +
                ", account - " + account +
                ", cardType - " + cardType;
    }
}