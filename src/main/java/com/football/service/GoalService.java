package com.football.service;

import com.football.model.Goal;
import java.util.List;

public interface GoalService {
    int create(Goal e);
    List<Goal> list();
}
