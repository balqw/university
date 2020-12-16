package ua.com.foxminded.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
public class LessonEntity {
    private Integer lessonId;
    private String title;
    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    private LocalDateTime startLesson;
    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    private LocalDateTime endLesson ;
    private ClassRoomEntity classRoom;

}
