package com.football.model;
import java.time.LocalDate;
public class Match {
    private Integer matchId;
    private Integer seasonId;
    private LocalDate matchDate;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private Integer stadiumId;
    public Integer getMatchId(){return matchId;}
    public void setMatchId(Integer v){this.matchId=v;}
    public Integer getSeasonId(){return seasonId;}
    public void setSeasonId(Integer v){this.seasonId=v;}
    public LocalDate getMatchDate(){return matchDate;}
    public void setMatchDate(LocalDate v){this.matchDate=v;}
    public Integer getHomeTeamId(){return homeTeamId;}
    public void setHomeTeamId(Integer v){this.homeTeamId=v;}
    public Integer getAwayTeamId(){return awayTeamId;}
    public void setAwayTeamId(Integer v){this.awayTeamId=v;}
    public Integer getStadiumId(){return stadiumId;}
    public void setStadiumId(Integer v){this.stadiumId=v;}
}
