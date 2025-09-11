package com.football.service;

import com.football.model.PlayerPositions;
import java.util.List;

public interface PlayerPositionsService {
    int create(PlayerPositions e);
    List<PlayerPositions> list();
}
