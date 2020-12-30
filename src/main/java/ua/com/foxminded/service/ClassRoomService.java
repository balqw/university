package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.dao.ClassRoomDaoHibernate;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClassRoomService{
    private final ClassRoomDaoHibernate classRoomDao;


    public ClassRoomEntity save(ClassRoomEntity entity) {
        return classRoomDao.save(entity);
    }

    public List<ClassRoomEntity> readAll() {
        return classRoomDao.readAll();
    }

    public ClassRoomEntity findOne(Integer id) {
        return classRoomDao.findOne(id);
    }

    public ClassRoomEntity update(ClassRoomEntity entity) {
          return classRoomDao.update(entity);
    }

    public void delete(Integer id) {
        classRoomDao.delete(id);
    }
}
