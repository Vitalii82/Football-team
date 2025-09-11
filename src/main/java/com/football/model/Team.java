package com.football.model;


public class Team {
    private Integer teamId;
    private String teamName;
    private Integer foundedYear;
    private Integer stadiumId;
    private Integer managerId;

    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public Integer getFoundedYear() { return foundedYear; }
    public void setFoundedYear(Integer foundedYear) { this.foundedYear = foundedYear; }

    public Integer getStadiumId() { return stadiumId; }
    public void setStadiumId(Integer stadiumId) { this.stadiumId = stadiumId; }

    public Integer getManagerId() { return managerId; }
    public void setManagerId(Integer managerId) { this.managerId = managerId; }
}
