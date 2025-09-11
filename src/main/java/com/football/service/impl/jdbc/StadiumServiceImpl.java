package com.football.service.impl.jdbc;

import com.football.model.Stadium;
import com.football.service.StadiumService;
import com.football.service.impl.mybatis.StadiumServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class StadiumServiceImpl implements StadiumService {
    private final StadiumServiceImpl delegate = new StadiumServiceImpl();

    @Override
    public int create(Stadium e) { return delegate.create(e); }

    @Override
    public List<Stadium> list() { return delegate.list(); }
}
