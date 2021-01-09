package ua.com.foxminded.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lesson")
public class LessonEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer lessonId;

    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startLesson;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endLesson;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classRoom", referencedColumnName = "number")
    private ClassRoomEntity classRoom;

}
