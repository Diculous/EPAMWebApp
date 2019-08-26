package by.epam.payments;

public class CardType {
    private String cardType;
    private int cashBack;

    public CardType() {
    }

    public CardType(String cardType, int cashBack) {
        this.cardType = cardType;
        this.cashBack = cashBack;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getCashBack() {
        return cashBack;
    }

    public void setCashBack(int cashBack) {
        this.cashBack = cashBack;
    }

    @Override
    public String toString() {
        return "CardType: " +
               "Type - " + cardType +
               ", cashback - " + cashBack;
    }
}
