package com.football.service.impl.jdbc;

import com.football.model.PlayerPositions;
import com.football.service.PlayerPositionsService;
import com.football.service.impl.mybatis.PlayerPositionsServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class PlayerPositionsServiceImpl implements PlayerPositionsService {
    private final PlayerPositionsServiceImpl delegate = new PlayerPositionsServiceImpl();

    @Override
    public int create(PlayerPositions e) { return delegate.create(e); }

    @Override
    public List<PlayerPositions> list() { return delegate.list(); }
}
