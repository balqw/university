package ua.com.foxminded.config;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DateBaseInitializer {
    private final DataSource dataSource;

    @Autowired
    public DateBaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void run(){
        try(Connection connection = dataSource.getConnection()) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader("src/main/resources/init.sql"));
            scriptRunner.runScript(reader);
        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
