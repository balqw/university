package ua.com.foxminded.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.dao.LessonDao;
import ua.com.foxminded.domain.entity.LessonEntity;

import java.util.List;
@Service
public class LessonService implements CrudOperation<LessonEntity,Integer> {
    private final LessonDao lessonDao;
    @Autowired
    public LessonService(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public LessonEntity save(LessonEntity entity) {
        return lessonDao.save(entity);
    }

    @Override
    public List<LessonEntity> readAll() {
        return lessonDao.readAll();
    }

    @Override
    public LessonEntity findOne(Integer id) {
        return lessonDao.findOne(id);
    }

    @Override
    public LessonEntity update(LessonEntity entity) {
        return lessonDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        lessonDao.delete(id);
    }
}
