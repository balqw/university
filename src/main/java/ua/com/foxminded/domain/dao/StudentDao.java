package ua.com.foxminded.domain.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.mapperEntity.StudentMapper;

import java.util.List;


public class StudentDao implements CrudOperation <StudentEntity, Integer>{
    private final String INSERT = "insert into students (first_name,last_name,course) values(?,?,?)";
    private final String FIND_BY_ID = "select * from students where id = ?";
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

    public void insert (StudentEntity studentEntity){
        jdbcTemplate.update(INSERT,studentEntity.getFirstName(),
                studentEntity.getLastName(),studentEntity.getCourse());
    }


    public StudentEntity read(Integer id){
        return jdbcTemplate.queryForObject(FIND_BY_ID,new Object[]{id}, new StudentMapper());
    }
}
