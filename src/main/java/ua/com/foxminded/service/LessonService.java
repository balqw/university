package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.LessonDao;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonDao lessonDao;


    public LessonEntity save(LessonEntity entity) {
        return lessonDao.save(entity);
    }

    public List<LessonEntity> readAll() {
        return lessonDao.readAll();
    }

    public LessonEntity findOne(Integer id) {
        return lessonDao.findOne(id);
    }

    public LessonEntity update(LessonEntity entity) {
        if(lessonDao.exist(entity))
            return lessonDao.update(entity);

        throw new NotFoundException(format("lesson with id = '%s' not exist",entity.getLessonId()));
    }

    public void delete(Integer id) {
        lessonDao.delete(id);
    }
}
