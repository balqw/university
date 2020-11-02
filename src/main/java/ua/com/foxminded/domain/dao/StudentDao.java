package ua.com.foxminded.domain.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.util.List;


public class StudentDao implements CrudOperation <StudentEntity, Integer>{

    JdbcTemplate jdbcTemplate;

    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public StudentEntity create(StudentEntity entity) {
        return null;
    }

    @Override
    public List<StudentEntity> readAll() {
        return null;
    }

    @Override
    public StudentEntity update(StudentEntity entity) {
        return null;
    }

    @Override
    public StudentEntity delete(Integer entity) {
        return null;
    }


    public void insert(StudentEntity student){
        jdbcTemplate.update("insert into students (first_name,last_name,course) values(?,?,?)",
                student.getFirstName(),
                student.getLastName(),
                student.getCourse());
    }
}
