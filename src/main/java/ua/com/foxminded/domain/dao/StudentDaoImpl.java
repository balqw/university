package ua.com.foxminded.domain.dao;

import lombok.AllArgsConstructor;
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


    @Override
    public StudentEntity save(StudentEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<StudentEntity> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        return em.createQuery("SELECT student from StudentEntity student").getResultList();
    }

    @Override
    public StudentEntity findOne(Integer id) {
        EntityManager em = managerFactory.createEntityManager();
        return em.find(StudentEntity.class, id);
    }

    @Override
    public StudentEntity update(StudentEntity entity) {
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
        StudentEntity student = em.find(StudentEntity.class, id);
        em.remove(student);
        et.commit();
        em.close();
    }

    @Override
    public boolean exist(StudentEntity entity) {
        return false;//FixMe
    }

    @Override
    public List<StudentEntity> findByGroup(Integer groupId) {
        EntityManager em = managerFactory.createEntityManager();
        Query query = em.createNativeQuery("Select * from student where studentgroup =:id");
        query.setParameter("id",groupId);
        return query.getResultList();
    }
}
