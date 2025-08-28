package com.football.dao;
import com.football.model.Season;
import java.util.Optional;
public interface SeasonsDao {
    int insert(Season s);
    Optional<Season> findByYears(int startYear, int endYear);
}
