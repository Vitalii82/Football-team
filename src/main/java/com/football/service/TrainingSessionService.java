package com.football.service;

import com.football.model.TrainingSession;
import java.util.List;

public interface TrainingSessionService {
    int create(TrainingSession e);
    List<TrainingSession> list();
}
