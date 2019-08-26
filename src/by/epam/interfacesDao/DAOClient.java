package by.epam.interfacesDao;

import by.epam.payments.Client;

import java.util.List;

public interface DAOClient {
    List<Client> findAll();
    boolean insertClient(Client client);
    boolean updateClient(Client client);
    boolean deleteClient(Client client);
}
