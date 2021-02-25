package ua.com.foxminded.reposytory;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.foxminded.domain.entity.StudentEntity;


public interface StudentRepository extends JpaRepository<StudentEntity,Integer>{

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);



}
