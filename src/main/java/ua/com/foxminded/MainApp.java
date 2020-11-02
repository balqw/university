package ua.com.foxminded;


import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitDataBase;
import ua.com.foxminded.config.JdbcTempleFactory;
import ua.com.foxminded.domain.dao.StudentDao;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.sql.Connection;

public class MainApp {

    public static void main(String[] args){

        DBConnection dbConnection = new DBConnection("h2");
        InitDataBase initDataBase = new InitDataBase(dbConnection);
        initDataBase.create();


        JdbcTempleFactory jdbcTemple = new JdbcTempleFactory("h2");
        StudentDao studentDao = new StudentDao(jdbcTemple.getJdbcTemplate());
        studentDao.create(new StudentEntity("first_name","last_name",2));

    }

}
