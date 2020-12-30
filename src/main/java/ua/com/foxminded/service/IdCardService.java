package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.IdCardDao;
import ua.com.foxminded.domain.dao.IdCardDaoHibernate;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import java.util.List;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class IdCardService{
    private final IdCardDaoHibernate idCardDao;


    public IdCardEntity save(IdCardEntity entity) {
        return idCardDao.save(entity);
    }

    public List<IdCardEntity> readAll() {
        return idCardDao.readAll();
    }

    public IdCardEntity findOne(Integer id) {
        return idCardDao.findOne(id);
    }

    public IdCardEntity update(IdCardEntity entity) {
        return idCardDao.update(entity);
    }

    public void delete(Integer id) {
        idCardDao.delete(id);
    }
}
