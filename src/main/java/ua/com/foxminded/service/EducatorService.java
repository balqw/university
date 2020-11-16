package ua.com.foxminded.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.dao.EducatorDao;
import ua.com.foxminded.domain.entity.EducatorEntity;

import java.util.List;
@Service
public class EducatorService implements CrudOperation<EducatorEntity,Integer> {
    private final EducatorDao service;
    @Autowired
    public EducatorService(EducatorDao service) {
        this.service = service;
    }

    @Override
    public EducatorEntity save(EducatorEntity entity) {
        return service.save(entity);
    }

    @Override
    public List<EducatorEntity> readAll() {
        return service.readAll();
    }

    @Override
    public EducatorEntity findOne(Integer id) {
        return service.findOne(id);
    }

    @Override
    public EducatorEntity update(EducatorEntity entity) {
        return service.update(entity);
    }

    @Override
    public void delete(Integer id) {
        service.delete(id);
    }
}
