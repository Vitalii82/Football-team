package com.football.model;

import java.math.BigDecimal;

public class PlayerStatistics {
    private Integer statId;
    private Integer matchId;
    private Integer playerId;
    private Integer minutesPlayed;
    private Integer goals;
    private Integer assists;
    private Integer yellowCards;
    private Integer redCards;
    private BigDecimal rating;

    public Integer getStatId() { return statId; }
    public void setStatId(Integer statId) { this.statId = statId; }

    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }

    public Integer getMinutesPlayed() { return minutesPlayed; }
    public void setMinutesPlayed(Integer minutesPlayed) { this.minutesPlayed = minutesPlayed; }

    public Integer getGoals() { return goals; }
    public void setGoals(Integer goals) { this.goals = goals; }

    public Integer getAssists() { return assists; }
    public void setAssists(Integer assists) { this.assists = assists; }

    public Integer getYellowCards() { return yellowCards; }
    public void setYellowCards(Integer yellowCards) { this.yellowCards = yellowCards; }

    public Integer getRedCards() { return redCards; }
    public void setRedCards(Integer redCards) { this.redCards = redCards; }

    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }
}
