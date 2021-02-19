package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.dao.LessonDaoImpl;
import ua.com.foxminded.domain.dao.StudentDaoImpl;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.dto.LessonInfo;
import ua.com.foxminded.domain.entity.mappers.LessonInfoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonDaoImpl lessonDaoImpl;
    private final ClassRoomDao classRoomDao;
    private final StudentDaoImpl studentDao;
    private final LessonInfoMapper lessonInfoMapper;

    public LessonInfo save(LessonInfo lessonInfo) {
        ClassRoomEntity classRoom = null;

        try {
            classRoom = classRoomDao.findByNumber(lessonInfo.getClassRoom().getNumber());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("class not exist");
        }
        lessonInfo.setClassRoom(classRoom);


        LessonEntity lessonEntity = lessonInfoMapper.toEntity(lessonInfo);

        lessonDaoImpl.save(lessonEntity);

        return lessonInfo;

    }

    public List<LessonInfo> readAll() {
        return lessonInfoMapper.toDtos(lessonDaoImpl.readAll());
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

    public LessonInfo getLessonInfo(Integer groupId) {
        List<StudentEntity> students = studentDao.findByGroup(groupId);
        return null;
    }

    public void setGroup(Integer idGroup, Integer idLesson){
        lessonDaoImpl.setGroup(idLesson,idGroup);
    }
}
