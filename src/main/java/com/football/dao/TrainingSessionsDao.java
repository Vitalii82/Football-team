package com.football.dao;

import com.football.model.TrainingSessions;
import java.util.List;
import java.util.Optional;

public interface TrainingSessionsDao {
    int insert(TrainingSessions e);
    List<TrainingSessions> findAll();
    Optional<TrainingSessions> findById(Integer id);
    int delete(Integer id);
}
