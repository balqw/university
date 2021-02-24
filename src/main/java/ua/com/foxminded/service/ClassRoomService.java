package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.entity.ClassRoomEntity;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClassRoomService{
    private final ClassRoomDao classRoomDao;


    @Transactional
    public ClassRoomEntity save(ClassRoomEntity entity) {
       if(classRoomDao.exist(entity))
           throw new IllegalArgumentException("classroom already exist");
        return classRoomDao.save(entity);
    }

    @Transactional
    public List<ClassRoomEntity> readAll() {
        return classRoomDao.readAll();
    }

    @Transactional
    public ClassRoomEntity findOne(Integer id) {
        return classRoomDao.findOne(id);
    }

    @Transactional
    public ClassRoomEntity update(ClassRoomEntity entity) {
          return classRoomDao.update(entity);
    }

    @Transactional
    public void delete(Integer id) {
        classRoomDao.delete(id);
    }
}
