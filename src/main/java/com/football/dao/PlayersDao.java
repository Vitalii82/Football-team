package com.football.dao;
import com.football.model.Player;
import java.util.List;
import java.util.Optional;
public interface PlayersDao {
    int insert(Player p);
    Optional<Player> findById(int id);
    List<Player> findAll();
}
