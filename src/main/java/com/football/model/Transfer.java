package com.football.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transfer {
    private Integer transferId;
    private Integer playerId;
    private Integer fromTeamId;
    private Integer toTeamId;
    private BigDecimal fee;
    private LocalDate transferDate;

    public Integer getTransferId() { return transferId; }
    public void setTransferId(Integer transferId) { this.transferId = transferId; }

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }

    public Integer getFromTeamId() { return fromTeamId; }
    public void setFromTeamId(Integer fromTeamId) { this.fromTeamId = fromTeamId; }

    public Integer getToTeamId() { return toTeamId; }
    public void setToTeamId(Integer toTeamId) { this.toTeamId = toTeamId; }

    public BigDecimal getFee() { return fee; }
    public void setFee(BigDecimal fee) { this.fee = fee; }

    public LocalDate getTransferDate() { return transferDate; }
    public void setTransferDate(LocalDate transferDate) { this.transferDate = transferDate; }
}
