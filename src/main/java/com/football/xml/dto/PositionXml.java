
package com.football.xml.dto;

import jakarta.xml.bind.annotation.XmlElement;

public class PositionXml {
    private String name;

    @XmlElement public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
