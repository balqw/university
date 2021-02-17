package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.entity.IdCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentGroupDao implements CrudOperation<Group, Integer> {

    private final EntityManagerFactory managerFactory;

    @Override
    public Group save(Group entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
        return entity;
    }

    @Override
    public List<Group> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        return em.createQuery("select a from Group a").getResultList();
    }

    @Override
    public Group findOne(Integer id) {
        return managerFactory.createEntityManager().find(Group.class, id);
    }



    @Override
    public Group update(Group entity) {
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

    public Group findByAbbr(String name){
        EntityManager em = managerFactory.createEntityManager();
        return em.find(Group.class,name);
    }

    public Group findGroupInLesson(Integer id){
        EntityManager em = managerFactory.createEntityManager();


    }

    @Override
    public boolean exist(Group entity) {
        return false;
    }
}
