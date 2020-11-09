package ua.com.foxminded.domain.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.com.foxminded.config.JdbcTempleFactory;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.entity.mapperEntity.LessonMapper;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class LessonDao implements CrudOperation<LessonEntity, Integer>{

    private final String INSERT = "insert into lesson (title,startLesson,endLesson,classRoom) values(?,?,?,?)";
    private final String FIND_BY_ID = "select * from lesson where id = ?";
    private final String FIND_ALL = "select * from lesson";
    private final String UPDATE = "update lesson set title=?,startLesson=?,endLesson=?,classRoom=? where id=? ";
    private final String DELETE = "delete from lesson where id = ?";
    private final JdbcTemplate jdbcTemplate;


    public LessonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LessonEntity save(LessonEntity entity) {
        KeyHolder keyH = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,entity.getTitle());
            ps.setTimestamp(2, Timestamp.valueOf(entity.getStartLesson()));
            ps.setTimestamp(3,Timestamp.valueOf(entity.getEndLesson()));
            ps.setInt(4,Optional.ofNullable(entity.getClassRoom())
                    .map(ClassRoomEntity::getNumber).orElse(0));
            ps.getGeneratedKeys();
            return ps;
        },keyH);
        entity.setId((int)keyH.getKeys().get("id"));
        return entity;
    }

    @Override
    public List<LessonEntity> readAll() {
         return jdbcTemplate.query(FIND_ALL,new LessonMapper());
    }

    @Override
    public LessonEntity findOne(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,new Object[]{id},new LessonMapper());
    }

    @Override
    public LessonEntity update(LessonEntity entity) {
        jdbcTemplate.update(UPDATE,entity.getTitle(),
                Timestamp.valueOf(entity.getStartLesson()),
                Timestamp.valueOf(entity.getEndLesson()),
                entity.getClassRoom().getNumber());
        return entity;
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE,id);
    }
}
