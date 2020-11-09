package ua.com.foxminded.domain.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.mapperEntity.StudentMapper;

import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.RowMapper;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


public class StudentDao implements CrudOperation <StudentEntity, Integer>{
    private final String INSERT = "insert into student (firstName,lastName,course) values(?,?,?)";
    private final String FIND_BY_ID = "select * from student where id = ?";
    private final String FIND_ALL = "select * from student";
    private final String UPDATE = "update student set first_name=?,last_name=?,course=? where id=? ";
    private final String DELETE = "delete from student where id = ?";
    private final JdbcTemplate jdbcTemplate;

    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public StudentEntity save(StudentEntity entity) {
        KeyHolder keyH = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,entity.getFirstName());
            ps.setString(2,entity.getLastName());
            ps.setInt(3,entity.getCourse());
            return ps;
        },keyH);
        entity.setId((Integer) keyH.getKeys().get("id"));
        return entity;
    }

    @Override
    public List<StudentEntity> readAll() {
        return jdbcTemplate.query(FIND_ALL,new StudentMapper());
    }

    @Override
    public StudentEntity findOne(Integer id) {
        StudentEntity studentEntity = (StudentEntity) jdbcTemplate.queryForObject(FIND_BY_ID,new Object[]{id},
                new BeanPropertyRowMapper(StudentEntity.class));
        return studentEntity;
    }

    @Override
    public StudentEntity update(StudentEntity entity) {
        jdbcTemplate.update(UPDATE,entity.getFirstName(),
                entity.getLastName(),
                entity.getCourse(),
                entity.getId());
         return entity;
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE,id);
    }

}
