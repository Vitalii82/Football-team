
package com.football.xml.dto;

import jakarta.xml.bind.annotation.XmlElement;

public class ManagerXml {
    private String firstName;
    private String lastName;
    private String nationality;
    private String dateOfBirth; // ISO yyyy-MM-dd
    private Integer experienceYears;

    @XmlElement public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    @XmlElement public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @XmlElement public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    @XmlElement public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    @XmlElement public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
}
