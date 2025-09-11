package com.football.mybatis.mapper;

import com.football.model.MatchResults;
import java.util.List;

public interface MatchResultsMapper {
    List<MatchResults> findAll();
    int insert(MatchResults e);
}
