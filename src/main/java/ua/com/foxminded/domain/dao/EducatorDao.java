package ua.com.foxminded.domain.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class EducatorDao implements CrudOperation<EducatorEntity,Integer>{
    private final EntityManagerFactory managerFactory;
    private final Logger logger = LoggerFactory.getLogger(EducatorDao.class);

    @Autowired
    public EducatorDao(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }


    @Override
    public EducatorEntity save(EducatorEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
        logger.debug("save educator {}", entity);
        return entity;
    }

    @Override
    public List<EducatorEntity> readAll() {
        List<EducatorEntity>educators =  managerFactory.createEntityManager().createQuery("select educator from EducatorEntity educator").getResultList();
        logger.debug("read all educators");
        return educators;
    }

    @Override
    public EducatorEntity findOne(Integer id) {
       EducatorEntity educator = managerFactory.createEntityManager().find(EducatorEntity.class, id);
       logger.debug("find educator with id = {}", id);
       return educator;
    }

    @Override
    public EducatorEntity update(EducatorEntity entity) {
      EntityManager em = managerFactory.createEntityManager();
      EntityTransaction et = em.getTransaction();
      et.begin();
      em.merge(entity);
      et.commit();
      em.close();
      logger.debug("update educator {}", entity);
      return entity;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(em.find(EducatorEntity.class,id));
        et.commit();
        em.close();
        logger.debug("delete educator with id = {}", id);
    }

    @Override
    public boolean exist(EducatorEntity entity) {
        return false;
    }
}
