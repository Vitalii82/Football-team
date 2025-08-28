package com.football.model;
public class Team {
    private Integer teamId;
    private String teamName;
    private Integer foundedYear;
    private Integer stadiumId;
    private Integer managerId;
    public Integer getTeamId(){return teamId;}
    public void setTeamId(Integer v){this.teamId=v;}
    public String getTeamName(){return teamName;}
    public void setTeamName(String v){this.teamName=v;}
    public Integer getFoundedYear(){return foundedYear;}
    public void setFoundedYear(Integer v){this.foundedYear=v;}
    public Integer getStadiumId(){return stadiumId;}
    public void setStadiumId(Integer v){this.stadiumId=v;}
    public Integer getManagerId(){return managerId;}
    public void setManagerId(Integer v){this.managerId=v;}
}
