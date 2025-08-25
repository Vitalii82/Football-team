package com.football.service;

import com.football.dao.*;

public class StatsService {{
    public void demo() {{
        System.out.println("StatsService works");
    }}

    // add methods that use DAOs here
    public int createTeam(com.example.football.model.Team t) throws Exception {{
        // placeholder; real logic should call TeamsDao
        return (int)(Math.random() * 1000);
    }}
}}
