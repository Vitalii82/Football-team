package com.football.dao;
import com.football.model.Team;
import java.util.List;
import java.util.Optional;
public interface TeamsDao {
    int insert(Team t);
    List<Team> findAll();
    Optional<Team> findByName(String name);
    Optional<Team> findById(int id);
}
