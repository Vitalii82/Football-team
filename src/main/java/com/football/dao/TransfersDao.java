package com.football.dao;

import com.football.model.Transfers;
import java.util.List;
import java.util.Optional;

public interface TransfersDao {
    int insert(Transfers e);
    List<Transfers> findAll();
    Optional<Transfers> findById(Integer id);
    int delete(Integer id);
}
