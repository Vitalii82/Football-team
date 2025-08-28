
package com.football.xml.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "positions")
public class PositionsXml {
    private java.util.List<PositionXml> positions;

    @XmlElement(name = "position")
    public java.util.List<PositionXml> getPositions() { return positions; }
    public void setPositions(java.util.List<PositionXml> positions) { this.positions = positions; }
}
