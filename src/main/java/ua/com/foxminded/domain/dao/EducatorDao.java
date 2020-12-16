package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.mapperEntity.EducatorMapper;
import ua.com.foxminded.domain.exceptions.NotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import static java.lang.String.format;

@Repository
@RequiredArgsConstructor
public class EducatorDao implements CrudOperation<EducatorEntity, Integer> {
    private final String INSERT = "insert into educator (firstName,lastName,idCard) values(?,?,?)";
    private final String FIND_BY_ID = "select * from educator full join idcard on educator.idcard = idcard.cardid where educatorId = ?";
    private final String FIND_ALL   = "select * from educator full join idcard on educator.idcard = idcard.cardid";
    private final String UPDATE = "update educator set firstName=?,lastName=?,idCard=? where educatorId=? ";
    private final String DELETE = "delete from educator where educatorId = ?";
    private final String COUNT = "select count(educatorId) from educator where educatorId=?";
    private final String UNIQ = "select count (*) from educator where firstName = ? and lastName = ?";
    private final JdbcTemplate jdbcTemplate;
    private final static Logger logger = LoggerFactory.getLogger(EducatorDao.class);


    @Override
    public EducatorEntity save(EducatorEntity entity) {
        KeyHolder keyH = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            if (entity.getIdCard() != null && entity.getIdCard().getCardId() != null) {
                ps.setInt(3, entity.getIdCard().getCardId());
            } else ps.setNull(3, Types.INTEGER);

            return ps;
        }, keyH);
        entity.setEducatorId((Integer) keyH.getKeys().get("educatorId"));
        logger.debug("save educator {}", entity);
        return entity;
    }

    @Override
    public List<EducatorEntity> readAll() {
        logger.debug("read all educators");
        return jdbcTemplate.query(FIND_ALL, new EducatorMapper());
    }

    @Override
    public EducatorEntity findOne(Integer id) {
        logger.debug("find educator with id {}", id);
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new EducatorMapper());
        } catch (RuntimeException e) {
            logger.error("find educator with id {} failed", id, e);
            String msg = format("educator with id = '%s' not exist", id);
            throw new NotFoundException(msg);
        }
    }

    @Override
    public EducatorEntity update(EducatorEntity entity) {
        logger.debug("update educator {}", entity);
        jdbcTemplate.update(UPDATE,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getIdCard().getCardId(),
                entity.getEducatorId());
        return entity;
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete educator with id {}", id);
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public boolean exist(EducatorEntity educator) {
        return jdbcTemplate.queryForObject(COUNT, new Object[]{educator.getEducatorId()}, Integer.class) > 0;
    }

    public boolean exist(String fName, String lName) {
        return jdbcTemplate.queryForObject(UNIQ, new Object[]{fName, lName}, Integer.class) > 0;
    }
}
