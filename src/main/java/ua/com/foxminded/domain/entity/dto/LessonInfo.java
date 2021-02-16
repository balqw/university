package ua.com.foxminded.domain.entity.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class LessonInfo {
    private String title;
    private LocalDateTime startLesson;
    private LocalDateTime endLesson;
    private ClassRoomEntity classRoom;
    private List<StudentEntity>students;
}
