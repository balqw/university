package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.foxminded.domain.dao.EducatorDao;
import ua.com.foxminded.domain.dao.IdCardDao;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.domain.exceptions.NotFoundException;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EducatorService {
    private final EducatorDao educatorDao;
    private final IdCardDao idCardDao;

    public EducatorEntity save(EducatorEntity educator) {
        if (educatorDao.exist(educator)) throw new IllegalArgumentException("educator already exist");
        idCardExistsAndValid(educator.getIdCard());
        return educatorDao.save(educator);
    }

    private void idCardExistsAndValid(IdCardEntity idCard) {
        if (idCard != null && idCard.getCardId() != null) {
            if (!idCardDao.exist(idCard))
                throw new NotFoundException("Such idCard not found");
        }
    }

    public List<EducatorEntity> readAll() {
        return educatorDao.readAll();
    }

    public EducatorEntity findOne(Integer id) {
        return educatorDao.findOne(id);
    }

    public EducatorEntity update(EducatorEntity educator) {
        if (educatorDao.exist(educator))
            return educatorDao.update(educator);
        throw new NotFoundException(format("educator with id = '%s' not exist", educator.getIdCard()));
    }

    public void delete(Integer id) {
        educatorDao.delete(id);
    }
}
