package com.football.model;


public class Goals {
    private Integer goalId;
    private Integer matchId;
    private Integer playerId;
    private Integer minuteScored;
    private Boolean isOwnGoal;

    public Integer getGoalId() { return goalId; }
    public void setGoalId(Integer goalId) { this.goalId = goalId; }

    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }

    public Integer getMinuteScored() { return minuteScored; }
    public void setMinuteScored(Integer minuteScored) { this.minuteScored = minuteScored; }

    public Boolean getIsOwnGoal() { return isOwnGoal; }
    public void setIsOwnGoal(Boolean isOwnGoal) { this.isOwnGoal = isOwnGoal; }
}
