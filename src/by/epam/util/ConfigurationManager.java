package by.epam.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigurationManager {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("by/epam/connectionConfig");
    private static final ResourceBundle resourceBundleSQL = ResourceBundle.getBundle("by/epam/SQLConfig");
    private static ConfigurationManager instance;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (instance == null)
            instance = new ConfigurationManager();
        return instance;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
    public String getPropertySQL(String key) {
        return resourceBundleSQL.getString(key);
    }
}