package com.company.UgonnaGuadalupeCapstone.dao;


import com.company.UgonnaGuadalupeCapstone.model.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GamesDaoJdbcTemplateImp implements GamesDao {

    private static final String INSERT_GAME_SQL =
            "insert into game (title, esrb_rating, description, price, studio, quantity) values (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_GAME_SQL =
            "select * from game where game_id = ?";
    private static final String SELECT_ALL_GAMES =
            "select * from game";
    private static final String UPDATE_GAMES =
            "update game set title = ?, esrb_rating = ?, description = ?, price = ?, studio = ?, quantity = ? where game_id = ?";
    private static final String DELETE_GAME_SQL =
            "delete from game where game_id = ?";
    private static final String SELECT_GAME_BY_STUDIO =
            "select * from game where studio = ?";
    private static final String SELECT_GAME_BY_ESRB =
            "select * from game where esrb_rating = ?";
    private static final String SELECT_GAME_BY_TITLE =
            "select * from game where title = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public GamesDaoJdbcTemplateImp(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Games addGame(Games games){

        jdbcTemplate.update(INSERT_GAME_SQL,
                games.getTitle(),
                games.getEsrbRating(),
                games.getDescription(),
                games.getPrice(),
                games.getStudio(),
                games.getQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        games.setGameId(id);
        return games;
    }

    @Override
    public Games getGame(int id){
        try {
            return jdbcTemplate.queryForObject(SELECT_GAME_SQL, this::mapRowToGame, id);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Games> getAllGames(){
        return jdbcTemplate.query(SELECT_ALL_GAMES, this::mapRowToGame);
    }

    @Override
    public List<Games> getGameByStudio(String studio){
        return jdbcTemplate.query(SELECT_GAME_BY_STUDIO, this::mapRowToGame, studio);
    }

    @Override
    public List<Games> getGameByESRB(String esrb){
        return jdbcTemplate.query(SELECT_GAME_BY_ESRB, this::mapRowToGame, esrb);
    }

    @Override
    public List<Games> getGameByTitle(String title){
        return jdbcTemplate.query(SELECT_GAME_BY_TITLE, this::mapRowToGame, title);
    }

    @Override
    public void updateGame(Games games){
        jdbcTemplate.update(UPDATE_GAMES,
                games.getTitle(),
                games.getEsrbRating(),
                games.getDescription(),
                games.getPrice(),
                games.getStudio(),
                games.getQuantity(),
                games.getGameId());
    }

    @Override
    public void deleteGame(int id){
        jdbcTemplate.update(DELETE_GAME_SQL, id);
    }

    //row mapper
    private Games mapRowToGame(ResultSet rs, int rowNum) throws SQLException {
        Games games = new Games();
        games.setGameId(rs.getInt("game_id"));
        games.setTitle(rs.getString("title"));
        games.setEsrbRating(rs.getString("esrb_rating"));
        games.setDescription(rs.getString("description"));
        games.setPrice(rs.getBigDecimal("price"));
        games.setStudio(rs.getString("studio"));
        games.setQuantity(rs.getInt("quantity"));

        return games;
    }



}
