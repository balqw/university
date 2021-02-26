package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.entity.IdCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IdCardDao implements CrudOperation<IdCardEntity, Integer>{
    private final Logger logger = LoggerFactory.getLogger(IdCardDao.class);
    private final EntityManager em;



    @Override
    @Transactional
    public IdCardEntity save(IdCardEntity entity) {
        em.persist(entity);
        logger.debug("save id card {}", entity);
        return entity;
    }

    @Override
    public List<IdCardEntity> readAll() {
        List<IdCardEntity>cards =  em.createQuery("select idCard from IdCardEntity idCard").getResultList();
        logger.debug("read all cards");
        return cards;
    }

    @Override
    public IdCardEntity findOne(Integer id) {
        IdCardEntity card =  em.find(IdCardEntity.class, id);
        logger.debug("find card with id = {}", id);
        return card;
    }

    @Override
    public IdCardEntity update(IdCardEntity entity) {
        em.merge(entity);
        logger.debug("update card {}", entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        em.remove(em.find(IdCardEntity.class,id));
        logger.debug("delete card with id = {}", id);
    }

    @Override
    public boolean exist(IdCardEntity entity) {
       return false; //it's no matter
    }
}
