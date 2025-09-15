package com.football.service.impl.mybatis;

import com.football.model.Player;
import com.football.mybatis.mapper.PlayerMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.PlayerService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PlayerServiceImpl implements PlayerService {
    @Override
    public int create(Player e) {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            PlayerMapper m = session.getMapper(PlayerMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Player> list() {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            PlayerMapper m = session.getMapper(PlayerMapper.class);
            return m.findAll();
        }
    }
}
