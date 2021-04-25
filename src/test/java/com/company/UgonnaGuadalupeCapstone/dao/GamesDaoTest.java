package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Games;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GamesDaoTest {

    @Autowired
    GamesDao gamesDao;

    @Before
    public void setUp() throws Exception{
        //clean test db
        List<Games> gList = gamesDao.getAllGames();
        for (Games g : gList ){
            gamesDao.deleteGame(g.getGameId());
        }
    }

    @Test
    public void addGetDeleteGames(){

        Games games = new Games();
        games.setTitle("Game Title");
        games.setEsrbRating("4.55");
        games.setDescription("This is a test game");
        games.setPrice(new BigDecimal("3.44"));
        games.setStudio("studio test");
        games.setQuantity(50);

        games = gamesDao.addGame(games);

        Games games1 = gamesDao.getGame(games.getGameId());
        assertEquals(games1, games);

        gamesDao.deleteGame(games.getGameId());
        games1 = gamesDao.getGame(games.getGameId());

        assertNull(games1);
    }

    @Test
    public void getAllGames(){
        Games games = new Games();
        games.setTitle("Game Title");
        games.setEsrbRating("4.55");
        games.setDescription("This is a test game");
        games.setPrice(new BigDecimal("3.44"));
        games.setStudio("studio test");
        games.setQuantity(50);

        gamesDao.addGame(games);

        games = new Games();
        games.setTitle("Game Title2");
        games.setEsrbRating("3.35");
        games.setDescription("This is a second test game");
        games.setPrice(new BigDecimal("3.33"));
        games.setStudio("studio test2");
        games.setQuantity(60);

        gamesDao.addGame(games);

        List<Games> gamesList = gamesDao.getAllGames();

        assertEquals(2, gamesList.size());

    }

    @Test
    public void getAllGamesByStudio(){

        Games games = new Games();
        games.setTitle("Game Title");
        games.setEsrbRating("4.55");
        games.setDescription("This is a test game");
        games.setPrice(new BigDecimal("3.44"));
        games.setStudio("studio test");
        games.setQuantity(50);

        gamesDao.addGame(games);

        Games games1 = new Games();
        games1.setTitle("Game Title4");
        games1.setEsrbRating("4.35");
        games1.setDescription("This is a test4 game");
        games1.setPrice(new BigDecimal("3.34"));
        games1.setStudio("studio test");
        games1.setQuantity(20);

        gamesDao.addGame(games1);

        List<Games> gamesList = gamesDao.getGameByStudio("studio test");

        assertEquals(gamesList.size(), 2);
    }

    @Test
    public void getAllGamesByESRB(){

        Games games = new Games();
        games.setTitle("Game Title");
        games.setEsrbRating("4.35");
        games.setDescription("This is a test game");
        games.setPrice(new BigDecimal("3.44"));
        games.setStudio("studio test");
        games.setQuantity(50);

        gamesDao.addGame(games);

        Games games1 = new Games();
        games1.setTitle("Game Title4");
        games1.setEsrbRating("4.35");
        games1.setDescription("This is a test4 game");
        games1.setPrice(new BigDecimal("3.34"));
        games1.setStudio("studio test");
        games1.setQuantity(20);

        gamesDao.addGame(games1);

        List<Games> gamesList = gamesDao.getGameByESRB("4.35");

        assertEquals(gamesList.size(), 2);
    }

    @Test
    public void getAllGamesByTitle(){

        Games games = new Games();
        games.setTitle("Game Title");
        games.setEsrbRating("4.55");
        games.setDescription("This is a test game");
        games.setPrice(new BigDecimal("3.44"));
        games.setStudio("studio test");
        games.setQuantity(50);

        gamesDao.addGame(games);

        Games games1 = new Games();
        games1.setTitle("Game Title");
        games1.setEsrbRating("4.35");
        games1.setDescription("This is a test4 game");
        games1.setPrice(new BigDecimal("3.34"));
        games1.setStudio("studio test");
        games1.setQuantity(20);

        gamesDao.addGame(games1);

        List<Games> gamesList = gamesDao.getGameByTitle("Game Title");

        assertEquals(gamesList.size(), 2);
    }

    @Test
    public void updateGames(){
        Games games = new Games();
        games.setTitle("Game Title");
        games.setEsrbRating("4.55");
        games.setDescription("This is a test game");
        games.setPrice(new BigDecimal("3.44"));
        games.setStudio("studio test");
        games.setQuantity(50);

        gamesDao.addGame(games);

        games.setQuantity(40);
        games.setStudio("studio test 2");

        gamesDao.updateGame(games);

        Games games1 = gamesDao.getGame(games.getGameId());

        assertEquals(games, games1);
    }
}
