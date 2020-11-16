package ua.com.foxminded.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.CrudOperation;
import ua.com.foxminded.domain.dao.IdCardDao;
import ua.com.foxminded.domain.entity.IdCardEntity;
import java.util.List;
@RequiredArgsConstructor
@Service
public class IdCardService implements CrudOperation<IdCardEntity,Integer> {

    private final IdCardDao idCardDao;

    @Override
    public IdCardEntity save(IdCardEntity entity) {
        return idCardDao.save(entity);
    }

    @Override
    public List<IdCardEntity> readAll() {
        return idCardDao.readAll();
    }

    @Override
    public IdCardEntity findOne(Integer id) {
        return idCardDao.findOne(id);
    }

    @Override
    public IdCardEntity update(IdCardEntity entity) {
        return idCardDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        idCardDao.delete(id);
    }
}
