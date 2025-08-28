package com.football.service;
import com.football.dao.PlayersDao;
import com.football.dao.impl.PlayersDaoJdbc;
import com.football.model.Player;
import java.time.LocalDate;
import java.util.Optional;

public class PlayerService {
    private final PlayersDao players = new PlayersDaoJdbc();
    public int ensurePlayer(String first, String last){
        // naive: always insert; in real code you'd search by unique constraint
        Player p = new Player();
        p.setFirstName(first); p.setLastName(last);
        p.setNationality("Unknown"); p.setDateOfBirth(LocalDate.of(2000,1,1));
        return players.insert(p);
    }
    public Optional<Player> byId(int id){ return players.findById(id); }
}
