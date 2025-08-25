package com.football.dao;

import com.football.model.Stadiums;
import java.util.List;
import java.util.Optional;

public interface StadiumsDao {
    int insert(Stadiums e);
    List<Stadiums> findAll();
    Optional<Stadiums> findById(Integer id);
    int delete(Integer id);
}
