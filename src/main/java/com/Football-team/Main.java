package com.example.football;

import com.example.football.model.Team;
import com.example.football.service.*;

public class Main {
    public static void main(String[] args) {
        try {
            TeamService teamService = new TeamService();
            PlayerService playerService = new PlayerService();
            MatchService matchService = new MatchService();
            TransferService transferService = new TransferService();
            StatsService statsService = new StatsService();

            Team t = new Team();
            t.setTeamName("New Horizon FC");
            t.setFoundedYear(2024);
            int teamId = teamService.createTeam(t);
            System.out.println("Created team id: " + teamId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
