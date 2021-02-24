package ua.com.foxminded.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public EducatorEntity save(EducatorEntity educator) {
        if(educatorDao.exist(educator))
            throw new IllegalArgumentException("educator already exist");
        
        return educatorDao.save(educator);
    }

    @Transactional
    public void idCardExistsAndValid(IdCardEntity idCard) {
        if (idCard != null && idCard.getCardId() != null) {
            if (!idCardDao.exist(idCard))
                throw new NotFoundException("Such idCard not found");
        }
    }

    @Transactional
    public List<EducatorEntity> readAll() {
        return educatorDao.readAll();
    }

    @Transactional
    public EducatorEntity findOne(Integer id) {
        return educatorDao.findOne(id);
    }

    @Transactional
    public EducatorEntity update(EducatorEntity educator) {
            return educatorDao.update(educator);
    }

    @Transactional
    public void delete(Integer id) {
        educatorDao.delete(id);
    }

}
