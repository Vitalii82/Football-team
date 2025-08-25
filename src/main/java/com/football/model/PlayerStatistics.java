package com.football.model;


public class PlayerStatistics {
    private Integer playerId;
    private Integer seasonId;
    private Integer matchesPlayed;
    private Integer goalsScored;
    private Integer assists;
    private Integer yellowCards;
    private Integer redCards;

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }

    public Integer getSeasonId() { return seasonId; }
    public void setSeasonId(Integer seasonId) { this.seasonId = seasonId; }

    public Integer getMatchesPlayed() { return matchesPlayed; }
    public void setMatchesPlayed(Integer matchesPlayed) { this.matchesPlayed = matchesPlayed; }

    public Integer getGoalsScored() { return goalsScored; }
    public void setGoalsScored(Integer goalsScored) { this.goalsScored = goalsScored; }

    public Integer getAssists() { return assists; }
    public void setAssists(Integer assists) { this.assists = assists; }

    public Integer getYellowCards() { return yellowCards; }
    public void setYellowCards(Integer yellowCards) { this.yellowCards = yellowCards; }

    public Integer getRedCards() { return redCards; }
    public void setRedCards(Integer redCards) { this.redCards = redCards; }
}
