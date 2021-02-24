package ua.com.foxminded.domain.dao;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class StudentDaoImpl implements StudentDao{
    private final EntityManagerFactory managerFactory;
    private final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);


    @Override
    public StudentEntity save(StudentEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
        logger.debug("save student {}", entity);
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<StudentEntity> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        List<StudentEntity>students =  em.createQuery("SELECT student from StudentEntity student").getResultList();
        logger.debug("read all students");
        return students;
    }

    @Override
    public StudentEntity findOne(Integer id) {
        EntityManager em = managerFactory.createEntityManager();
        StudentEntity student =  em.find(StudentEntity.class, id);
        logger.debug("find student with id = {}", id);
        return student;
    }

    @Override
    public StudentEntity update(StudentEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(entity);
        et.commit();
        em.close();
        logger.debug("update student {}", entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        StudentEntity student = em.find(StudentEntity.class, id);
        em.remove(student);
        et.commit();
        em.close();
        logger.debug("delete student with id = {}", id);
    }

    @Override
    public boolean exist(StudentEntity entity){
        EntityManager em = managerFactory.createEntityManager();
        Query q = em.createQuery("select count(student) from StudentEntity student where student.firstName = :name " +
                "and student.lastName = :surname")
                .setParameter("name",entity.getFirstName())
                .setParameter("surname",entity.getLastName());
        Long count = (Long) q.getSingleResult();
        return count>0;
    }

    @Override
    public List<StudentEntity> findByGroup(Integer groupId) {
        EntityManager em = managerFactory.createEntityManager();
        Query query = em.createNativeQuery("Select * from student where studentgroup =:id");
        query.setParameter("id",groupId);
        return query.getResultList();
    }
}
