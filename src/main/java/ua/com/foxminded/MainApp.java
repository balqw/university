package ua.com.foxminded;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.com.foxminded.config.PrepareDB;
import ua.com.foxminded.config.SpringConfig;
import ua.com.foxminded.domain.entity.*;
import ua.com.foxminded.service.StudentService;

import java.time.format.DateTimeFormatter;


public class MainApp {

    public static void main(String[] args){

        //DBConnection dbConnection = new DBConnection();
        //InitDataBase initDataBase = new InitDataBase(dbConnection);
        //initDataBase.create();


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PrepareDB prepareDB = context.getBean(PrepareDB.class);
        prepareDB.run();
        StudentEntity studentEntity = context.getBean(StudentEntity.class);
        studentEntity.setFirstName("23");
        studentEntity.setLastName("tes23t");

        StudentService studentService = context.getBean(StudentService.class);
        studentService.save(studentEntity);




    }

}
