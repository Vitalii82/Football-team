package com.football.service.impl.jdbc;

import com.football.model.Matche;
import com.football.service.MatcheService;
import com.football.service.impl.mybatis.MatcheServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class MatcheServiceImpl implements MatcheService {
    private final MatcheServiceImpl delegate = new MatcheServiceImpl();

    @Override
    public int create(Matche e) { return delegate.create(e); }

    @Override
    public List<Matche> list() { return delegate.list(); }
}
