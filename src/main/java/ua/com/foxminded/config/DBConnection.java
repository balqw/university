package ua.com.foxminded.config;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private final String URL;
    private final String LOGIN;
    private final String PASS;
    private final Properties properties = new Properties();
    private  static final String PROPERTY_SOURCE = "src/main/resources/application.properties";
    private static final String ERR_MESSAGE = "Couldn't create connection, cause: %s";

    public DBConnection(String dbPrefix) {
        URL = dbPrefix+".url";
        LOGIN = dbPrefix+".login";
        PASS = dbPrefix+".pass";
    }

    public Connection getConnection(){

        try(FileInputStream fis = new FileInputStream(PROPERTY_SOURCE)){

            properties.load(fis);
            String url = properties.getProperty(URL);
            String login = properties.getProperty(LOGIN);
            String pass = properties.getProperty(PASS);

            return DriverManager.getConnection(url,login,pass);
        }catch (Exception e){
            throw new RuntimeException(String.format(ERR_MESSAGE, e.getLocalizedMessage()));
        }

    }
}
