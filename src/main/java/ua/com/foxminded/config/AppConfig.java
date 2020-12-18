package ua.com.foxminded.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("ua.com.foxminded.*")
@EnableTransactionManagement
public class AppConfig  {

    @Autowired
    Environment environment;



    private final String URL = "url";
    private final String USER = "user";
    private final String PASS = "pass";
    private final String DRIVER = "driver";



/*
   @Bean
    DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty(DRIVER));
        dataSource.setUrl(environment.getProperty(URL));
        dataSource.setUsername(environment.getProperty(USER));
        dataSource.setPassword(environment.getProperty(PASS));
        return  dataSource;
    }
*/

    @Bean
    public DataSource dataSource() throws NamingException{
        return (DataSource) new JndiTemplate().lookup("jdbc.url");
    }


    @Bean
    JdbcTemplate jdbcTemplate() throws NamingException{
        return  new JdbcTemplate(dataSource());
    }




}
