package com.football.navigator;

import com.football.facade.FootballFacade;

public class AppNavigator implements Navigator {
    private final FootballFacade facade;

    public AppNavigator(FootballFacade facade) {
        this.facade = facade;
    }

    @Override
    public void go(Command command) {
        switch (command) {
            case BOOTSTRAP -> facade.bootstrapData();
            case LIST_TEAMS -> facade.printTeams();
            case EXIT -> System.out.println("Bye!");
            default -> throw new IllegalArgumentException("Unknown command: " + command);
        }
    }
}
