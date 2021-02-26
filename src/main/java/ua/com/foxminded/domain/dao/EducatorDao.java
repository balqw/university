package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EducatorDao implements CrudOperation<EducatorEntity,Integer>{
    private final EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(EducatorDao.class);


    @Override
    public EducatorEntity save(EducatorEntity entity) {
        em.persist(entity);
        logger.debug("save educator {}", entity);
        return entity;
    }

    @Override
    public List<EducatorEntity> readAll() {
        List<EducatorEntity>educators =  em.createQuery("select educator from EducatorEntity educator").getResultList();
        logger.debug("read all educators");
        return educators;
    }

    @Override
    public EducatorEntity findOne(Integer id) {
       EducatorEntity educator = em.find(EducatorEntity.class, id);
       logger.debug("find educator with id = {}", id);
       return educator;
    }

    @Override
    public EducatorEntity update(EducatorEntity entity) {
      em.merge(entity);
      logger.debug("update educator {}", entity);
      return entity;
    }

    @Override
    public void delete(Integer id) {
        em.remove(em.find(EducatorEntity.class,id));
        logger.debug("delete educator with id = {}", id);
    }

    @Override
    public boolean exist(EducatorEntity entity) {
        Query q = em.createQuery("select count(a) from EducatorEntity a where a.firstName=:name and a.lastName = :surname")
                .setParameter("name",entity.getFirstName())
                .setParameter("surname",entity.getLastName());
        Long count = (Long) q.getSingleResult();
        return count>0;

    }
}
