package com.football.dao;

import com.football.model.Teams;
import java.util.List;
import java.util.Optional;

public interface TeamsDao {
    int insert(Teams e);
    List<Teams> findAll();
    Optional<Teams> findById(Integer id);
    int delete(Integer id);
}
