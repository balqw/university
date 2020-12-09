package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.StudentDao;
import ua.com.foxminded.domain.entity.StudentEntity;
import java.util.List;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class StudentService  {
    private final StudentDao studentDao;


    public StudentEntity save(StudentEntity entity) {
        if(studentDao.isExist(entity.getFirstName(),entity.getLastName()))
            throw  new IllegalArgumentException("student already exist");
        return studentDao.save(entity);
    }


    public List<StudentEntity> readAll() {
        return studentDao.readAll();
    }


    public StudentEntity findOne(Integer id) {
        return studentDao.findOne(id);
    }


    public StudentEntity update(StudentEntity entity) {
        if(studentDao.isExist(entity.getStudentId()))
            return studentDao.update(entity);

        throw new IllegalArgumentException(format("student with id = '%s' not exist",entity.getStudentId()));
    }


    public void delete(Integer id) {
        studentDao.delete(id);
    }


}
