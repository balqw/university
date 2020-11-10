package ua.com.foxminded.domain.entity.mapperEntity;
import org.springframework.jdbc.core.RowMapper;;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.IdCardEntity;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EducatorMapper implements RowMapper<EducatorEntity> {
    private  IdCardEntity idCardEntity = new IdCardEntity();

    @Override
    public EducatorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        EducatorEntity educatorEntity = new EducatorEntity();
        educatorEntity.setEducatorId(rs.getInt("educatorId"));
        educatorEntity.setFirstName(rs.getString("firstName"));
        educatorEntity.setLastName(rs.getString("lastName"));
        idCardEntity.setCardId(rs.getInt("cardId"));
        idCardEntity.setDataExpire(rs.getTimestamp("dateExpire").toLocalDateTime());
        educatorEntity.setIdCard(idCardEntity);
        return educatorEntity;
    }
}
