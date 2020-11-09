package ua.com.foxminded.domain.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.mapperEntity.ClassRoomMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class ClassRoomDao implements CrudOperation<ClassRoomEntity,Integer>{
    private final String INSERT = "insert into classroom (number,capacity) values(?,?)";
    private final String FIND_BY_ID = "select * from classroom where number = ?";
    private final String FIND_ALL = "select * from classroom";
    private final String UPDATE = "update classroom set number=?,capacity=? where id=? ";
    private final String DELETE = "delete from classroom where id = ?";
    private final JdbcTemplate jdbcTemplate;

    public ClassRoomDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ClassRoomEntity save(ClassRoomEntity entity){
        jdbcTemplate.update(INSERT,entity.getNumber(),
                entity.getCapacity());
        return entity;
    }

    @Override
    public List<ClassRoomEntity> readAll() {
        return jdbcTemplate.query(FIND_ALL,new ClassRoomMapper());
    }

    @Override
    public ClassRoomEntity findOne(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,new Object[]{id},new ClassRoomMapper());
    }

    @Override
    public ClassRoomEntity update(ClassRoomEntity entity) {
        jdbcTemplate.update(UPDATE,
                entity.getNumber(),
                entity.getCapacity());
        return entity;
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE,id);
    }
}
