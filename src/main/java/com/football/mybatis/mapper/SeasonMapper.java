package com.football.mybatis.mapper;

import com.football.model.Season;
import java.util.List;

public interface SeasonMapper {
    List<Season> findAll();
    int insert(Season e);
}
