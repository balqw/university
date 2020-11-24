package ua.com.foxminded.domain.entity;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class LessonEntity {

    private int lessonId;
    private String title;
    private LocalDateTime startLesson;
    private LocalDateTime endLesson ;
    private ClassRoomEntity classRoom;

}
