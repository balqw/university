package ua.com.foxminded.domain.entity.mapperEntity;

import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.domain.entity.IdCardEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdCardMapper implements RowMapper<IdCardEntity> {
    @Override
    public IdCardEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        IdCardEntity idCardEntity = new IdCardEntity();
        idCardEntity.setCardId(rs.getInt("cardId"));
        if(rs.getTimestamp("dateExpire")!=null)
        idCardEntity.setDateExpire(rs.getTimestamp("dateExpire").toLocalDateTime().toLocalDate());
        return idCardEntity;
    }
}
