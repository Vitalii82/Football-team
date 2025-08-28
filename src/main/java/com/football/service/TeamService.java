package com.football.service;
import com.football.dao.TeamsDao;
import com.football.dao.impl.TeamsDaoJdbc;
import com.football.model.Team;
import java.util.List;
import java.util.Optional;

public class TeamService {
    private final TeamsDao teams = new TeamsDaoJdbc();
    public int ensureTeam(String name, Integer foundedYear){
        Optional<Team> existing = teams.findByName(name);
        if (existing.isPresent()) return existing.get().getTeamId();
        Team t = new Team(); t.setTeamName(name); t.setFoundedYear(foundedYear);
        return teams.insert(t);
    }
    public List<Team> list(){ return teams.findAll(); }
    public Optional<Team> byId(int id){ return teams.findById(id); }
}
