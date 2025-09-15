package com.football.factory;

import java.io.InputStream;
import java.util.Properties;

import com.football.service.ContractService;
import com.football.service.GoalService;
import com.football.service.ManagerService;
import com.football.service.MatchResultsService;
import com.football.service.MatchService;
import com.football.service.PlayerPositionsService;
import com.football.service.PlayerService;
import com.football.service.PlayerStatisticsService;
import com.football.service.PositionService;
import com.football.service.SeasonService;
import com.football.service.SeasonTeamsService;
import com.football.service.StadiumService;
import com.football.service.TeamService;
import com.football.service.TrainingSessionService;
import com.football.service.TransferService;


public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    public static ServiceFactory getInstance() { return INSTANCE; }

    private final String impl;

    private ServiceFactory() {
        String tmp = "mybatis";
        try (InputStream in = Thread.currentThread()
                                    .getContextClassLoader()
                                    .getResourceAsStream("db.properties")) {
            if (in != null) {
                Properties p = new Properties();
                p.load(in);
                tmp = p.getProperty("service.impl", "mybatis").trim();
            }
        } catch (Exception ignored) {}
        impl = tmp.toLowerCase();
    }

    @SuppressWarnings("unchecked")
    private <T> T build(String jdbcClass, String mybatisClass) {
        String fqn = "jdbc".equals(impl) ? jdbcClass : mybatisClass;
        try {
            return (T) Class.forName(fqn).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create service: " + fqn, e);
        }
    }

    public ManagerService managerService() {
        return build("com.football.service.impl.jdbc.ManagerServiceImpl",
                     "com.football.service.impl.mybatis.ManagerServiceImpl");
    }
    public StadiumService stadiumService() {
        return build("com.football.service.impl.jdbc.StadiumServiceImpl",
                     "com.football.service.impl.mybatis.StadiumServiceImpl");
    }
    public TeamService teamService() {
        return build("com.football.service.impl.jdbc.TeamServiceImpl",
                     "com.football.service.impl.mybatis.TeamServiceImpl");
    }
    public PlayerService playerService() {
        return build("com.football.service.impl.jdbc.PlayerServiceImpl",
                     "com.football.service.impl.mybatis.PlayerServiceImpl");
    }
    public PositionService positionService() {
        return build("com.football.service.impl.jdbc.PositionServiceImpl",
                     "com.football.service.impl.mybatis.PositionServiceImpl");
    }
    public PlayerPositionsService playerPositionsService() {
        return build("com.football.service.impl.jdbc.PlayerPositionsServiceImpl",
                     "com.football.service.impl.mybatis.PlayerPositionsServiceImpl");
    }
    public ContractService contractService() {
        return build("com.football.service.impl.jdbc.ContractServiceImpl",
                     "com.football.service.impl.mybatis.ContractServiceImpl");
    }
    public SeasonService seasonService() {
        return build("com.football.service.impl.jdbc.SeasonServiceImpl",
                     "com.football.service.impl.mybatis.SeasonServiceImpl");
    }
    public SeasonTeamsService seasonTeamsService() {
        return build("com.football.service.impl.jdbc.SeasonTeamsServiceImpl",
                     "com.football.service.impl.mybatis.SeasonTeamsServiceImpl");
    }
    public MatchService matchService() {
        return build("com.football.service.impl.jdbc.MatchServiceImpl",
                     "com.football.service.impl.mybatis.MatchServiceImpl");
    }
    public MatchResultsService matchResultsService() {
        return build("com.football.service.impl.jdbc.MatchResultsServiceImpl",
                     "com.football.service.impl.mybatis.MatchResultsServiceImpl");
    }
    public GoalService goalService() {
        return build("com.football.service.impl.jdbc.GoalServiceImpl",
                     "com.football.service.impl.mybatis.GoalServiceImpl");
    }
    public TransferService transferService() {
        return build("com.football.service.impl.jdbc.TransferServiceImpl",
                     "com.football.service.impl.mybatis.TransferServiceImpl");
    }
    public TrainingSessionService trainingSessionService() {
        return build("com.football.service.impl.jdbc.TrainingSessionServiceImpl",
                     "com.football.service.impl.mybatis.TrainingSessionServiceImpl");
    }
    public PlayerStatisticsService playerStatisticsService() {
        return build("com.football.service.impl.jdbc.PlayerStatisticsServiceImpl",
                     "com.football.service.impl.mybatis.PlayerStatisticsServiceImpl");
    }
}
