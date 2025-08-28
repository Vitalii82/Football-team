
package com.football.dao;

import com.football.model.Position;
import java.util.List;

public interface PositionsDao {
    int insert(Position p);
    List<Position> findAll();
}
