package com.example.football.util;

import com.example.football.model.Manager;
import com.example.football.model.Position;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DomXmlParser {

    public List<Manager> parseManagers(InputStream xml) {
        List<Manager> out = new ArrayList<>();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
            NodeList nodes = doc.getElementsByTagName("manager");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element e = (Element) nodes.item(i);
                Manager m = new Manager();
                m.setFirstName(text(e, "firstName"));
                m.setLastName(text(e, "lastName"));
                m.setNationality(text(e, "nationality"));
                String dob = text(e, "dateOfBirth");
                if (dob != null && !dob.isBlank()) m.setDateOfBirth(LocalDate.parse(dob));
                String exp = text(e, "experienceYears");
                if (exp != null && !exp.isBlank()) m.setExperienceYears(Integer.parseInt(exp));
                out.add(m);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse managers XML", ex);
        }
        return out;
    }

    public List<Position> parsePositions(InputStream xml) {
        List<Position> out = new ArrayList<>();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
            NodeList nodes = doc.getElementsByTagName("position");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element e = (Element) nodes.item(i);
                Position p = new Position();
                p.setPositionName(text(e, "name"));
                out.add(p);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse positions XML", ex);
        }
        return out;
    }

    private static String text(Element parent, String tag) {
        NodeList nl = parent.getElementsByTagName(tag);
        if (nl.getLength() == 0) return null;
        return nl.item(0).getTextContent();
    }
}
