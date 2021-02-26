package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.domain.entity.IdCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupDao implements CrudOperation<Group, Integer> {

    private final EntityManagerFactory managerFactory;
    private final Logger logger = LoggerFactory.getLogger(Group.class);


    @Override
    public Group save(Group entity) {
        EntityManager em = managerFactory.createEntityManager();
        em.persist(entity);
        logger.debug("save group {}", entity);
        return entity;
    }

    @Override
    public List<Group> readAll() {
        EntityManager em = managerFactory.createEntityManager();
        logger.debug("read all group");
        return em.createQuery("select a from Group a").getResultList();
    }

    @Override
    public Group findOne(Integer id) {
        Group group = managerFactory.createEntityManager().find(Group.class, id);
        logger.debug("find group with id = {}", id);
        return group;
    }


    @Override
    public Group update(Group entity) {
        EntityManager em = managerFactory.createEntityManager();
        em.merge(entity);
        logger.debug("update group", entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = managerFactory.createEntityManager();
        em.remove(em.find(IdCardEntity.class, id));
        logger.debug("delete group with id = {}", id);
    }

    public Group findByAbbr(String name) {
        EntityManager em = managerFactory.createEntityManager();
        return em.find(Group.class, name);
    }

    public Group findGroupInLesson(Integer id) {
        EntityManager em = managerFactory.createEntityManager();
        throw new NotImplementedException();//ToDo
    }

    @Override
    public boolean exist(Group entity) {
        EntityManager em = managerFactory.createEntityManager();
        Query q = em.createQuery("select count (a) from Group a where a.abbreviate=:abbr")
                .setParameter("abbr",entity.getAbbreviate());
        Long count = (Long) q.getSingleResult();
        return count>0;
    }
}
