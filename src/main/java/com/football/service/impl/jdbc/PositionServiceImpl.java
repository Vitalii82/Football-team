package com.football.service.impl.jdbc;

import com.football.model.Position;
import com.football.service.PositionService;
import com.football.service.impl.mybatis.PositionServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class PositionServiceImpl implements PositionService {
    private final PositionServiceImpl delegate = new PositionServiceImpl();

    @Override
    public int create(Position e) { return delegate.create(e); }

    @Override
    public List<Position> list() { return delegate.list(); }
}
