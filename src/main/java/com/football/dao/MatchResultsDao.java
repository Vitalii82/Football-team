package com.football.dao;

import com.football.model.MatchResults;
import java.util.List;
import java.util.Optional;

public interface MatchResultsDao {
    int insert(MatchResults e);
    List<MatchResults> findAll();
    Optional<MatchResults> findById(Integer id);
    int delete(Integer id);
}
