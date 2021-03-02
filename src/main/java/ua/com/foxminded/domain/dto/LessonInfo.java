package ua.com.foxminded.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.foxminded.domain.entity.ClassRoomEntity;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class LessonInfo {

    private Integer lessonId;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Title must be between 2 nad 20")
    private String title;

    @FutureOrPresent(message = "Date must be future or present")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startLesson;

    @Future(message = "Date must be future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endLesson;

    private ClassRoomEntity classRoom;
}
