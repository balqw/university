package ua.com.foxminded.domain.entity.mapperEntity;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.Constants;
import ua.com.foxminded.config.JdbcTempleFactory;
import ua.com.foxminded.domain.dao.EducatorDao;
import ua.com.foxminded.domain.dao.IdCardDao;
import ua.com.foxminded.domain.entity.EducatorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.com.foxminded.Constants.POSTGRES_DB;

public class EducatorMapper implements RowMapper<EducatorEntity> {

    JdbcTemplate jdbcTemplate = new JdbcTempleFactory(POSTGRES_DB).getJdbcTemplate();
    IdCardDao idCardDao = new IdCardDao(jdbcTemplate);
    EducatorDao educatorDao = new EducatorDao(jdbcTemplate);

    @Override
    public EducatorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        EducatorEntity educatorEntity = new EducatorEntity();
        educatorEntity.setId(rs.getInt("id"));
        educatorEntity.setFirstName(rs.getString("firstName"));
        educatorEntity.setLastName(rs.getString("lastName"));
        educatorEntity.setIdCard(idCardDao.findOne(educatorEntity.getId()));
        return educatorEntity;
    }
}
