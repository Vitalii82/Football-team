package com.football.service.impl.jdbc;

import com.football.model.Team;
import com.football.service.TeamService;
import com.football.service.impl.mybatis.TeamServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class TeamServiceImpl implements TeamService {
    private final TeamServiceImpl delegate = new TeamServiceImpl();

    @Override
    public int create(Team e) { return delegate.create(e); }

    @Override
    public List<Team> list() { return delegate.list(); }
}
