package com.football.service.impl.jdbc;

import com.football.model.SeasonTeams;
import com.football.service.SeasonTeamsService;
import com.football.service.impl.mybatis.SeasonTeamsServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class SeasonTeamsServiceImpl implements SeasonTeamsService {
    private final SeasonTeamsServiceImpl delegate = new SeasonTeamsServiceImpl();

    @Override
    public int create(SeasonTeams e) { return delegate.create(e); }

    @Override
    public List<SeasonTeams> list() { return delegate.list(); }
}
