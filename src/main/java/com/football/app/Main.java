package com.football.app;

import com.football.service.*;
import java.time.LocalDate;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try {
            // 1) Ensure base data
            TeamService teamSvc = new TeamService();
            int teamA = teamSvc.ensureTeam("Lions FC", 2010);
            int teamB = teamSvc.ensureTeam("Eagles FC", 2012);

            PlayerService playerSvc = new PlayerService();
            int playerId = playerSvc.ensurePlayer("John", "Doe");

            SeasonService seasonSvc = new SeasonService();
            int currentYear = LocalDate.now().getYear();
            int seasonId = seasonSvc.ensureSeason(2025, 2026);

            // 2) Transfer flow: buy player into teamB
            TransferService transferSvc = new TransferService();
            transferSvc.buyPlayer(playerId, teamB, new BigDecimal("1000000"));

            // 3) Match flow: schedule and play a match
            MatchService matchSvc = new MatchService();
            matchSvc.scheduleAndPlay(seasonId, teamA, teamB, LocalDate.now().plusDays(1));

            // 4) Print teams list (proof)
            System.out.println("Teams in DB:");
            teamSvc.list().forEach(t -> System.out.println(" - " + t.getTeamName()));

            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
