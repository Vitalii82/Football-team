package com.football.service.impl.jdbc;

import com.football.model.Manager;
import com.football.service.ManagerService;
import com.football.service.impl.mybatis.ManagerServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class ManagerServiceImpl implements ManagerService {
    private final ManagerServiceImpl delegate = new ManagerServiceImpl();

    @Override
    public int create(Manager e) { return delegate.create(e); }

    @Override
    public List<Manager> list() { return delegate.list(); }
}
