package ua.com.foxminded.config;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection implements AutoCloseable {
    private static final String PROPERTY_SOURCE = "src/main/resources/application.properties";
    private static final String ERR_MESSAGE = "Couldn't create connection, cause: %s";
    private final String URL;
    private final String LOGIN;
    private final String PASS;
    private final Properties properties;
    private Connection connection;

    public DBConnection(String dbPrefix) {
        URL = dbPrefix + ".url";
        LOGIN = dbPrefix + ".login";
        PASS = dbPrefix + ".pass";
        properties = new Properties();
    }

    public Connection getConnection() {
        if (connection != null) return connection;

        try (FileInputStream fis = new FileInputStream(PROPERTY_SOURCE)) {
            properties.load(fis);
            String url = properties.getProperty(URL);
            String login = properties.getProperty(LOGIN);
            String pass = properties.getProperty(PASS);
            connection = DriverManager.getConnection(url, login, pass);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(String.format(ERR_MESSAGE, e.getLocalizedMessage()));
        }

    }

    @Override
    public void close() throws Exception {
        if (connection != null) connection.close();
    }
}
