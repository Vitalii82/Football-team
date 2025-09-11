package com.football.service;

import com.football.model.MatchResults;
import java.util.List;

public interface MatchResultsService {
    int create(MatchResults e);
    List<MatchResults> list();
}
