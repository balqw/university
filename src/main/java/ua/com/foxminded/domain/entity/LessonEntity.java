package ua.com.foxminded.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "lesson")
public class LessonEntity {
    @Id
    @GeneratedValue
    private Integer lessonId;

    private String title;

    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    private LocalDateTime startLesson;

    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    private LocalDateTime endLesson ;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classRoom", referencedColumnName = "number")
    private ClassRoomEntity classRoom;

}
