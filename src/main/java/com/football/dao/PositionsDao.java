package com.football.dao;

import com.football.model.Positions;
import java.util.List;
import java.util.Optional;

public interface PositionsDao {
    int insert(Positions e);
    List<Positions> findAll();
    Optional<Positions> findById(Integer id);
    int delete(Integer id);
}
