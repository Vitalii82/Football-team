package com.football.model;


public class Goal {
    private Integer goalId;
    private Integer matchId;
    private Integer playerId;
    private Integer minute;
    private Boolean isOwnGoal;
    private Boolean isPenalty;

    public Integer getGoalId() { return goalId; }
    public void setGoalId(Integer goalId) { this.goalId = goalId; }

    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }

    public Integer getMinute() { return minute; }
    public void setMinute(Integer minute) { this.minute = minute; }

    public Boolean getIsOwnGoal() { return isOwnGoal; }
    public void setIsOwnGoal(Boolean isOwnGoal) { this.isOwnGoal = isOwnGoal; }

    public Boolean getIsPenalty() { return isPenalty; }
    public void setIsPenalty(Boolean isPenalty) { this.isPenalty = isPenalty; }
}
