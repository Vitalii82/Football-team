package com.football.service;

public class DbProbeService {
    public void printCounts() {
        try (var c = com.football.config.ConnectionManager.getConnection()) {
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM managers")) { rs.next(); System.out.println("managers: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM stadiums")) { rs.next(); System.out.println("stadiums: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM teams")) { rs.next(); System.out.println("teams: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM players")) { rs.next(); System.out.println("players: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM positions")) { rs.next(); System.out.println("positions: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM player_positions")) { rs.next(); System.out.println("player_positions: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM contracts")) { rs.next(); System.out.println("contracts: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM seasons")) { rs.next(); System.out.println("seasons: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM season_teams")) { rs.next(); System.out.println("season_teams: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM matches")) { rs.next(); System.out.println("matches: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM match_results")) { rs.next(); System.out.println("match_results: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM goals")) { rs.next(); System.out.println("goals: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM transfers")) { rs.next(); System.out.println("transfers: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM training_sessions")) { rs.next(); System.out.println("training_sessions: " + rs.getInt(1)); }
            try (var st = c.createStatement(); var rs = st.executeQuery("SELECT COUNT(*) FROM player_statistics")) { rs.next(); System.out.println("player_statistics: " + rs.getInt(1)); }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
