
package com.football.service;

import com.football.dao.ManagersDao;
import com.football.dao.PositionsDao;
import com.football.dao.impl.ManagersDaoJdbc;
import com.football.dao.impl.PositionsDaoJdbc;
import com.football.model.Manager;
import com.football.model.Position;
import com.football.xml.dto.ManagerXml;
import com.football.xml.dto.ManagersXml;
import com.football.xml.dto.PositionXml;
import com.football.xml.dto.PositionsXml;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.time.LocalDate;

public class XmlJaxbService {
    private final ManagersDao managersDao = new ManagersDaoJdbc();
    private final PositionsDao positionsDao = new PositionsDaoJdbc();

    public int importManagers(String xmlPath, String xsdPath) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(ManagersXml.class);
            Unmarshaller um = ctx.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(xsdPath));
            um.setSchema(schema);

            ManagersXml root = (ManagersXml) um.unmarshal(new File(xmlPath));
            int count = 0;
            for (ManagerXml mx : root.getManagers()) {
                Manager m = new Manager();
                m.setFirstName(mx.getFirstName());
                m.setLastName(mx.getLastName());
                m.setNationality(mx.getNationality());
                if (mx.getDateOfBirth()!=null && !mx.getDateOfBirth().isBlank()) {
                    m.setDateOfBirth(LocalDate.parse(mx.getDateOfBirth()));
                }
                m.setExperienceYears(mx.getExperienceYears());
                managersDao.insert(m);
                count++;
            }
            return count;
        } catch (Exception e) {
            throw new RuntimeException("JAXB import (managers) failed", e);
        }
    }

    public int importPositions(String xmlPath, String xsdPath) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(PositionsXml.class);
            Unmarshaller um = ctx.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(xsdPath));
            um.setSchema(schema);

            PositionsXml root = (PositionsXml) um.unmarshal(new File(xmlPath));
            int count = 0;
            for (PositionXml px : root.getPositions()) {
                Position p = new Position();
                p.setPositionName(px.getName());
                positionsDao.insert(p);
                count++;
            }
            return count;
        } catch (Exception e) {
            throw new RuntimeException("JAXB import (positions) failed", e);
        }
    }
}
