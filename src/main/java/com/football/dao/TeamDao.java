package com.football.dao;

import com.football.model.Team;
import java.util.List;
import java.util.Optional;

public interface TeamDao {
    int insert(Team team);
    List<Team> findAll();
    Optional<Team> findById(int id);
    int update(Team team);
    int delete(int id);
}
