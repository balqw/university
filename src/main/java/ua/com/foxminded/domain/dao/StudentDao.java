package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.domain.entity.mapperEntity.StudentMapper;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import static java.lang.String.format;

@Repository
@RequiredArgsConstructor
public class StudentDao implements CrudOperation <StudentEntity, Integer>{
    private final String INSERT = "insert into student (firstName,lastName,course) values (?,?,?)";
    private final String FIND_BY_ID = "select * from student where studentId = ?";
    private final String FIND_ALL = "select * from student";
    private final String UPDATE = "update student set firstName=?,lastName=?,course=? where studentId=? ";
    private final String DELETE = "delete from student where studentId = ?";
    private final String COUNT = "select count(studentId) from student where studentId=?";
    private final String UNIQ = "select count (*) from student where firstName=? and lastName = ? ";
    private final JdbcTemplate jdbcTemplate;
    private final static Logger logger = LoggerFactory.getLogger(StudentDao.class);


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
        entity.setStudentId((Integer) keyH.getKeys().get("studentId"));
        logger.debug("save student: {}",entity);
        return entity;
    }

    @Override
    public List<StudentEntity> readAll() {
        logger.debug("read all students");
        return jdbcTemplate.query(FIND_ALL,new StudentMapper());
    }

    @Override
    public StudentEntity findOne(Integer id) {
        logger.debug("find by id");
        try{StudentEntity studentEntity;
            studentEntity = jdbcTemplate.queryForObject(FIND_BY_ID,new Object[]{id},
                new StudentMapper());
        return studentEntity;
        }catch (RuntimeException e){
            logger.error("student with id {} failed",id,e);
            String msg = format("student with id = '%s' not exist",id);
            throw new NotFoundException(msg);
        }
    }

    @Override
    public StudentEntity update(StudentEntity entity) {

        jdbcTemplate.update(UPDATE,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getCourse(),
                entity.getStudentId());
        logger.debug("update student: {}",entity);
         return entity;
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete student with id {}",id);
        jdbcTemplate.update(DELETE,id);
    }


    public boolean isExist(Integer id) {
      return jdbcTemplate.queryForObject(COUNT,new Object[]{id},Integer.class)>0;
    }

    public boolean isExist(String fName, String lName ){
        return jdbcTemplate.queryForObject(UNIQ,new Object[]{lName,fName},Integer.class)>0;
    }

}
