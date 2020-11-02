package ua.com.foxminded.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class JdbcTempleFactory {
    private final String DRIVER;
    private  final String URL;
    private  final String LOGIN;
    private  final String PASSWORD;
    private JdbcTemplate jdbcTemplate;
    private  static final String PROPERTY_SOURCE = "src/main/resources/application.properties";

    public JdbcTempleFactory(String dbPrefix) {
        DRIVER = dbPrefix+".driver";
        URL = dbPrefix+".url";
        LOGIN = dbPrefix+".login";
        PASSWORD = dbPrefix+".pass";
    }

    public JdbcTemplate getJdbcTemplate(){

        try(InputStream inStream = new FileInputStream(PROPERTY_SOURCE)) {

            Properties properties = new Properties();
            properties.load(inStream);
            DriverManagerDataSource dataSource = new DriverManagerDataSource(
                    properties.getProperty(URL),
                    properties.getProperty(LOGIN),
                    properties.getProperty(PASSWORD));
            dataSource.setDriverClassName("org.h2.Driver");
            this.jdbcTemplate = new JdbcTemplate(dataSource);

        } catch (Exception e) {
            throw new RuntimeException( e.getLocalizedMessage());
        }
        return jdbcTemplate;
    }


}
