package com.example.football.service;

import com.example.football.dao.*;

public class TeamService {{
    public void demo() {{
        System.out.println("TeamService works");
    }}

    // add methods that use DAOs here
    public int createTeam(com.example.football.model.Team t) throws Exception {{
        // placeholder; real logic should call TeamsDao
        return (int)(Math.random() * 1000);
    }}
}}
