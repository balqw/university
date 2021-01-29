package ua.com.foxminded.domain.entity.dto;

import lombok.Data;
import ua.com.foxminded.domain.entity.ClassRoomEntity;

import java.time.LocalDateTime;

@Data
public class LessonDTO {
    private Integer lessonId;
    private String title;
    private LocalDateTime startLesson;
    private LocalDateTime endLesson;
    private ClassRoomEntity classRoom;
}
