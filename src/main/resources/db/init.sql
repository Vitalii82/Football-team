DROP DATABASE IF EXISTS Football_matches;
CREATE DATABASE football_sim CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE football_sim;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE managers (
  manager_id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name  VARCHAR(50) NOT NULL,
  nationality VARCHAR(50),
  date_of_birth DATE,
  experience_years INT DEFAULT 0
) ENGINE=InnoDB;

CREATE TABLE stadiums (
  stadium_id INT PRIMARY KEY AUTO_INCREMENT,
  stadium_name VARCHAR(100) NOT NULL,
  capacity INT,
  city VARCHAR(100),
  country VARCHAR(100)
) ENGINE=InnoDB;

CREATE TABLE teams (
  team_id INT PRIMARY KEY AUTO_INCREMENT,
  team_name VARCHAR(100) NOT NULL UNIQUE,
  founded_year YEAR,
  stadium_id INT,
  manager_id INT UNIQUE,
  CONSTRAINT fk_team_stadium
    FOREIGN KEY (stadium_id) REFERENCES stadiums(stadium_id)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_team_manager
    FOREIGN KEY (manager_id) REFERENCES managers(manager_id)
    ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE players (
  player_id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name  VARCHAR(50) NOT NULL,
  nationality VARCHAR(50),
  date_of_birth DATE,
  height_cm INT,
  weight_kg INT
) ENGINE=InnoDB;

CREATE TABLE positions (
  position_id INT PRIMARY KEY AUTO_INCREMENT,
  position_name VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE player_positions (
  player_id INT NOT NULL,
  position_id INT NOT NULL,
  PRIMARY KEY (player_id, position_id),
  CONSTRAINT fk_pp_player
    FOREIGN KEY (player_id) REFERENCES players(player_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_pp_position
    FOREIGN KEY (position_id) REFERENCES positions(position_id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE contracts (
  contract_id INT PRIMARY KEY AUTO_INCREMENT,
  player_id INT NOT NULL,
  team_id INT NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE,
  salary DECIMAL(12,2) DEFAULT 0,
  CONSTRAINT fk_contract_player
    FOREIGN KEY (player_id) REFERENCES players(player_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_contract_team
    FOREIGN KEY (team_id) REFERENCES teams(team_id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE seasons (
  season_id INT PRIMARY KEY AUTO_INCREMENT,
  start_year YEAR NOT NULL,
  end_year   YEAR NOT NULL
) ENGINE=InnoDB;

CREATE TABLE season_teams (
  season_id INT NOT NULL,
  team_id   INT NOT NULL,
  PRIMARY KEY (season_id, team_id),
  CONSTRAINT fk_st_season
    FOREIGN KEY (season_id) REFERENCES seasons(season_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_st_team
    FOREIGN KEY (team_id) REFERENCES teams(team_id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE matches (
  match_id INT PRIMARY KEY AUTO_INCREMENT,
  season_id INT NOT NULL,
  match_date DATE NOT NULL,
  home_team_id INT NOT NULL,
  away_team_id INT NOT NULL,
  stadium_id INT,
  CONSTRAINT fk_match_season
    FOREIGN KEY (season_id) REFERENCES seasons(season_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_match_home_team
    FOREIGN KEY (home_team_id) REFERENCES teams(team_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_match_away_team
    FOREIGN KEY (away_team_id) REFERENCES teams(team_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_match_stadium
    FOREIGN KEY (stadium_id) REFERENCES stadiums(stadium_id)
    ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE match_results (
  match_id INT PRIMARY KEY,
  home_score INT NOT NULL DEFAULT 0,
  away_score INT NOT NULL DEFAULT 0,
  CONSTRAINT fk_mr_match
    FOREIGN KEY (match_id) REFERENCES matches(match_id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE goals (
  goal_id INT PRIMARY KEY AUTO_INCREMENT,
  match_id INT NOT NULL,
  player_id INT NOT NULL,
  minute_scored INT NOT NULL,
  is_own_goal BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT fk_goal_match
    FOREIGN KEY (match_id) REFERENCES matches(match_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_goal_player
    FOREIGN KEY (player_id) REFERENCES players(player_id)
    ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE transfers (
  transfer_id INT PRIMARY KEY AUTO_INCREMENT,
  player_id INT NOT NULL,
  from_team_id INT,
  to_team_id INT,
  transfer_fee DECIMAL(14,2) DEFAULT 0,
  transfer_date DATE NOT NULL,
  CONSTRAINT fk_tr_player
    FOREIGN KEY (player_id) REFERENCES players(player_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_tr_from_team
    FOREIGN KEY (from_team_id) REFERENCES teams(team_id)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_tr_to_team
    FOREIGN KEY (to_team_id) REFERENCES teams(team_id)
    ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE training_sessions (
  session_id INT PRIMARY KEY AUTO_INCREMENT,
  team_id INT NOT NULL,
  session_date DATE NOT NULL,
  focus_area VARCHAR(100),
  CONSTRAINT fk_ts_team
    FOREIGN KEY (team_id) REFERENCES teams(team_id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE player_statistics (
  player_id INT NOT NULL,
  season_id INT NOT NULL,
  matches_played INT DEFAULT 0,
  goals_scored INT DEFAULT 0,
  assists INT DEFAULT 0,
  yellow_cards INT DEFAULT 0,
  red_cards INT DEFAULT 0,
  PRIMARY KEY (player_id, season_id),
  CONSTRAINT fk_ps_player
    FOREIGN KEY (player_id) REFERENCES players(player_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_ps_season
    FOREIGN KEY (season_id) REFERENCES seasons(season_id)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO managers (first_name, last_name, nationality, date_of_birth, experience_years) VALUES
('Alex','Miller','England','1975-04-12',15),
('Diego','Santos','Brazil','1972-09-21',18),
('Luca','Bianchi','Italy','1978-02-10',12),
('Oleh','Kovalenko','Ukraine','1980-06-18',10),
('Hans','Meyer','Germany','1969-01-03',22);

INSERT INTO stadiums (stadium_name, capacity, city, country) VALUES
('River Park',42000,'London','England'),
('Mar Azul',50000,'Rio de Janeiro','Brazil'),
('Stadio Centro',38000,'Milan','Italy'),
('Dnipro Arena',32000,'Dnipro','Ukraine'),
('Berg Stadion',45000,'Munich','Germany');

INSERT INTO teams (team_name, founded_year, stadium_id, manager_id) VALUES
('River FC',1905,1,1),
('Blue Sea',1920,2,2),
('Centro AC',1910,3,3),
('Dnipro Stars',1950,4,4),
('Mountain SV',1899,5,5);

INSERT INTO players (first_name,last_name,nationality,date_of_birth,height_cm,weight_kg) VALUES
('John','Smith','England','1996-01-20',185,78),
('Marco','Rossi','Italy','1994-05-11',182,76),
('Pedro','Alves','Brazil','1998-03-04',177,72),
('Taras','Shevchuk','Ukraine','2000-07-22',180,75),
('Lukas','Weber','Germany','1995-11-30',188,81),
('Igor','Bondar','Ukraine','1999-10-02',176,70),
('Felipe','Costa','Brazil','1997-12-15',181,74),
('Giorgio','Conti','Italy','2001-09-07',179,71),
('Maksym','Koval','Ukraine','1993-08-13',190,83),
('Oliver','King','England','2002-04-27',184,77),
('Jonas','Keller','Germany','1997-02-18',186,79),
('Rafael','Silva','Brazil','1996-06-25',175,69);

INSERT INTO positions (position_name) VALUES ('GK'),('DF'),('MF'),('FW');

INSERT INTO player_positions VALUES
(1,4),(2,2),(3,3),(4,3),(5,2),(6,3),(7,4),(8,2),(9,1),(10,4),(11,2),(12,3);

INSERT INTO contracts (player_id, team_id, start_date, end_date, salary) VALUES
(1,1,'2024-07-01',NULL,1200000.00),
(2,3,'2023-07-01',NULL,900000.00),
(3,2,'2024-07-01',NULL,800000.00),
(4,4,'2024-07-01',NULL,650000.00),
(5,5,'2023-07-01',NULL,1000000.00),
(6,4,'2023-07-01','2025-06-30',500000.00),
(7,2,'2023-07-01',NULL,1100000.00),
(8,3,'2024-07-01',NULL,700000.00),
(9,5,'2023-07-01',NULL,1300000.00),
(10,1,'2024-07-01',NULL,600000.00),
(11,5,'2024-07-01',NULL,950000.00),
(12,2,'2023-07-01',NULL,750000.00);

INSERT INTO seasons (start_year, end_year) VALUES (2023,2024),(2024,2025);

INSERT INTO season_teams VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),
(2,1),(2,2),(2,3),(2,4),(2,5);

INSERT INTO matches (season_id, match_date, home_team_id, away_team_id, stadium_id) VALUES
(1,'2024-03-10',1,2,1),
(1,'2024-03-17',3,4,3),
(1,'2024-04-05',5,1,5),
(2,'2024-09-12',2,3,2),
(2,'2024-10-01',4,5,4);

INSERT INTO match_results (match_id, home_score, away_score) VALUES
(1,2,1),
(2,0,0),
(3,1,3),
(4,2,2),
(5,4,1);

INSERT INTO goals (match_id, player_id, minute_scored, is_own_goal) VALUES
(1,1,15,FALSE),(1,7,47,FALSE),(1,2,71,FALSE),
(2,6,55,TRUE),
(3,10,22,FALSE),(3,9,44,FALSE),(3,1,70,FALSE),(3,5,83,FALSE),
(4,3,10,FALSE),(4,2,60,FALSE),(4,8,77,FALSE),(4,7,85,FALSE),
(5,4,5,FALSE),(5,11,33,FALSE),(5,9,72,FALSE),(5,5,81,FALSE),(5,4,88,FALSE);

INSERT INTO transfers (player_id, from_team_id, to_team_id, transfer_fee, transfer_date) VALUES
(6,4,1,2000000.00,'2025-06-15'),
(1,1,5,5000000.00,'2024-06-01'),
(3,2,2,0.00,'2024-07-01'); 

INSERT INTO training_sessions (team_id, session_date, focus_area) VALUES
(1,'2024-08-01','Finishing'),
(1,'2024-08-05','Pressing'),
(2,'2024-08-02','Defending'),
(3,'2024-08-03','Ball Control'),
(4,'2024-08-04','Set Pieces'),
(5,'2024-08-06','Conditioning');

INSERT INTO player_statistics (player_id, season_id, matches_played, goals_scored, assists, yellow_cards, red_cards) VALUES
(1,1,24,12,5,3,0),
(2,1,22,1,3,4,0),
(3,1,21,6,7,5,0),
(4,1,25,5,4,6,1),
(5,1,26,3,2,2,0),
(1,2,6,3,1,0,0),
(2,2,6,0,1,1,0),
(3,2,6,2,2,0,0),
(4,2,6,1,1,1,0),
(5,2,6,2,0,0,0);

