package ua.com.foxminded.domain.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.EducatorEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class ClassRoomDao implements CrudOperation<ClassRoomEntity, Integer>{
    private final EntityManagerFactory managerFactory;
    private final Logger logger = LoggerFactory.getLogger(ClassRoomDao.class);

    @Autowired
    public ClassRoomDao(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    @Override
    public ClassRoomEntity save(ClassRoomEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
        logger.debug("save class room {}",entity);
        return entity;
    }

    @Override
    public List<ClassRoomEntity> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        List<ClassRoomEntity>rooms = em.createQuery("select room from ClassRoomEntity room").getResultList();
        logger.debug("read all rooms");
        return rooms;
    }

    @Override
    public ClassRoomEntity findOne(Integer id) {
        ClassRoomEntity room =  managerFactory.createEntityManager().find(ClassRoomEntity.class, id);
        logger.debug("find room by id = {}",id);
        return room;
    }

    @Override
    public ClassRoomEntity update(ClassRoomEntity entity) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(entity);
        et.commit();
        em.close();
        logger.debug("update room {}",entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(em.find(ClassRoomEntity.class,id));
        et.commit();
        em.close();
        logger.debug("delete room with id = {}", id);
    }

    @Override
    public boolean exist(ClassRoomEntity entity) {
        return false;
    }

    public ClassRoomEntity findByNumber(Integer number) throws RuntimeException{
        return (ClassRoomEntity) managerFactory.createEntityManager().createQuery("select a from ClassRoomEntity a where a.number = ?1")
                .setParameter(1,number).getSingleResult();
    }
}
