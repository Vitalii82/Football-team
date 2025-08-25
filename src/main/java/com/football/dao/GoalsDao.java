package com.football.dao;

import com.football.model.Goals;
import java.util.List;
import java.util.Optional;

public interface GoalsDao {
    int insert(Goals e);
    List<Goals> findAll();
    Optional<Goals> findById(Integer id);
    int delete(Integer id);
}
