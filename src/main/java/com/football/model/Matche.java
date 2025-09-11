package com.football.model;

import java.time.LocalDate;

public class Matche {
    private Integer matchId;
    private Integer seasonId;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private LocalDate matchDate;
    private Integer stadiumId;

    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }

    public Integer getSeasonId() { return seasonId; }
    public void setSeasonId(Integer seasonId) { this.seasonId = seasonId; }

    public Integer getHomeTeamId() { return homeTeamId; }
    public void setHomeTeamId(Integer homeTeamId) { this.homeTeamId = homeTeamId; }

    public Integer getAwayTeamId() { return awayTeamId; }
    public void setAwayTeamId(Integer awayTeamId) { this.awayTeamId = awayTeamId; }

    public LocalDate getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDate matchDate) { this.matchDate = matchDate; }

    public Integer getStadiumId() { return stadiumId; }
    public void setStadiumId(Integer stadiumId) { this.stadiumId = stadiumId; }
}
