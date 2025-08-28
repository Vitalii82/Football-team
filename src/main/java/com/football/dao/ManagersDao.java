
package com.football.dao;

import com.football.model.Manager;
import java.util.List;

public interface ManagersDao {
    int insert(Manager m);
    List<Manager> findAll();
}
