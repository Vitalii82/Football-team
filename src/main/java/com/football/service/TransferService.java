package com.football.service;

import com.football.model.Transfer;
import java.util.List;

public interface TransferService {
    int create(Transfer e);
    List<Transfer> list();
}
