package com.football.app;

import com.football.facade.FootballFacade;
import com.football.navigator.AppNavigator;
import com.football.navigator.Command;
import com.football.navigator.Navigator;


public class Main {
    public static void main(String[] args) {
        FootballFacade facade = new FootballFacade();
        Navigator nav = new AppNavigator(facade);

        nav.go(Command.BOOTSTRAP);
        nav.go(Command.SETUP_SEASON_AND_MATCH);
        nav.go(Command.RECORD_RESULT);
        nav.go(Command.TRAINING);
        nav.go(Command.SNAPSHOT);

        System.out.println("Implementation chosen via 'service.impl' in db.properties (jdbc | mybatis).");
        nav.go(Command.EXIT);
    }
}