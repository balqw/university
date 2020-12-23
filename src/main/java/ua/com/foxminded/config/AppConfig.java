package ua.com.foxminded.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("ua.com.foxminded.*")
@EnableTransactionManagement
public class AppConfig  {

    @Autowired
    private Environment environment;
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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf
                = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan(new String[] { "ua.com.foxminded.domain.entity" });
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(adapter);
        emf.setJpaProperties(jpaProperties());
        return emf;
    }

    public Properties jpaProperties(){
       Properties properties = new Properties();
       properties.put("hibernate.hbm2ddl.auto",environment.getProperty("hibernate.hbm2ddl.auto"));
       properties.put("hibernate.dialect",environment.getProperty("hibernate.dialect"));
       properties.put("hibernate.show_sql",environment.getProperty("hibernate.show_sql"));
       properties.put("hibernate.format_sql",environment.getProperty("hibernate.format_sql"));
        return properties;
    }


    @Bean
    public EntityManager getEntityManager(LocalContainerEntityManagerFactoryBean emf){
        EntityManager em = emf.getNativeEntityManagerFactory().createEntityManager();
        return  em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public JdbcTemplate JdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
