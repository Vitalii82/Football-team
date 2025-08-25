package com.football.service.impl;

import com.football.dao.ManagerDao;
import com.football.dao.PositionDao;
import com.football.dao.impl.ManagerDaoJdbc;
import com.football.dao.impl.PositionDaoJdbc;
import com.football.model.Manager;
import com.football.model.Position;
import com.football.service.XmlImportService;
import com.football.util.DomXmlParser;

import java.io.InputStream;
import java.util.List;

public class XmlImportServiceImpl implements XmlImportService {
    private final DomXmlParser parser = new DomXmlParser();
    private final ManagerDao managerDao = new ManagerDaoJdbc();
    private final PositionDao positionDao = new PositionDaoJdbc();

    @Override
    public int importManagers(String resourcePath) {
        try (InputStream in = res(resourcePath)) {
            List<Manager> list = parser.parseManagers(in);
            int n = 0; for (Manager m : list) { managerDao.insert(m); n++; }
            return n;
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    @Override
    public int importPositions(String resourcePath) {
        try (InputStream in = res(resourcePath)) {
            List<Position> list = parser.parsePositions(in);
            int n = 0; for (Position p : list) { positionDao.insert(p); n++; }
            return n;
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private InputStream res(String p) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(p);
        if (in == null) throw new IllegalArgumentException("Resource not found: " + p);
        return in;
    }
}
