package com.football.mybatis.mapper;

import com.football.model.Transfer;
import java.util.List;

public interface TransferMapper {
    List<Transfer> findAll();
    int insert(Transfer e);
}
