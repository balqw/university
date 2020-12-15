package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.domain.entity.mapperEntity.IdCardMapper;
import ua.com.foxminded.domain.exceptions.NotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import static java.lang.String.format;

@Repository
@RequiredArgsConstructor
public class IdCardDao implements CrudOperation<IdCardEntity, Integer> {
    private final String INSERT = "insert into idCard (dateExpire) values (?)";
    private final String FIND_BY_ID = "select * from idCard where cardId = ?";
    private final String FIND_ALL = "select * from idCard";
    private final String UPDATE = "update idCard set dateExpire=? where cardId=? ";
    private final String DELETE = "delete from idCard where cardId = ?";
    private final JdbcTemplate jdbcTemplate;
    private final static Logger logger = LoggerFactory.getLogger(IdCardDao.class);
    private final String COUNT = "select count(cardId) from idCard where cardId=?";

    @Override
    public IdCardEntity save(IdCardEntity entity) {
        KeyHolder keyH = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(entity.getDataExpire()));
            return preparedStatement;
        }, keyH);
        entity.setCardId((Integer) keyH.getKeys().get("cardId"));
        logger.debug("save idCard {}", entity);
        return entity;
    }

    @Override
    public List<IdCardEntity> readAll() {
        logger.debug("read all idCards");
        return jdbcTemplate.query(FIND_ALL, new IdCardMapper());
    }

    @Override
    public IdCardEntity findOne(Integer id) {
        logger.debug("find idCard with id {}", id);
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new IdCardMapper());
        } catch (RuntimeException e) {
            logger.error("find idCard with id {} failed", id, e);
            String msg = format("idCard with id = '%s' not exist", id);
            throw new NotFoundException(msg);
        }
    }

    @Override
    public IdCardEntity update(IdCardEntity entity) {
        logger.debug("update idCard {}", entity);
        jdbcTemplate.update(UPDATE,
                Timestamp.valueOf(entity.getDataExpire()),
                entity.getCardId());
        return entity;
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete idCard with id {}", id);
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public boolean exist(IdCardEntity idCard) {
        return jdbcTemplate.queryForObject(COUNT, new Object[]{idCard.getCardId()}, Integer.class) > 0;
    }

}
