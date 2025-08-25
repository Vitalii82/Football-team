package com.football.app;

import com.football.model.Team;
import com.football.service.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            var probe = new DbProbeService();
            probe.printCounts();

            var mgrs = new ManagerService();
            int mid = mgrs.create("Carlo","Bianchi","Italy", LocalDate.of(1970,3,14), 20);
            System.out.println("Inserted manager id="+mid);

            var poss = new PositionService();
            int pid = poss.create("WB");
            System.out.println("Inserted position id="+pid);

            var teams = new TeamService();
            Team t = new Team();
            t.setTeamName("Academy FC");
            t.setFoundedYear(2020);
            int tid = teams.create(t);
            System.out.println("Inserted team id="+tid);

            teams.list().forEach(x -> System.out.println("Team: " + x.getTeamName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
