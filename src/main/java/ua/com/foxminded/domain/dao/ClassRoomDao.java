package ua.com.foxminded.domain.dao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import javax.persistence.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassRoomDao implements CrudOperation<ClassRoomEntity, Integer>{
    private final Logger logger = LoggerFactory.getLogger(ClassRoomDao.class);
    private final EntityManager em;

    @Override
    public ClassRoomEntity save(ClassRoomEntity entity) {

        em.persist(entity);
        logger.debug("save class room {}",entity);
        return entity;
    }

    @Override
    public List<ClassRoomEntity> readAll() {
        List<ClassRoomEntity>rooms = em.createQuery("select room from ClassRoomEntity room").getResultList();
        logger.debug("read all rooms");
        return rooms;
    }

    @Override
    public ClassRoomEntity findOne(Integer id) {
        ClassRoomEntity room =  em.find(ClassRoomEntity.class, id);
        logger.debug("find room by id = {}",id);
        return room;
    }

    @Override
    public ClassRoomEntity update(ClassRoomEntity entity) {
        em.merge(entity);
        logger.debug("update room {}",entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        em.remove(em.find(ClassRoomEntity.class,id));
        logger.debug("delete room with id = {}", id);
    }

   @Override
   public boolean exist(ClassRoomEntity entity) {
       Query q =  em.createQuery("select count(a) from ClassRoomEntity a where a.number = :num")
               .setParameter("num", entity.getNumber());
       Long c = (Long) q.getSingleResult();
       return c>0;
   }

    public ClassRoomEntity findByNumber(Integer number) throws RuntimeException{
        return (ClassRoomEntity) em.createQuery("select a from ClassRoomEntity a where a.number = ?1")
                .setParameter(1,number).getSingleResult();
    }
}
