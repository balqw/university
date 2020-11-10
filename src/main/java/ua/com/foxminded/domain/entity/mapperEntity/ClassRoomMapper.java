package ua.com.foxminded.domain.entity.mapperEntity;
import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.domain.entity.ClassRoomEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassRoomMapper implements RowMapper<ClassRoomEntity> {
    @Override
    public ClassRoomEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
         ClassRoomEntity classRoomEntity = new ClassRoomEntity();
         classRoomEntity.setClassId(rs.getInt("classId"));
         classRoomEntity.setNumber(rs.getInt("number"));
         classRoomEntity.setCapacity(rs.getInt("capacity"));
         return classRoomEntity;
    }
}
