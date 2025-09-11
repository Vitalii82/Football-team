package com.football.service;

import com.football.model.Player;
import java.util.List;

public interface PlayerService {
    int create(Player e);
    List<Player> list();
}
