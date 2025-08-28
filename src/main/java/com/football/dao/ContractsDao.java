package com.football.dao;
import com.football.model.Contract;
import java.util.Optional;
public interface ContractsDao {
    int insert(Contract c);
    Optional<Contract> findActiveByPlayer(int playerId);
    int endActiveContract(int playerId, java.time.LocalDate endDate);
}
