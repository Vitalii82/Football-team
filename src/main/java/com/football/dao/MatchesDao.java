package com.football.dao;

import com.football.model.Matches;
import java.util.List;
import java.util.Optional;

public interface MatchesDao {
    int insert(Matches e);
    List<Matches> findAll();
    Optional<Matches> findById(Integer id);
    int delete(Integer id);
}
