package com.football.model;


public class Stadium {
    private Integer stadiumId;
    private String name;
    private String city;
    private Integer capacity;
    private Integer builtYear;

    public Integer getStadiumId() { return stadiumId; }
    public void setStadiumId(Integer stadiumId) { this.stadiumId = stadiumId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Integer getBuiltYear() { return builtYear; }
    public void setBuiltYear(Integer builtYear) { this.builtYear = builtYear; }
}
