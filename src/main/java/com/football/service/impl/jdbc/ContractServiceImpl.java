package com.football.service.impl.jdbc;

import com.football.model.Contract;
import com.football.service.ContractService;
import com.football.service.impl.mybatis.ContractServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class ContractServiceImpl implements ContractService {
    private final ContractServiceImpl delegate = new ContractServiceImpl();

    @Override
    public int create(Contract e) { return delegate.create(e); }

    @Override
    public List<Contract> list() { return delegate.list(); }
}
