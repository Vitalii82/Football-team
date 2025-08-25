package com.football.dao;

import com.football.model.Contracts;
import java.util.List;
import java.util.Optional;

public interface ContractsDao {
    int insert(Contracts e);
    List<Contracts> findAll();
    Optional<Contracts> findById(Integer id);
    int delete(Integer id);
}
