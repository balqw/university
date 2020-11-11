package ua.com.foxminded.service;

import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.entity.ClassRoomEntity;

import java.util.List;

public class ClassRoomService implements CrudOperation<ClassRoomEntity,Integer> {

    private final ClassRoomDao service;

    public ClassRoomService(ClassRoomDao service) {
        this.service = service;
    }

    @Override
    public ClassRoomEntity save(ClassRoomEntity entity) {
        return service.save(entity);
    }

    @Override
    public List<ClassRoomEntity> readAll() {
        return service.readAll();
    }

    @Override
    public ClassRoomEntity findOne(Integer id) {
        return service.findOne(id);
    }

    @Override
    public ClassRoomEntity update(ClassRoomEntity entity) {
        return service.update(entity);
    }

    @Override
    public void delete(Integer id) {
        service.delete(id);
    }
}
