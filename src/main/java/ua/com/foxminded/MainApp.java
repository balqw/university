package ua.com.foxminded;


import org.h2.command.Parser;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitDataBase;
import ua.com.foxminded.config.JdbcTempleFactory;
import ua.com.foxminded.domain.dao.*;
import ua.com.foxminded.domain.entity.*;

import java.sql.Connection;
import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ua.com.foxminded.Constants.H2_DB;
import static ua.com.foxminded.Constants.POSTGRES_DB;

public class MainApp {

    public static void main(String[] args){

        DBConnection dbConnection = new DBConnection(POSTGRES_DB);
        InitDataBase initDataBase = new InitDataBase(dbConnection);
        initDataBase.create();
        JdbcTemplate jdbcTemplate = new JdbcTempleFactory(POSTGRES_DB).getJdbcTemplate();
        StudentDao studentDao = new StudentDao(jdbcTemplate);
        IdCardDao idCardDao = new IdCardDao(jdbcTemplate);
        EducatorDao educatorDao = new EducatorDao(jdbcTemplate);
        ClassRoomDao classRoomDao = new ClassRoomDao(jdbcTemplate);
        LessonDao lessonDao = new LessonDao(jdbcTemplate);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        EducatorEntity educator1 = new EducatorEntity();
        educator1.setFirstName("John");
        educator1.setLastName("Mathews");
        educator1 = educatorDao.save(educator1);

        System.out.println(educator1.getIdCard());

        IdCardEntity idCardEntity = new IdCardEntity();
        idCardEntity.setDataExpire(LocalDateTime.parse("2000-01-01 00:00",dtf));
        educator1.setIdCard(idCardEntity);
        idCardEntity = idCardDao.save(idCardEntity);

        educatorDao.setIdCard(educator1);

        System.out.println(educator1.getIdCard());

        ClassRoomEntity classRoomEntity = new ClassRoomEntity();

        classRoomEntity.setNumber(2);
        classRoomEntity =  classRoomDao.save(classRoomEntity);
        System.out.println(classRoomEntity);

        ClassRoomEntity classRoomEntity1 = new ClassRoomEntity();
        classRoomEntity1.setNumber(5);
        classRoomEntity1 = classRoomDao.save(classRoomEntity1);


        LessonEntity lessonEntity1 = new LessonEntity();
        lessonEntity1.setTitle("math");
        lessonEntity1.setStartLesson(LocalDateTime.parse("2000-10-10 00:00",dtf));
        lessonEntity1.setEndLesson(LocalDateTime.parse("2000-10-10 01:00",dtf));
        //lessonEntity1.setClassRoom(classRoomEntity);

        System.out.println(lessonEntity1 = lessonDao.save(lessonEntity1));

        System.out.println(lessonDao.findOne(1));



    }

}
