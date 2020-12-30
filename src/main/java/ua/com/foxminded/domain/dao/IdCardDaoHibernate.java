package ua.com.foxminded.domain.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.IdCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class IdCardDaoHibernate implements CrudOperation<IdCardEntity, Integer>{

    private final EntityManagerFactory managerFactory;

    @Autowired
    public IdCardDaoHibernate(EntityManagerFactory managerFactory) {
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
        return entity;
    }

    @Override
    public List<IdCardEntity> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        return em.createQuery("select idCard from IdCardEntity idCard").getResultList();
    }

    @Override
    public IdCardEntity findOne(Integer id) {
        return managerFactory.createEntityManager().find(IdCardEntity.class, id);
    }

    @Override
    public IdCardEntity update(IdCardEntity entity) {
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
        em.remove(em.find(IdCardEntity.class,id));
        et.commit();
        em.close();
    }

    @Override
    public boolean exist(IdCardEntity entity) {
        return false;
    }
}
