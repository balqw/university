package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.dao.IdCardDao;
import ua.com.foxminded.domain.entity.IdCardEntity;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IdCardService {
    private final IdCardDao idCardDao;


    @Transactional
    public IdCardEntity save(IdCardEntity entity) {
        return idCardDao.save(entity);
    }

    @Transactional
    public List<IdCardEntity> readAll() {
        return idCardDao.readAll();
    }

    @Transactional
    public IdCardEntity findOne(Integer id) {
        return idCardDao.findOne(id);
    }

    @Transactional
    public IdCardEntity update(IdCardEntity entity) {
        return idCardDao.update(entity);
    }

    @Transactional
    public void delete(Integer id) {
        idCardDao.delete(id);
    }
}
