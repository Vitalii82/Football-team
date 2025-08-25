package com.football.dao;

import com.football.model.Players;
import java.util.List;
import java.util.Optional;

public interface PlayersDao {
    int insert(Players e);
    List<Players> findAll();
    Optional<Players> findById(Integer id);
    int delete(Integer id);
}
