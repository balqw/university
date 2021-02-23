package ua.com.foxminded.domain.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.IdCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
public class IdCardDao implements CrudOperation<IdCardEntity, Integer>{

    private final EntityManagerFactory managerFactory;
    private final Logger logger = LoggerFactory.getLogger(IdCardDao.class);
    @Autowired
    public IdCardDao(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }


    @Override
    public IdCardEntity save(IdCardEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
        logger.debug("save id card {}", entity);
        return entity;
    }

    @Override
    public List<IdCardEntity> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        List<IdCardEntity>cards =  em.createQuery("select idCard from IdCardEntity idCard").getResultList();
        logger.debug("read all cards");
        return cards;
    }

    @Override
    public IdCardEntity findOne(Integer id) {
        IdCardEntity card =  managerFactory.createEntityManager().find(IdCardEntity.class, id);
        logger.debug("find card with id = {}", id);
        return card;
    }

    @Override
    public IdCardEntity update(IdCardEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(entity);
        et.commit();
        em.close();
        logger.debug("update card {}", entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(em.find(IdCardEntity.class,id));
        et.commit();
        em.close();
        logger.debug("delete card with id = {}", id);
    }

    @Override
    public boolean exist(IdCardEntity entity) {
       return false; //it's no matter
    }
}
