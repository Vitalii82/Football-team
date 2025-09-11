package com.football.service.impl.jdbc;

import com.football.model.Goal;
import com.football.service.GoalService;
import com.football.service.impl.mybatis.GoalServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class GoalServiceImpl implements GoalService {
    private final GoalServiceImpl delegate = new GoalServiceImpl();

    @Override
    public int create(Goal e) { return delegate.create(e); }

    @Override
    public List<Goal> list() { return delegate.list(); }
}
