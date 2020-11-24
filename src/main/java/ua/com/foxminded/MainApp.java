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


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DateBaseInitializer prepareDB = context.getBean(DateBaseInitializer.class);
        prepareDB.run();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName("23");
        studentEntity.setLastName("tes23t");

        StudentService studentService = context.getBean(StudentService.class);
        studentService.save(studentEntity);

        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setStartLesson(LocalDateTime.now());
        lessonEntity.setEndLesson(LocalDateTime.now());

        LessonService lessonService = context.getBean(LessonService.class);

        List<LessonEntity> lessons
         = lessonService.readAll();
        System.out.println(lessons);
        ClassRoomService roomService = context.getBean(ClassRoomService.class);
        ClassRoomEntity classRoomEntity = new ClassRoomEntity();
        classRoomEntity = roomService.save(classRoomEntity);
        lessonEntity.setClassRoom(classRoomEntity);
        lessonEntity = lessonService.save(lessonEntity);
        lessonService.update(lessonEntity);
        System.out.println(lessonService.findOne(1));



        context.close();
    }

}
