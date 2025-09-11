package com.football.service;

import com.football.model.Matche;
import java.util.List;

public interface MatcheService {
    int create(Matche e);
    List<Matche> list();
}
