package by.epam.interfacesDao;

import by.epam.payments.ClientLoginData;
import by.epam.util.AjaxClientLoader;

import java.util.List;

public interface DAOClientLoginData {
    ClientLoginData findByLoginAndPassword(String login, String password);
    ClientLoginData findClientName(String login);
    List<AjaxClientLoader> findClientData (String name);
}
