package by.epam.payments;

public class OperationType {
    private String operationType;

    public OperationType() {
    }

    public OperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return "OperationType: " + operationType;
    }
}
