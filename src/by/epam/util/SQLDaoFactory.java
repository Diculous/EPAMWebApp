package by.epam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDaoFactory {
    private static Connection connection = null;


    public static Connection createConnection() {
        try {
            ConfigurationManager configurationManager = ConfigurationManager.getInstance();
            Class.forName(configurationManager.getProperty("DRIVER"));
            connection = DriverManager.getConnection(configurationManager.getProperty("URL"), configurationManager.getProperty("USER"), configurationManager.getProperty("PASSWORD"));
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}