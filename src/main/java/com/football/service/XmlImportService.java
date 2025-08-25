package com.football.service;

import com.football.dao.ManagersDao;
import com.football.dao.PositionsDao;
import com.football.model.Manager;
import com.football.model.Position;
import com.football.util.DomXmlParser;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class XmlImportService {
    private final DomXmlParser parser = new DomXmlParser();
    private final ManagersDao managersDao = new ManagersDao();
    private final PositionsDao positionsDao = new PositionsDao();

    public int importManagersFromResource(String resourcePath) throws SQLException {
        try (InputStream in = getResource(resourcePath)) {
            List<Manager> managers = parser.parseManagers(in);
            int count = 0;
            for (Manager m : managers) {
                managersDao.insert(m);
                count++;
            }
            return count;
        } catch (Exception e) {
            throw new SQLException("Failed importing managers from " + resourcePath, e);
        }
    }

    public int importPositionsFromResource(String resourcePath) throws SQLException {
        try (InputStream in = getResource(resourcePath)) {
            List<Position> positions = parser.parsePositions(in);
            int count = 0;
            for (Position p : positions) {
                positionsDao.insert(p);
                count++;
            }
            return count;
        } catch (Exception e) {
            throw new SQLException("Failed importing positions from " + resourcePath, e);
        }
    }

    private InputStream getResource(String path) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (in == null) throw new IllegalArgumentException("Resource not found: " + path);
        return in;
    }
}
