package ua.com.foxminded.domain.entity.mapperEntity;
import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import ua.com.foxminded.domain.entity.LessonEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonMapper implements RowMapper<LessonEntity> {

    @Override
    public LessonEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClassRoomEntity classRoomEntity = new ClassRoomEntity();
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setTitle(rs.getString("title"));
        lessonEntity.setStartLesson(rs.getTimestamp("startLesson").toLocalDateTime());
        lessonEntity.setEndLesson(rs.getTimestamp("endLesson").toLocalDateTime());
        classRoomEntity.setNumber(rs.getInt("number"));
        classRoomEntity.setCapacity(rs.getInt("capacity"));
        lessonEntity.setClassRoom(classRoomEntity);
        return lessonEntity;
    }
}
