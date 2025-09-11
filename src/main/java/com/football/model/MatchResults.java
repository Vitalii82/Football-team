package com.football.model;


public class MatchResults {
    private Integer matchId;
    private Integer homeGoals;
    private Integer awayGoals;
    private Integer winnerTeamId;

    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }

    public Integer getHomeGoals() { return homeGoals; }
    public void setHomeGoals(Integer homeGoals) { this.homeGoals = homeGoals; }

    public Integer getAwayGoals() { return awayGoals; }
    public void setAwayGoals(Integer awayGoals) { this.awayGoals = awayGoals; }

    public Integer getWinnerTeamId() { return winnerTeamId; }
    public void setWinnerTeamId(Integer winnerTeamId) { this.winnerTeamId = winnerTeamId; }
}
