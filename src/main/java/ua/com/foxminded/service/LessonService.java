package ua.com.foxminded.service;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.dao.LessonDaoImpl;
import ua.com.foxminded.domain.dao.StudentDaoImpl;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.dto.LessonInfo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonDaoImpl lessonDaoImpl;
    private final ClassRoomDao classRoomDao;
    private final StudentDaoImpl studentDao;

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

    public LessonInfo getLessonInfo(Integer groupId){
        List<StudentEntity>students = studentDao.findByGroup(groupId);
     return null;
    }
}
