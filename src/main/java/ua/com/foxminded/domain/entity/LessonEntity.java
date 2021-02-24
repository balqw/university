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
    @Column(name = "lesson_id")
    private Integer lessonId;

    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "start_lesson")
    private LocalDateTime startLesson;

    @Column(name = "end_lesson")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endLesson;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_room", referencedColumnName = "number")
    private ClassRoomEntity classRoom;
}
