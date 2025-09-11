package com.football.service;

import com.football.model.Season;
import java.util.List;

public interface SeasonService {
    int create(Season e);
    List<Season> list();
}
