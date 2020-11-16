package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.dao.EducatorDao;
import ua.com.foxminded.domain.entity.EducatorEntity;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EducatorService implements CrudOperation<EducatorEntity,Integer> {
    private final EducatorDao educatorDao;

    @Override
    public EducatorEntity save(EducatorEntity entity) {
        return educatorDao.save(entity);
    }

    @Override
    public List<EducatorEntity> readAll() {
        return educatorDao.readAll();
    }

    @Override
    public EducatorEntity findOne(Integer id) {
        return educatorDao.findOne(id);
    }

    @Override
    public EducatorEntity update(EducatorEntity entity) {
        return educatorDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        educatorDao.delete(id);
    }
}
