package com.football.model;


public class Stadiums {
    private Integer stadiumId;
    private String stadiumName;
    private Integer capacity;
    private String city;
    private String country;

    public Integer getStadiumId() { return stadiumId; }
    public void setStadiumId(Integer stadiumId) { this.stadiumId = stadiumId; }

    public String getStadiumName() { return stadiumName; }
    public void setStadiumName(String stadiumName) { this.stadiumName = stadiumName; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
