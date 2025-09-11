package com.football.service;

import com.football.model.Manager;
import java.util.List;

public interface ManagerService {
    int create(Manager e);
    List<Manager> list();
}
