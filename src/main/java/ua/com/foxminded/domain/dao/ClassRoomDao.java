package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.mapperEntity.ClassRoomMapper;
import ua.com.foxminded.domain.exceptions.NotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static java.lang.String.format;

@Repository
@RequiredArgsConstructor
public class ClassRoomDao implements CrudOperation<ClassRoomEntity, Integer> {
    private final String INSERT = "insert into classroom (number,capacity) values(?,?)";
    private final String FIND_BY_ID = "select * from classroom where number = ?";
    private final String FIND_ALL = "select * from classroom";
    private final String UPDATE = "update classroom set number=?,capacity=? where classId=? ";
    private final String DELETE = "delete from classroom where classId = ?";
    private final String COUNT = "select count(classId) from classroom where classId=?";
    private final JdbcTemplate jdbcTemplate;
    private final static Logger logger = LoggerFactory.getLogger(ClassRoomDao.class);


    @Override
    public ClassRoomEntity save(ClassRoomEntity entity) {
        KeyHolder keyH = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getNumber());
            ps.setInt(2, entity.getCapacity());
            return ps;
        }, keyH);
        entity.setClassId((int) keyH.getKeys().get("classId"));
        logger.debug("save classRoom {}", entity);
        return entity;
    }

    @Override
    public List<ClassRoomEntity> readAll() {
        logger.debug("read all classRooms");
        return jdbcTemplate.query(FIND_ALL, new ClassRoomMapper());
    }

    @Override
    public ClassRoomEntity findOne(Integer id) {
        logger.debug("find classRoom with id {}", id);
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new ClassRoomMapper());
        } catch (RuntimeException e) {
            logger.error("classRoom with id {} failed", id, e);
            String msg = format("classRoom with id = '%s' not exist", id);
            throw new NotFoundException(msg);
        }
    }

    @Override
    public ClassRoomEntity update(ClassRoomEntity entity) {
        logger.debug("update classRoom {}", entity);
        jdbcTemplate.update(UPDATE,
                entity.getNumber(),
                entity.getCapacity());
        return entity;
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete classRoom with id {}", id);
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public boolean exist(ClassRoomEntity entity) {
        return jdbcTemplate.queryForObject(COUNT, new Object[]{entity.getClassId()}, Integer.class) > 0;
    }

    public boolean isExist(Integer id) {
        return jdbcTemplate.queryForObject(COUNT, new Object[]{id}, Integer.class) > 0;
    }


}
