package ua.com.foxminded.domain.entity.mapperEntity;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.config.JdbcTempleFactory;
import ua.com.foxminded.domain.dao.ClassRoomDao;
import ua.com.foxminded.domain.entity.LessonEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonMapper implements RowMapper<LessonEntity> {
    JdbcTemplate jdbcTemplate = new JdbcTempleFactory("POSTGRES").getJdbcTemplate();
    ClassRoomDao classRoomDao = new ClassRoomDao(jdbcTemplate);


    @Override
    public LessonEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setTitle(rs.getString("title"));
        lessonEntity.setStartLesson(rs.getTimestamp("startLesson").toLocalDateTime());
        lessonEntity.setEndLesson(rs.getTimestamp("endLesson").toLocalDateTime());
        lessonEntity.setClassRoom(classRoomDao.findOne(rs.getInt("classRoom")));
        return lessonEntity;
    }
}
