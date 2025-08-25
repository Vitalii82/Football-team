package com.football.service;

import com.football.dao.impl.ManagerDaoJdbc;
import com.football.model.Manager;
import java.time.LocalDate;
import java.util.List;

public class ManagerService {
    private final ManagerDaoJdbc dao = new ManagerDaoJdbc();
    public int create(String first, String last, String nat, LocalDate dob, Integer exp) {
        Manager m = new Manager();
        m.setFirstName(first); m.setLastName(last);
        m.setNationality(nat); m.setDateOfBirth(dob); m.setExperienceYears(exp);
        return dao.insert(m);
    }
    public List<Manager> list() { return dao.findAll(); }
}
