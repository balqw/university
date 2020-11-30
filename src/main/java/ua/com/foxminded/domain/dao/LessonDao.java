package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.LessonEntity;
import ua.com.foxminded.domain.entity.mapperEntity.LessonMapper;
import ua.com.foxminded.domain.exceptions.NotFoundException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import static java.lang.String.format;


@Repository
@RequiredArgsConstructor
public class LessonDao implements CrudOperation<LessonEntity, Integer>{

    private final String INSERT = "insert into lesson (title,startLesson,endLesson) values(?,?,?)";
    private final String FIND_ALL = "select * from lesson join classRoom on classroom.number = lesson.classroom";
    private final String UPDATE = "update lesson set title=?,startLesson=?,endLesson=?,classRoom=? where lessonId = ?";
    private final String DELETE = "delete from lesson where id = ?";
    private final String FIND_BY_ID = "select * from lesson join classRoom on classroom.number = lesson.classroom where lessonId = ?";
    private final String COUNT = "select count(lessonId) from lesson where lessonId=?";
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LessonDao.class);



    @Override
    public LessonEntity save(LessonEntity entity) {
        KeyHolder keyH = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,entity.getTitle());
            ps.setTimestamp(2, Timestamp.valueOf(entity.getStartLesson()));
            ps.setTimestamp(3,Timestamp.valueOf(entity.getEndLesson()));
            return ps;
        },keyH);
        entity.setLessonId((int) (keyH.getKeys()).get("lessonId"));
        logger.debug("save lesson {} ",entity);
        return entity;
    }

    @Override
    public List<LessonEntity> readAll() {
        logger.debug("read all lesson");
         return jdbcTemplate.query(FIND_ALL,new LessonMapper());
    }

    @Override
    public LessonEntity findOne(Integer id) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,new Object[]{id},new LessonMapper());
        }catch (RuntimeException e){
            logger.error("find by id {} failed",id,e);
            String msg = format("lesson with id = '%s' not exist",id);
            throw new NotFoundException(msg);
        }
    }

    @Override
    public LessonEntity update(LessonEntity entity) {
        jdbcTemplate.update(UPDATE,entity.getTitle(),
                Timestamp.valueOf(entity.getStartLesson()),
                Timestamp.valueOf(entity.getEndLesson()),
                entity.getClassRoom().getNumber(),
                entity.getLessonId());
        logger.debug("lesson update {}:",entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE,id);
        logger.debug("delete lesson with id {}",id);
    }


    public boolean isExist(Integer id) {
        return jdbcTemplate.queryForObject(COUNT,new Object[]{id},Integer.class)>0;
    }

}