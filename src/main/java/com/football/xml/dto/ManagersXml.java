
package com.football.xml.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "managers")
public class ManagersXml {
    private List<ManagerXml> managers;

    @XmlElement(name = "manager")
    public List<ManagerXml> getManagers() { return managers; }
    public void setManagers(List<ManagerXml> managers) { this.managers = managers; }
}
