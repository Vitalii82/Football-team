package com.football.service.impl.jdbc;

import com.football.model.Player;
import com.football.service.PlayerService;
import com.football.service.impl.mybatis.PlayerServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class PlayerServiceImpl implements PlayerService {
    private final PlayerServiceImpl delegate = new PlayerServiceImpl();

    @Override
    public int create(Player e) { return delegate.create(e); }

    @Override
    public List<Player> list() { return delegate.list(); }
}
