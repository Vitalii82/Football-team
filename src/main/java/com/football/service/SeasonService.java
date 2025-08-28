package com.football.service;
import com.football.dao.SeasonsDao;
import com.football.dao.impl.SeasonsDaoJdbc;
import com.football.model.Season;
import java.util.Optional;

public class SeasonService {
    private final SeasonsDao seasons = new SeasonsDaoJdbc();
    public int ensureSeason(int startYear, int endYear){
        Optional<Season> ex = seasons.findByYears(startYear, endYear);
        if (ex.isPresent()) return ex.get().getSeasonId();
        Season s = new Season(); s.setStartYear(startYear); s.setEndYear(endYear);
        return seasons.insert(s);
    }
}
