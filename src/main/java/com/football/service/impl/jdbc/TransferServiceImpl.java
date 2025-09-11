package com.football.service.impl.jdbc;

import com.football.model.Transfer;
import com.football.service.TransferService;
import com.football.service.impl.mybatis.TransferServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class TransferServiceImpl implements TransferService {
    private final TransferServiceImpl delegate = new TransferServiceImpl();

    @Override
    public int create(Transfer e) { return delegate.create(e); }

    @Override
    public List<Transfer> list() { return delegate.list(); }
}
