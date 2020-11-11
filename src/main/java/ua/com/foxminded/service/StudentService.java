package ua.com.foxminded.service;

import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.dao.StudentDao;
import ua.com.foxminded.domain.entity.StudentEntity;
import java.util.List;

public class StudentService implements CrudOperation<StudentEntity,Integer> {
    private final StudentDao service;

    public StudentService(StudentDao service) {
        this.service = service;
    }

    @Override
    public StudentEntity save(StudentEntity entity) {
        return (service.save(entity));
    }

    @Override
    public List<StudentEntity> readAll() {
        return service.readAll();
    }

    @Override
    public StudentEntity findOne(Integer id) {
        return service.findOne(id);
    }

    @Override
    public StudentEntity update(StudentEntity entity) {
        return service.update(entity);
    }

    @Override
    public void delete(Integer id) {
        service.delete(id);
    }
}
