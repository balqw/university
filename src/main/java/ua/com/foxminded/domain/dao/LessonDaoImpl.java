package ua.com.foxminded.domain.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.mappers.LessonInfoMapperImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LessonDaoImpl implements LessonDao {

    private final EntityManagerFactory managerFactory;
    private final Logger logger = LoggerFactory.getLogger(LessonInfoMapperImpl.class);

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
        logger.debug("save lesson {}", entity);
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LessonEntity> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        List<LessonEntity>lessons = em.createQuery("SELECT a from LessonEntity a").getResultList();
        logger.debug("read all lessons");
        return lessons;
    }

    @Override
    public LessonEntity findOne(Integer id) {
       LessonEntity lesson = managerFactory.createEntityManager().find(LessonEntity.class, id);
       logger.debug("find lesson with id = {}", id);
       return lesson;
    }

    @Override
    public LessonEntity update(LessonEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(entity);
        et.commit();
        em.close();
        logger.debug("update lesson {}", entity);
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
        logger.debug("delete lesson with id = {}", id);
    }

    @Override
    public boolean exist(LessonEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        Query q = em.createQuery("select count(lesson) from LessonEntity lesson " +
                "where lesson.startLesson = :start " +
                "and lesson.title = :title")
                .setParameter("start",entity.getStartLesson())
                .setParameter("title",entity.getTitle());
        Long count = (Long) q.getSingleResult();
        return count>0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LessonEntity> findByGroup(Integer groupId) {
        EntityManager em = managerFactory.createEntityManager();
        Query query = em.createNativeQuery("SELECT * from lesson_group  where group_id = :id");//ToDo -> create on DB side
        query.setParameter("id", groupId);
        return query.getResultList();
    }

    public void setGroup(Integer lessId, Integer groupId) {
        EntityManager em = managerFactory.createEntityManager();
        Query query = em.createNativeQuery("INSERT into lesson_group (lessonid,groupid) values (:idLesson,:idGroup)");
        query.setParameter("idLesson", lessId).setParameter("idGroup", groupId);

    }



}
