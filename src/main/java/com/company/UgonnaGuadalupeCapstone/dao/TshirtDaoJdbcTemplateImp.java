package com.company.UgonnaGuadalupeCapstone.dao;


import com.company.UgonnaGuadalupeCapstone.model.Tshirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TshirtDaoJdbcTemplateImp implements TshirtDao{

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_TSHIRT_SQL =
            "insert into t_shirt (size, color, description, price, quantity) values (?, ?, ?, ?, ?)";
    private static final String SELECT_TSHIRT_SQL =
            "select * from t_shirt where t_shirt_id = ?";
    private static final String SELECT_ALL_TSHIRT_SQL =
            "select * from t_shirt";
    private static final String UPDATE_TSHIRT_SQL =
            "update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? where t_shirt_id = ?";
    private static final String DELETE_TSHIRT =
            "delete from t_shirt where t_shirt_id = ?";
    private static final String SELECT_TSHIRT_BY_COLOR =
            "select * from t_shirt where color = ?";
    private static final String SELECT_TSHIRT_BY_SIZE =
            "select * from t_shirt where size = ?";

    @Autowired
    public TshirtDaoJdbcTemplateImp(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Tshirt addTshirt(Tshirt tshirt){

        jdbcTemplate.update(
                INSERT_TSHIRT_SQL,
                tshirt.getSize(),
                tshirt.getColor(),
                tshirt.getDescription(),
                tshirt.getPrice(),
                tshirt.getQuantity());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        tshirt.setTshirtId(id);

        return tshirt;
    }

    @Override
    public Tshirt getTshirt(int id){

        try {
            return jdbcTemplate.queryForObject(SELECT_TSHIRT_SQL, this::mapRowToConsole, id);
        }catch (EmptyResultDataAccessException e){
            //if there is no match for this tshirt id, return null
            return null;
        }
    }

    @Override
    public List<Tshirt> getAllTshirt() {
        return jdbcTemplate.query(SELECT_ALL_TSHIRT_SQL, this::mapRowToConsole);
    }

    @Override
    public List<Tshirt> getTshirtByColor(String color) {
        return jdbcTemplate.query(SELECT_TSHIRT_BY_COLOR, this::mapRowToConsole, color);
    }

    @Override
    public List<Tshirt> getTshirtBySize(String size) {
        return jdbcTemplate.query(SELECT_TSHIRT_BY_SIZE, this::mapRowToConsole, size);
    }

    @Override
    public void updateTshirt(Tshirt tshirt) {
        jdbcTemplate.update(
                UPDATE_TSHIRT_SQL,
                tshirt.getSize(),
                tshirt.getColor(),
                tshirt.getDescription(),
                tshirt.getPrice(),
                tshirt.getQuantity(),
                tshirt.getTshirtId());
    }

    @Override
    public void deleteTshirt(int id) {
        jdbcTemplate.update(DELETE_TSHIRT, id);

    }


    //row mapper
    private Tshirt mapRowToConsole(ResultSet rs, int rowNum) throws SQLException {
        Tshirt tshirt = new Tshirt();
        tshirt.setTshirtId(rs.getInt("t_shirt_id"));
        tshirt.setSize(rs.getString("size"));
        tshirt.setColor(rs.getString("color"));
        tshirt.setDescription(rs.getString("description"));
        tshirt.setPrice(rs.getBigDecimal("price"));
        tshirt.setQuantity(rs.getInt("quantity"));
        return tshirt;

    }



}
