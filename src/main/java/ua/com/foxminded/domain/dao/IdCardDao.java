package ua.com.foxminded.domain.dao;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.domain.entity.IdCardEntity;
import ua.com.foxminded.domain.entity.mapperEntity.IdCardMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class IdCardDao implements CrudOperation<IdCardEntity,Integer>{
    private final String INSERT = "insert into idCard (dateExpire) values (?)";
    private final String FIND_BY_ID = "select * from idCard where id = ?";
    private final String FIND_ALL = "select * from idCard";
    private final String UPDATE = "update idCard set dateExpire=? where id=? ";
    private final String DELETE = "delete from idCard where id = ?";
    private final JdbcTemplate jdbcTemplate;


    @Override
    public IdCardEntity save(IdCardEntity entity) {
       KeyHolder keyH = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1,Timestamp.valueOf(entity.getDataExpire()));
            return preparedStatement;
        },keyH);
        entity.setCardId((Integer) keyH.getKeys().get("cardId"));
        return entity;
    }

    @Override
    public List<IdCardEntity> readAll() {
        return jdbcTemplate.query(FIND_ALL,new IdCardMapper());
    }

    @Override
    public IdCardEntity findOne(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,new Object[]{id},new IdCardMapper());
    }

    @Override
    public IdCardEntity update(IdCardEntity entity) {
        jdbcTemplate.update(UPDATE,
                Timestamp.valueOf(entity.getDataExpire()),
                entity.getCardId());
        return entity;
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE,id);
    }
}
