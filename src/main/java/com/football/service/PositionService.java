package com.football.service;

import com.football.dao.impl.PositionDaoJdbc;
import com.football.model.Position;
import java.util.List;

public class PositionService {
    private final PositionDaoJdbc dao = new PositionDaoJdbc();
    public int create(String name){ Position p=new Position(); p.setPositionName(name); return dao.insert(p); }
    public List<Position> list(){ return dao.findAll(); }
}
