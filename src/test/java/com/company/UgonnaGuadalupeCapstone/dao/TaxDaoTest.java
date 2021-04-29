package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Console;
import com.company.UgonnaGuadalupeCapstone.model.Games;
import com.company.UgonnaGuadalupeCapstone.model.Tshirt;
import com.company.UgonnaGuadalupeCapstone.model.Tax;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxDaoTest {

    @Autowired
    TaxDao taxDao;

//    @Test
//    public void getProcessingFee(){
//        Tax tax = new Tax();
//
//
//    }
//    @Test
//    public void getAllGames(){
//        Games games = new Games();
//        games.setTitle("Game Title");
//        games.setEsrbRating("4.55");
//        games.setDescription("This is a test game");
//        games.setPrice(new BigDecimal("3.44"));
//        games.setStudio("studio test");
//        games.setQuantity(50);
//
//        gamesDao.addGame(games);
//
//        games = new Games();
//        games.setTitle("Game Title2");
//        games.setEsrbRating("3.35");
//        games.setDescription("This is a second test game");
//        games.setPrice(new BigDecimal("3.33"));
//        games.setStudio("studio test2");
//        games.setQuantity(60);
//
//        gamesDao.addGame(games);
//
//        List<Games> gamesList = gamesDao.getAllGames();
//
//        assertEquals(2, gamesList.size());
//
//    }
}
