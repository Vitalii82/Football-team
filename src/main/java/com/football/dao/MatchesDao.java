package com.football.dao;
import com.football.model.Match;
import java.util.Optional;
public interface MatchesDao {
    int insert(Match m);
    Optional<Match> findExisting(Integer seasonId, Integer homeTeamId, Integer awayTeamId, java.time.LocalDate date);
}
