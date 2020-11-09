package ua.com.foxminded.domain.entity.mapperEntity;

import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.domain.entity.IdCardEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdCardMapper implements RowMapper<IdCardEntity> {
    @Override
    public IdCardEntity mapRow(ResultSet rs, int rowNum) throws SQLException {


        IdCardEntity idCardEntity = new IdCardEntity();
        idCardEntity.setId(rs.getInt("id"));
        idCardEntity.setDataExpire(rs.getTimestamp("dateExpire").toLocalDateTime());
        return idCardEntity;
    }
}
