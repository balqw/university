package ua.com.foxminded.service;

import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.dao.LessonDao;
import ua.com.foxminded.domain.entity.LessonEntity;

import java.util.List;

public class LessonService implements CrudOperation<LessonEntity,Integer> {
    private final LessonDao service;

    public LessonService(LessonDao service) {
        this.service = service;
    }

    @Override
    public LessonEntity save(LessonEntity entity) {
        return service.save(entity);
    }

    @Override
    public List<LessonEntity> readAll() {
        return service.readAll();
    }

    @Override
    public LessonEntity findOne(Integer id) {
        return service.findOne(id);
    }

    @Override
    public LessonEntity update(LessonEntity entity) {
        return service.update(entity);
    }

    @Override
    public void delete(Integer id) {
        service.delete(id);
    }
}
