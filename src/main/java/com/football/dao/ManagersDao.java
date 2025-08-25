package com.football.dao;

import com.football.model.Managers;
import java.util.List;
import java.util.Optional;

public interface ManagersDao {
    int insert(Managers e);
    List<Managers> findAll();
    Optional<Managers> findById(Integer id);
    int delete(Integer id);
}
