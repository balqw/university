package ua.com.foxminded.service;

import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.dao.IdCardDao;
import ua.com.foxminded.domain.entity.IdCardEntity;

import java.util.List;

public class IdCardService implements CrudOperation<IdCardEntity,Integer> {
    private final IdCardDao service;

    public IdCardService(IdCardDao service) {
        this.service = service;
    }

    @Override
    public IdCardEntity save(IdCardEntity entity) {
        return service.save(entity);
    }

    @Override
    public List<IdCardEntity> readAll() {
        return service.readAll();
    }

    @Override
    public IdCardEntity findOne(Integer id) {
        return service.findOne(id);
    }

    @Override
    public IdCardEntity update(IdCardEntity entity) {
        return service.update(entity);
    }

    @Override
    public void delete(Integer id) {
        service.delete(id);
    }
}
