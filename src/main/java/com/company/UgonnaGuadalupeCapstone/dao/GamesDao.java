package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Games;

import java.util.List;

public interface GamesDao {

    Games addGame(Games games);
    Games getGame(int id);
    List<Games> getAllGames();
    void updateGame(Games games);
    void deleteGame(int id);
    List<Games> getGameByStudio(String studio);
    List<Games> getGameByESRB(String esrb);
    List<Games> getGameByTitle(String title);
}
