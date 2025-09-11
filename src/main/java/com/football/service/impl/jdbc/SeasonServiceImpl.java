package com.football.service.impl.jdbc;

import com.football.model.Season;
import com.football.service.SeasonService;
import com.football.service.impl.mybatis.SeasonServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class SeasonServiceImpl implements SeasonService {
    private final SeasonServiceImpl delegate = new SeasonServiceImpl();

    @Override
    public int create(Season e) { return delegate.create(e); }

    @Override
    public List<Season> list() { return delegate.list(); }
}
