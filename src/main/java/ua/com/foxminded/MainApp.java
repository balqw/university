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

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    }

}
