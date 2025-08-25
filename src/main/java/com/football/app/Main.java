package com.football.app;

import com.football.model.Team;
import com.football.service.TeamService;
import com.football.service.XmlImportService;
import com.football.service.impl.TeamServiceImpl;
import com.football.service.impl.XmlImportServiceImpl;

public class Main {
    public static void main(String[] args) {
        XmlImportService xml = new XmlImportServiceImpl();
        int m = xml.importManagers("data/managers.xml");
        int p = xml.importPositions("data/positions.xml");
        System.out.println("Imported managers=" + m + ", positions=" + p);

        TeamService teams = new TeamServiceImpl();
        Team t = new Team();
        t.setTeamName("New Horizon FC");
        t.setFoundedYear(2024);
        int id = teams.create(t);
        System.out.println("Created team id: " + id);

        teams.list().forEach(tt -> System.out.println(" - " + tt.getTeamName()));
    }
}
