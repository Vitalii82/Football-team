package com.football.service.impl.jdbc;

import com.football.model.TrainingSession;
import com.football.service.TrainingSessionService;
import com.football.service.impl.mybatis.TrainingSessionServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class TrainingSessionServiceImpl implements TrainingSessionService {
    private final TrainingSessionServiceImpl delegate = new TrainingSessionServiceImpl();

    @Override
    public int create(TrainingSession e) { return delegate.create(e); }

    @Override
    public List<TrainingSession> list() { return delegate.list(); }
}
