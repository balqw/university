package ua.com.foxminded.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.entity.ClassRoomEntity;

import java.util.List;

@Service
public class ClassRoomService implements CrudOperation<ClassRoomEntity,Integer> {

    private final ClassRoomDao classRoomDao;
    @Autowired
    public ClassRoomService(ClassRoomDao classRoomDao) {
        this.classRoomDao = classRoomDao;
    }

    @Override
    public ClassRoomEntity save(ClassRoomEntity entity) {
        return classRoomDao.save(entity);
    }

    @Override
    public List<ClassRoomEntity> readAll() {
        return classRoomDao.readAll();
    }

    @Override
    public ClassRoomEntity findOne(Integer id) {
        return classRoomDao.findOne(id);
    }

    @Override
    public ClassRoomEntity update(ClassRoomEntity entity) {
        return classRoomDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        classRoomDao.delete(id);
    }
}
