package ua.com.foxminded.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;

@Configuration
@ComponentScan("ua.com.foxminded.*")
@PropertySource("classpath:application.properties")
public class SpringConfig {

    @Autowired
    Environment environment;
    private final String URL = "url";
    private final String USER = "user";
    private final String PASS = "pass";
    private final String DRIVER = "driver";

    @Bean
    DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty(DRIVER));
        dataSource.setUrl(environment.getProperty(URL));
        dataSource.setUsername(environment.getProperty(USER));
        dataSource.setPassword(environment.getProperty(PASS));
        return  dataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(){
        return  new JdbcTemplate(dataSource());
    }
}
