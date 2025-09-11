package com.football.mybatis.mapper;

import com.football.model.TrainingSession;
import java.util.List;

public interface TrainingSessionMapper {
    List<TrainingSession> findAll();
    int insert(TrainingSession e);
}
