package com.football.app;

// Switch these three imports between .jdbc and .mybatis to change implementation
import com.football.service.impl.mybatis.TeamServiceImpl;
import com.football.service.impl.mybatis.ManagerServiceImpl;
import com.football.service.impl.mybatis.PositionServiceImpl;

import com.football.model.Team;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TeamServiceImpl teams = new TeamServiceImpl();
        ManagerServiceImpl managers = new ManagerServiceImpl();
        PositionServiceImpl positions = new PositionServiceImpl();

        // list existing
        List<Team> all = teams.list();
        System.out.println("Teams in DB: " + all.size());

        // create example
        Team t = new Team();
        t.setTeamName("MyBatis United");
        t.setFoundedYear(2025);
        t.setStadiumId(1);
        t.setManagerId(1);
        int rows = teams.create(t);
        System.out.println("Inserted via MyBatis, affected: " + rows);

        // print again
        System.out.println("Teams now: " + teams.list().size());
    }
}
