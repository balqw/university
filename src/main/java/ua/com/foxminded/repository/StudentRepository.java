package ua.com.foxminded.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.domain.entity.StudentEntity;


public interface StudentRepository extends JpaRepository<StudentEntity,Integer>{

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);



}
