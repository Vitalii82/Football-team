package com.football.mybatis.mapper;

import com.football.model.Matche;
import java.util.List;

public interface MatcheMapper {
    List<Matche> findAll();
    int insert(Matche e);
}
