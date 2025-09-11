package com.football.model;


public class Season {
    private Integer seasonId;
    private Integer startYear;
    private Integer endYear;
    private String name;

    public Integer getSeasonId() { return seasonId; }
    public void setSeasonId(Integer seasonId) { this.seasonId = seasonId; }

    public Integer getStartYear() { return startYear; }
    public void setStartYear(Integer startYear) { this.startYear = startYear; }

    public Integer getEndYear() { return endYear; }
    public void setEndYear(Integer endYear) { this.endYear = endYear; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
