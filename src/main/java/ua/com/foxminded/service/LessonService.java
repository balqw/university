package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.dao.LessonDao;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.LessonEntity;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonDao lessonDao;
    private final ClassRoomDao classRoomDao;


    public LessonEntity save(LessonEntity entity) {
        ClassRoomEntity classRoom = null;
        try {
            classRoom = classRoomDao.findByNumber(entity.getClassRoom().getNumber());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("class not exist");
        }
        entity.setClassRoom(classRoom);
        return lessonDao.save(entity);

    }

    public List<LessonEntity> readAll() {
        return lessonDao.readAll();
    }

    public LessonEntity findOne(Integer id) {
        return lessonDao.findOne(id);
    }

    public LessonEntity update(LessonEntity entity) {
        return lessonDao.update(entity);
    }

    public void delete(Integer id) {
        lessonDao.delete(id);
    }
}
