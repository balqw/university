package ua.com.foxminded.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.domain.entity.LessonEntity;

import java.time.LocalDateTime;

public interface LessonRepository extends JpaRepository<LessonEntity,Integer> {
    Boolean existsLessonEntityByTitleAndStartLesson(String title, LocalDateTime start);
}
