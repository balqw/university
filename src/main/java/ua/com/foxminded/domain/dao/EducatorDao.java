package ua.com.foxminded.domain.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.com.foxminded.domain.entity.EducatorEntity;
import ua.com.foxminded.domain.entity.mapperEntity.EducatorMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class EducatorDao implements  CrudOperation<EducatorEntity,Integer>{
    private final String INSERT = "insert into educator (firstName,lastName) values(?,?)";
    private final String FIND_BY_ID = "select * from educator join idcard on educator.idcard = idcard.id where id = ?";
    private final String FIND_ALL = "select * from educator";
    private final String UPDATE = "update educator set first_name=?,last_name=?,idCard=? where id=? ";
    private final String DELETE = "delete from educator where id = ?";
    private final String SET_ID_CARD = "insert into educatorCard (idCard, idEducator) values(?,?)";
    private final JdbcTemplate jdbcTemplate;

    public EducatorDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public EducatorEntity save(EducatorEntity entity) {
        KeyHolder keyH = new GeneratedKeyHolder();
         jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,entity.getFirstName());
            ps.setString(2,entity.getLastName());
            return ps;
        },keyH);
         entity.setEducatorId((Integer) keyH.getKeys().get("educatorId"));
         return entity;
    }

    @Override
    public List<EducatorEntity> readAll() {
        return jdbcTemplate.query(FIND_ALL,new EducatorMapper());
    }

    @Override
    public EducatorEntity findOne(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id},new EducatorMapper());
    }

    @Override
    public EducatorEntity update(EducatorEntity entity) {
        jdbcTemplate.update(UPDATE,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getIdCard().getCardId());
        return entity;
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE,id);
    }

    public EducatorEntity setIdCard(EducatorEntity entity){
        jdbcTemplate.update(SET_ID_CARD,entity.getIdCard(),
                entity.getIdCard().getCardId());
        return entity;
    }
}
