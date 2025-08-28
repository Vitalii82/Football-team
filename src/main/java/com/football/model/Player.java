package com.football.model;
import java.time.LocalDate;
public class Player {
    private Integer playerId;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private Integer heightCm;
    private Integer weightKg;
    public Integer getPlayerId(){return playerId;}
    public void setPlayerId(Integer v){this.playerId=v;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String v){this.firstName=v;}
    public String getLastName(){return lastName;}
    public void setLastName(String v){this.lastName=v;}
    public String getNationality(){return nationality;}
    public void setNationality(String v){this.nationality=v;}
    public LocalDate getDateOfBirth(){return dateOfBirth;}
    public void setDateOfBirth(LocalDate v){this.dateOfBirth=v;}
    public Integer getHeightCm(){return heightCm;}
    public void setHeightCm(Integer v){this.heightCm=v;}
    public Integer getWeightKg(){return weightKg;}
    public void setWeightKg(Integer v){this.weightKg=v;}
}
