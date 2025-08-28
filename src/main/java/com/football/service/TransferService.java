package com.football.service;
import com.football.dao.ContractsDao;
import com.football.dao.PlayersDao;
import com.football.dao.TransfersDao;
import com.football.dao.impl.ContractsDaoJdbc;
import com.football.dao.impl.PlayersDaoJdbc;
import com.football.dao.impl.TransfersDaoJdbc;
import com.football.model.Contract;
import com.football.model.Transfer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferService {
    private final ContractsDao contracts = new ContractsDaoJdbc();
    private final TransfersDao transfers = new TransfersDaoJdbc();
    private final PlayersDao players = new PlayersDaoJdbc();

    public int buyPlayer(int playerId, Integer toTeamId, BigDecimal fee) {
        // end current contract if exists
        contracts.endActiveContract(playerId, LocalDate.now());
        Integer fromTeamId = contracts.findActiveByPlayer(playerId).map(Contract::getTeamId).orElse(null); // after end likely empty
        // create new contract
        Contract c = new Contract();
        c.setPlayerId(playerId);
        c.setTeamId(toTeamId);
        c.setStartDate(LocalDate.now());
        c.setSalary(new BigDecimal("0"));
        contracts.insert(c);
        // log transfer
        Transfer t = new Transfer();
        t.setPlayerId(playerId);
        t.setFromTeamId(fromTeamId);
        t.setToTeamId(toTeamId);
        t.setTransferFee(fee==null? BigDecimal.ZERO: fee);
        t.setTransferDate(LocalDate.now());
        return transfers.insert(t);
    }
}
