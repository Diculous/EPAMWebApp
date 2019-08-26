package by.epam.interfacesDao;

import by.epam.payments.OperationType;

import java.util.List;

public interface DAOOperationType {
    List<OperationType> findAll();
    boolean insertOperationType(OperationType operationType);
    boolean updateOperationType(OperationType operationType, String oldOperationType);
    boolean deleteOperationType(OperationType operationType);
}
