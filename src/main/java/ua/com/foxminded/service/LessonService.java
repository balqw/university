package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.dao.LessonDaoImpl;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.LessonEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonDaoImpl lessonDaoImpl;
    private final ClassRoomDao classRoomDao;

    public LessonEntity save(LessonEntity entity) {
        ClassRoomEntity classRoom = null;
        try {
            classRoom = classRoomDao.findByNumber(entity.getClassRoom().getNumber());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("class not exist");
        }
        entity.setClassRoom(classRoom);
        return lessonDaoImpl.save(entity);

    }

    public List<LessonEntity> readAll() {
        return lessonDaoImpl.readAll();
    }

    public LessonEntity findOne(Integer id) {
        return lessonDaoImpl.findOne(id);
    }

    public LessonEntity update(LessonEntity entity) {
        return lessonDaoImpl.update(entity);
    }

    public void delete(Integer id) {
        lessonDaoImpl.delete(id);
    }
}
