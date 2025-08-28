package com.football.model;
import java.time.LocalDate;
public class Contract {
    private Integer contractId;
    private Integer playerId;
    private Integer teamId;
    private LocalDate startDate;
    private LocalDate endDate;
    private java.math.BigDecimal salary;
    public Integer getContractId(){return contractId;}
    public void setContractId(Integer v){this.contractId=v;}
    public Integer getPlayerId(){return playerId;}
    public void setPlayerId(Integer v){this.playerId=v;}
    public Integer getTeamId(){return teamId;}
    public void setTeamId(Integer v){this.teamId=v;}
    public LocalDate getStartDate(){return startDate;}
    public void setStartDate(LocalDate v){this.startDate=v;}
    public LocalDate getEndDate(){return endDate;}
    public void setEndDate(LocalDate v){this.endDate=v;}
    public java.math.BigDecimal getSalary(){return salary;}
    public void setSalary(java.math.BigDecimal v){this.salary=v;}
}
