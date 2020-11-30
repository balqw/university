package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.EducatorDao;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import java.util.List;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EducatorService{
    private final EducatorDao educatorDao;


    public EducatorEntity save(EducatorEntity entity) {
        if(educatorDao.isExist(entity.getFirstName(),entity.getLastName()))
            throw new IllegalArgumentException("educator already exist");

        return educatorDao.save(entity);
    }

    public List<EducatorEntity> readAll() {
        return educatorDao.readAll();
    }

    public EducatorEntity findOne(Integer id) {
        return educatorDao.findOne(id);
    }

    public EducatorEntity update(EducatorEntity entity) {
        if(educatorDao.isExist(entity.getEducatorId()))
            return educatorDao.update(entity);

        throw new NotFoundException(format("educator with id = '%s' not exist",entity.getIdCard()));
    }

    public void delete(Integer id) {
        educatorDao.delete(id);
    }
}
