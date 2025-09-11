package com.football.service;

import com.football.model.Contract;
import java.util.List;

public interface ContractService {
    int create(Contract e);
    List<Contract> list();
}
