package ua.com.foxminded;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.com.foxminded.config.DateBaseInitializer;
import ua.com.foxminded.config.AppConfig;
import ua.com.foxminded.domain.entity.*;
import ua.com.foxminded.service.ClassRoomService;
import ua.com.foxminded.service.LessonService;
import ua.com.foxminded.service.StudentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MainApp {

    public static void main(String[] args){

        //DBConnection dbConnection = new DBConnection();
        //InitDataBase initDataBase = new InitDataBase(dbConnection);
        //initDataBase.create();

/*
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName("Mike");
        studentEntity.setLastName("Gilty");

        StudentService studentService = context.getBean(StudentService.class);
        studentService.save(studentEntity);

        StudentEntity studentEntity2 = new StudentEntity();
        studentEntity2.setFirstName("Sae");
        studentEntity2.setLastName("Mukido");
        studentService.save(studentEntity2);




        context.close()
        */


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    }

}
