package com.football.model;
import java.time.LocalDate;
import java.math.BigDecimal;
public class Transfer {
    private Integer transferId;
    private Integer playerId;
    private Integer fromTeamId;
    private Integer toTeamId;
    private BigDecimal transferFee;
    private LocalDate transferDate;
    public Integer getTransferId(){return transferId;}
    public void setTransferId(Integer v){this.transferId=v;}
    public Integer getPlayerId(){return playerId;}
    public void setPlayerId(Integer v){this.playerId=v;}
    public Integer getFromTeamId(){return fromTeamId;}
    public void setFromTeamId(Integer v){this.fromTeamId=v;}
    public Integer getToTeamId(){return toTeamId;}
    public void setToTeamId(Integer v){this.toTeamId=v;}
    public BigDecimal getTransferFee(){return transferFee;}
    public void setTransferFee(BigDecimal v){this.transferFee=v;}
    public LocalDate getTransferDate(){return transferDate;}
    public void setTransferDate(LocalDate v){this.transferDate=v;}
}
