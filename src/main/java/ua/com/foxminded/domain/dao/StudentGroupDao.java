package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.StudentGroupEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentGroupDao implements CrudOperation<StudentGroupEntity, Integer> {

    private final EntityManagerFactory managerFactory;

    @Override
    public StudentGroupEntity save(StudentGroupEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
        return entity;
    }

    @Override
    public List<StudentGroupEntity> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        return em.createQuery("select a from StudentGroupEntity a").getResultList();
    }

    @Override
    public StudentGroupEntity findOne(Integer id) {
        return managerFactory.createEntityManager().find(StudentGroupEntity.class, id);
    }

    @Override
    public StudentGroupEntity update(StudentGroupEntity entity) {
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
    public boolean exist(StudentGroupEntity entity) {
        return false;
    }
}
