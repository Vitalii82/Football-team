package com.football.dao;

import com.football.model.Seasons;
import java.util.List;
import java.util.Optional;

public interface SeasonsDao {
    int insert(Seasons e);
    List<Seasons> findAll();
    Optional<Seasons> findById(Integer id);
    int delete(Integer id);
}
