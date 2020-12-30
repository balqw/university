package ua.com.foxminded.domain.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class EducatorDaoHibernate implements CrudOperation<EducatorEntity,Integer>{
    private final EntityManagerFactory managerFactory;

    @Autowired
    public EducatorDaoHibernate(EntityManagerFactory managerFactory) {
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
        return entity;
    }

    @Override
    public List<EducatorEntity> readAll() {
        return managerFactory.createEntityManager().createQuery("select educator from EducatorEntity educator").getResultList();
    }

    @Override
    public EducatorEntity findOne(Integer id) {
       return managerFactory.createEntityManager().find(EducatorEntity.class, id);
    }

    @Override
    public EducatorEntity update(EducatorEntity entity) {
      EntityManager em = managerFactory.createEntityManager();
      EntityTransaction et = em.getTransaction();
      et.begin();
      em.merge(entity);
      et.commit();
      em.close();
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
    }

    @Override
    public boolean exist(EducatorEntity entity) {
        return false;
    }
}
