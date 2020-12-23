package ua.com.foxminded.domain.dao;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.StudentEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class StudentDaoHibernate implements CrudOperation<StudentEntity, Integer>{

    private EntityManager entityManager;

    
    @Autowired
    public StudentDaoHibernate(@Qualifier("getEntityManager") EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public StudentEntity save(StudentEntity entity) {
         EntityTransaction et = entityManager.getTransaction();
         et.begin();
         entityManager.persist(entity);
         et.commit();
         return entity;

    }

    @Override
    public List<StudentEntity> readAll() {
        //entityManager.getTransaction().begin();
        return entityManager.createQuery("select a from StudentEntity a",StudentEntity.class).getResultList();

    }

    @Override
    public StudentEntity findOne(Integer id) {
      return null;
    }

    @Override
    public StudentEntity update(StudentEntity entity) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public boolean exist(StudentEntity entity) {
        return false;
    }
}
