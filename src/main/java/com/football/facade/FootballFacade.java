package com.football.facade;

import com.football.factory.ServiceFactory;
import com.football.model.Team;
import com.football.service.ManagerService;
import com.football.service.PositionService;
import com.football.service.TeamService;

import java.time.LocalDate;
import java.util.List;

public class FootballFacade {
    private final TeamService teamService;
    private final ManagerService managerService;
    private final PositionService positionService;

    public FootballFacade() {
        var f = ServiceFactory.getInstance();
        this.teamService = f.teamService();
        this.managerService = f.managerService();
        this.positionService = f.positionService();
    }

    public void bootstrapData() {
        try { positionService.create("Forward"); } catch (Exception ignored) {}
        try { managerService.create("Alex", "Ivanov", "Ukraine", LocalDate.of(1980, 5, 1), 12); } catch (Exception ignored) {}

        Team t = new Team();
        t.setTeamName("Final Patterns FC");
        t.setFoundedYear(2025);
        t.setStadiumId(1);
        t.setManagerId(1);
        try { teamService.create(t); } catch (Exception ignored) {}
    }

    public void printTeams() {
        List<Team> teams = teamService.list();
        System.out.println("Teams: " + teams.size());
        for (Team t : teams) {
            System.out.println(" - " + t.getTeamId() + ": " + t.getTeamName());
        }
    }
}
