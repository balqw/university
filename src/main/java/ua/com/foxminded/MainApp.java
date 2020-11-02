package ua.com.foxminded;


import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitDataBase;
import ua.com.foxminded.config.JdbcTempleFactory;
import ua.com.foxminded.domain.dao.StudentDao;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.sql.Connection;

import static ua.com.foxminded.Constants.H2_DB;

public class MainApp {

    public static void main(String[] args){

/*        DBConnection dbConnection = new DBConnection(H2_DB);
        InitDataBase initDataBase = new InitDataBase(dbConnection);
        initDataBase.create();*/


        JdbcTempleFactory jdbcTemple = new JdbcTempleFactory(H2_DB);
        StudentDao studentDao = new StudentDao(jdbcTemple.getJdbcTemplate());
        studentDao.create(new StudentEntity("first_name","last_name",2));

    }

}
