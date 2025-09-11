package com.football.service;

import com.football.model.Stadium;
import java.util.List;

public interface StadiumService {
    int create(Stadium e);
    List<Stadium> list();
}
