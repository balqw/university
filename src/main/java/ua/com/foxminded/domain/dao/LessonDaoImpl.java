package ua.com.foxminded.domain.dao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.mappers.LessonInfoMapperImpl;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LessonDaoImpl implements LessonDao {
    private final EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(LessonInfoMapperImpl.class);

    @Override
    public LessonEntity save(LessonEntity entity) {
        em.persist(entity);
        logger.debug("save lesson {}", entity);
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LessonEntity> readAll() {
        List<LessonEntity>lessons = em.createQuery("SELECT a from LessonEntity a").getResultList();
        logger.debug("read all lessons");
        return lessons;
    }

    @Override
    public LessonEntity findOne(Integer id) {
       LessonEntity lesson = em.find(LessonEntity.class, id);
       logger.debug("find lesson with id = {}", id);
       return lesson;
    }

    @Override
    public LessonEntity update(LessonEntity entity) {
        EntityTransaction et = em.getTransaction();
        em.merge(entity);
        logger.debug("update lesson {}", entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        LessonEntity lessonEntity = em.find(LessonEntity.class, id);
        em.remove(lessonEntity);
        logger.debug("delete lesson with id = {}", id);
    }

    @Override
    public boolean exist(LessonEntity entity) {
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
        Query query = em.createNativeQuery("SELECT * from lesson_group  where group_id = :id");//ToDo -> create on DB side
        query.setParameter("id", groupId);
        return query.getResultList();
    }

    public void setGroup(Integer lessId, Integer groupId) {
        Query query = em.createNativeQuery("INSERT into lesson_group (lessonid,groupid) values (:idLesson,:idGroup)");
        query.setParameter("idLesson", lessId).setParameter("idGroup", groupId);

    }



}
