package com.football.mybatis.mapper;

import com.football.model.Goal;
import java.util.List;

public interface GoalMapper {
    List<Goal> findAll();
    int insert(Goal e);
}
