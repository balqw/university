package ua.com.foxminded.domain.entity.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LessonInfo {
    private Integer lessonId;
    private String title;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startLesson;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endLesson;
    private ClassRoomEntity classRoom;
    private List<Group>groupList;
}
