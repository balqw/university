package ua.com.foxminded.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LessonEntity {
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private ClassRoomEntity classRoom;
}
