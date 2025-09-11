package com.football.service.impl.jdbc;

import com.football.model.MatchResults;
import com.football.service.MatchResultsService;
import com.football.service.impl.mybatis.MatchResultsServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class MatchResultsServiceImpl implements MatchResultsService {
    private final MatchResultsServiceImpl delegate = new MatchResultsServiceImpl();

    @Override
    public int create(MatchResults e) { return delegate.create(e); }

    @Override
    public List<MatchResults> list() { return delegate.list(); }
}
