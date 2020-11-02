package ua.com.foxminded.config;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class InitDataBase {
    private final DBConnection dbConnection;

    public InitDataBase (DBConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public void create(){
        try(Connection connection = dbConnection.getConnection()) {
            Reader reader = new BufferedReader(new FileReader("src/main/resources/init.sql"));
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(reader);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("DB connection failed");
        }

    }

}
