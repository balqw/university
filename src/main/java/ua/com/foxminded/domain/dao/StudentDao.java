package ua.com.foxminded.domain.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;


public interface StudentDao extends CrudOperation<StudentEntity, Integer> {

    public List<StudentEntity> findByGroup(Integer groupId);

}
