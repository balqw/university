package ua.com.foxminded.domain.entity.mapperEntity;

import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.domain.entity.StudentEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<StudentEntity> {
    @Override
    public StudentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentEntity studentEntity = new StudentEntity();
                studentEntity.setStudentId(rs.getInt("studentId"));
                studentEntity.setFirstName(rs.getString("firstName"));
                studentEntity.setLastName(rs.getString("lastName"));
                studentEntity.setCourse(rs.getInt("course"));
        return studentEntity;
    }
}
