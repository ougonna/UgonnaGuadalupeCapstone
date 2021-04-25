package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Games;

import java.util.List;

public interface GamesDao {

    public Games addGame(Games games);
    public Games getGame(int id);
    public List<Games> getAllGames();
    public void updateGame(Games games);
    public void deleteGame(int id);
    public List<Games> getGameByStudio(String studio);
    public List<Games> getGameByESRB(String esrb);
    public List<Games> getGameByTitle(String title);
}
