package ua.com.foxminded.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.foxminded.domain.entity.ClassRoomEntity;

import java.time.LocalDateTime;

@Data
public class LessonInfo {
    private Integer lessonId;
    private String title;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startLesson;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endLesson;
    private ClassRoomEntity classRoom;
}
