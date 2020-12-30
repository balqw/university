package ua.com.foxminded.domain.entity.mapperEntity;

import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EducatorMapper implements RowMapper<EducatorEntity> {


    @Override
    public EducatorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        IdCardEntity idCardEntity = new IdCardEntity();
        EducatorEntity educatorEntity = new EducatorEntity();
        educatorEntity.setEducatorId(rs.getInt("educatorId"));
        educatorEntity.setFirstName(rs.getString("firstName"));
        educatorEntity.setLastName(rs.getString("lastName"));
        idCardEntity.setCardId(rs.getInt("cardId"));
        if(rs.getTimestamp("dateExpire")!=null)
        idCardEntity.setDateExpire(rs.getTimestamp("dateExpire").toLocalDateTime().toLocalDate());
        educatorEntity.setIdCard(idCardEntity);
        return educatorEntity;
    }
}
