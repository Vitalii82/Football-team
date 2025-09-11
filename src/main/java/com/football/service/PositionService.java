package com.football.service;

import com.football.model.Position;
import java.util.List;

public interface PositionService {
    int create(Position e);
    List<Position> list();
}
