package com.company.UgonnaGuadalupeCapstone.controller;


import com.company.UgonnaGuadalupeCapstone.dao.ConsoleDao;
import com.company.UgonnaGuadalupeCapstone.dao.GamesDao;
import com.company.UgonnaGuadalupeCapstone.model.Console;
import com.company.UgonnaGuadalupeCapstone.model.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CacheConfig(cacheNames = {"games"})
public class GamesController {

    @Autowired
    GamesDao dao;

    public GamesController(GamesDao dao){
        this.dao = dao;
    }

    // add game
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Games createGame(@RequestBody Games games){
        System.out.println("creating game");
        return dao.addGame(games);
    }

    //get game by id
    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Games getGames(@PathVariable int id){
        System.out.println("getting game id = " + id);
        return dao.getGame(id);
    }

    //get game by studio
    @RequestMapping(value = "/games/studio/{studio}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Games> getAllGamesByStudio(@PathVariable String studio){
        System.out.println("Getting games by studio.." + studio);
        return dao.getGameByStudio(studio);
    }

    //get game by esrb
    @RequestMapping(value = "/games/esrb/{esrb}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Games> getAllGamesByESRB(@PathVariable String esrb){
        System.out.println("Getting games by esrb.." + esrb);
        return dao.getGameByESRB(esrb);
    }

    //get game by title
    @RequestMapping(value = "/games/title/{title}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Games> getAllGamesByTitle(@PathVariable String title){
        System.out.println("Getting games by title.." + title);
        return dao.getGameByTitle(title);
    }

    //get all games
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Games> getAllGames(){
        System.out.println("Getting all games...");
        return dao.getAllGames();
    }

    //update game
    @RequestMapping(value = "/games", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateGame(@RequestBody Games games){
        System.out.println("Updating game id = " + games.getGameId());
        dao.updateGame(games);
    }

    //delete game
    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteGame(@PathVariable int id){
        System.out.println("Deleting game id = " + id);
        dao.deleteGame(id);
    }

}
