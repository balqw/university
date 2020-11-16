package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.dao.StudentDao;
import ua.com.foxminded.domain.entity.StudentEntity;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService implements CrudOperation<StudentEntity,Integer> {
    private final StudentDao studentDao;


    @Override
    public StudentEntity save(StudentEntity entity) {
        return (studentDao.save(entity));
    }

    @Override
    public List<StudentEntity> readAll() {
        return studentDao.readAll();
    }

    @Override
    public StudentEntity findOne(Integer id) {
        return studentDao.findOne(id);
    }

    @Override
    public StudentEntity update(StudentEntity entity) {
        return studentDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        studentDao.delete(id);
    }
}
