package ua.com.foxminded.domain.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.LessonEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LessonDaoImpl implements LessonDao {

    private final EntityManagerFactory managerFactory;

    @Autowired
    public LessonDaoImpl(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    @Override
    public LessonEntity save(LessonEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
        return entity;
    }

    @Override
    public List<LessonEntity> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        return em.createQuery("SELECT a from LessonEntity a").getResultList();
    }

    @Override
    public LessonEntity findOne(Integer id) {
        EntityManager em = managerFactory.createEntityManager();
        return em.find(LessonEntity.class, id);
    }

    @Override
    public LessonEntity update(LessonEntity entity) {
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
        LessonEntity lessonEntity = em.find(LessonEntity.class, id);
        em.remove(lessonEntity);
        et.commit();
        em.close();
    }

    @Override
    public boolean exist(LessonEntity entity) {
        return false;
    }

    @Override
    public List<LessonEntity> findByGroup(Integer groupId) {
        EntityManager em = managerFactory.createEntityManager();
        Query query = em.createNativeQuery("SELECT * from lesson_group  where group_id = :id");//ToDo -> create on DB side
        query.setParameter("id", groupId);
        return query.getResultList();
    }

    public void setGroup(Integer lessId, Integer groupId){
        EntityManager em = managerFactory.createEntityManager();
        Query query = em.createNativeQuery("INSERT into lesson_group (lessonid,groupid) values (:idLesson,:idGroup)");
        query.setParameter("idLesson",lessId).setParameter("idGroup",groupId);

    }




}
