package com.football.service;
import com.football.dao.MatchResultsDao;
import com.football.dao.MatchesDao;
import com.football.dao.impl.MatchResultsDaoJdbc;
import com.football.dao.impl.MatchesDaoJdbc;
import com.football.model.Match;
import com.football.model.MatchResult;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

public class MatchService {
    private final MatchesDao matches = new MatchesDaoJdbc();
    private final MatchResultsDao results = new MatchResultsDaoJdbc();
    private final Random rnd = new Random();

    public int scheduleAndPlay(int seasonId, int homeTeamId, int awayTeamId, LocalDate date){
        Optional<Match> ex = matches.findExisting(seasonId, homeTeamId, awayTeamId, date);
        int matchId;
        if (ex.isPresent()) matchId = ex.get().getMatchId();
        else { Match m = new Match(); m.setSeasonId(seasonId); m.setHomeTeamId(homeTeamId); m.setAwayTeamId(awayTeamId); m.setMatchDate(date); matchId = matches.insert(m); }
        // naive random result (you can replace with player-based logic)
        int home = rnd.nextInt(4); int away = rnd.nextInt(4);
        MatchResult r = new MatchResult(); r.setMatchId(matchId); r.setHomeScore(home); r.setAwayScore(away);
        results.insert(r);
        return matchId;
    }
}
