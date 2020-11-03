package ua.com.foxminded;


import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitDataBase;
import ua.com.foxminded.config.JdbcTempleFactory;
import ua.com.foxminded.domain.dao.StudentDao;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.sql.Connection;

import static ua.com.foxminded.Constants.H2_DB;
import static ua.com.foxminded.Constants.POSTGRES_DB;

public class MainApp {

    public static void main(String[] args){

        DBConnection dbConnection = new DBConnection(POSTGRES_DB);
        InitDataBase initDataBase = new InitDataBase(dbConnection);
        initDataBase.create();


        JdbcTempleFactory jdbcTemple = new JdbcTempleFactory(POSTGRES_DB);
        StudentDao studentDao = new StudentDao(jdbcTemple.getJdbcTemplate());
        studentDao.insert(new StudentEntity("first_name1","1",2));
        studentDao.insert(new StudentEntity("first_name2","2",24));
        System.out.println(studentDao.read(2));


    }

}
