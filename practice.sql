-- 10 left joins 

SELECT t.team_name, m.first_name, m.last_name
FROM teams t
LEFT JOIN managers m ON t.manager_id = m.manager_id;

SELECT t.team_name, s.stadium_name, s.capacity
FROM teams t
LEFT JOIN stadiums s ON t.stadium_id = s.stadium_id;

SELECT c.contract_id, p.first_name, p.last_name, c.salary
FROM contracts c
LEFT JOIN players p ON c.player_id = p.player_id;

SELECT st.team_id, s.start_year, s.end_year
FROM season_teams st
LEFT JOIN seasons s ON st.season_id = s.season_id;

SELECT m.match_id, m.match_date, mr.home_score, mr.away_score
FROM matches m
LEFT JOIN match_results mr ON mr.match_id = m.match_id;

SELECT g.match_id, p.first_name, p.last_name, g.minute_scored
FROM goals g
LEFT JOIN players p ON g.player_id = p.player_id;

SELECT tr.transfer_id, p.first_name, p.last_name, tt.team_name AS to_team
FROM transfers tr
LEFT JOIN players p ON tr.player_id = p.player_id
LEFT JOIN teams tt ON tr.to_team_id = tt.team_id;

SELECT ts.session_date, ts.focus_area, t.team_name
FROM training_sessions ts
LEFT JOIN teams t ON ts.team_id = t.team_id;

SELECT p.last_name, AVG(ps.goals_scored) AS avg_goals
FROM player_statistics ps
LEFT JOIN players p ON ps.player_id = p.player_id
GROUP BY p.last_name;

SELECT t.team_name, SUM(c.salary) AS total_wage_bill
FROM teams t
LEFT JOIN contracts c ON c.team_id = t.team_id
GROUP BY t.team_id, t.team_name;

-- 10 right joins

SELECT m.manager_id, m.first_name, m.last_name, t.team_name
FROM teams t
RIGHT JOIN managers m ON t.manager_id = m.manager_id;

SELECT s.stadium_name, s.capacity, t.team_name
FROM teams t
RIGHT JOIN stadiums s ON t.stadium_id = s.stadium_id;

SELECT p.player_id, p.first_name, p.last_name, c.salary
FROM contracts c
RIGHT JOIN players p ON c.player_id = p.player_id;

SELECT s.season_id, s.start_year, s.end_year, st.team_id
FROM season_teams st
RIGHT JOIN seasons s ON st.season_id = s.season_id;

SELECT mr.match_id, m.match_date, mr.home_score, mr.away_score
FROM match_results mr
RIGHT JOIN matches m ON mr.match_id = m.match_id;

SELECT p.player_id, p.first_name, COUNT(g.goal_id) AS goals
FROM goals g
RIGHT JOIN players p ON g.player_id = p.player_id
GROUP BY p.player_id;

SELECT tt.team_name AS to_team, COUNT(tr.transfer_id) AS inbound_transfers
FROM transfers tr
RIGHT JOIN teams tt ON tr.to_team_id = tt.team_id
GROUP BY tt.team_id;

SELECT t.team_name, COUNT(ts.session_id) AS sessions
FROM training_sessions ts
RIGHT JOIN teams t ON ts.team_id = t.team_id
GROUP BY t.team_id;

SELECT p.player_id, p.last_name, MAX(ps.goals_scored) AS best_goals_in_season
FROM player_statistics ps
RIGHT JOIN players p ON ps.player_id = p.player_id
GROUP BY p.player_id, p.last_name;

SELECT s.season_id, s.start_year, COUNT(m.match_id) AS matches_cnt, SUM(mr.home_score + mr.away_score) AS total_goals
FROM matches m
RIGHT JOIN seasons s ON m.season_id = s.season_id
LEFT JOIN match_results mr ON mr.match_id = m.match_id
GROUP BY s.season_id, s.start_year;

-- 10 inner joins

SELECT t.team_name, CONCAT(m.first_name,' ',m.last_name) AS manager
FROM teams t
INNER JOIN managers m ON t.manager_id = m.manager_id;

SELECT t.team_name, s.stadium_name, s.capacity
FROM teams t
INNER JOIN stadiums s ON t.stadium_id = s.stadium_id;

SELECT c.contract_id, p.last_name AS player, t.team_name, c.salary
FROM contracts c
INNER JOIN players p ON p.player_id = c.player_id
INNER JOIN teams t ON t.team_id = c.team_id;

SELECT s.start_year, s.end_year, t.team_name
FROM season_teams st
INNER JOIN seasons s ON s.season_id = st.season_id
INNER JOIN teams t ON t.team_id = st.team_id;

SELECT m.match_id, m.match_date, s.stadium_name, mr.home_score, mr.away_score
FROM matches m
INNER JOIN match_results mr ON mr.match_id = m.match_id
INNER JOIN stadiums s ON s.stadium_id = m.stadium_id;

SELECT g.goal_id, m.match_date, CONCAT(p.first_name,' ',p.last_name) AS scorer, g.minute_scored
FROM goals g
INNER JOIN matches m ON m.match_id = g.match_id
INNER JOIN players p ON p.player_id = g.player_id;

SELECT pz.player_id, pos.position_name
FROM player_positions pz
INNER JOIN positions pos ON pos.position_id = pz.position_id
INNER JOIN players pl ON pl.player_id = pz.player_id;

SELECT pl.last_name, AVG(ps.matches_played) AS avg_matches
FROM player_statistics ps
INNER JOIN players pl ON pl.player_id = ps.player_id
GROUP BY pl.last_name;

SELECT tr.transfer_id, CONCAT(pl.first_name,' ',pl.last_name) AS player,
       tf.team_name AS from_team, tt.team_name AS to_team, tr.transfer_fee
FROM transfers tr
INNER JOIN players pl ON pl.player_id = tr.player_id
LEFT JOIN teams tf ON tf.team_id = tr.from_team_id
LEFT JOIN teams tt ON tt.team_id = tr.to_team_id;

SELECT t.team_name, MIN(ts.session_date) AS first_session, MAX(ts.session_date) AS last_session
FROM training_sessions ts
INNER JOIN teams t ON t.team_id = ts.team_id
GROUP BY t.team_id, t.team_name;

-- 10 outer joins

(SELECT t.team_name, CONCAT(m.first_name,' ',m.last_name) AS manager
 FROM teams t LEFT JOIN managers m ON t.manager_id = m.manager_id)
UNION
(SELECT t.team_name, CONCAT(m.first_name,' ',m.last_name) AS manager
 FROM managers m LEFT JOIN teams t ON t.manager_id = m.manager_id
 WHERE t.team_id IS NULL);

(SELECT t.team_name, s.stadium_name
 FROM teams t LEFT JOIN stadiums s ON t.stadium_id = s.stadium_id)
UNION
(SELECT t.team_name, s.stadium_name
 FROM stadiums s LEFT JOIN teams t ON t.stadium_id = s.stadium_id
 WHERE t.team_id IS NULL);

(SELECT p.player_id, p.last_name, c.team_id
 FROM players p LEFT JOIN contracts c ON p.player_id = c.player_id)
UNION
(SELECT p.player_id, p.last_name, c.team_id
 FROM contracts c LEFT JOIN players p ON p.player_id = c.player_id
 WHERE p.player_id IS NULL);

(SELECT s.season_id, st.team_id
 FROM seasons s LEFT JOIN season_teams st ON s.season_id = st.season_id)
UNION
(SELECT s.season_id, st.team_id
 FROM season_teams st LEFT JOIN seasons s ON s.season_id = st.season_id
 WHERE s.season_id IS NULL);

(SELECT m.match_id, mr.home_score, mr.away_score
 FROM matches m LEFT JOIN match_results mr ON mr.match_id = m.match_id)
UNION
(SELECT m.match_id, mr.home_score, mr.away_score
 FROM match_results mr LEFT JOIN matches m ON mr.match_id = m.match_id
 WHERE m.match_id IS NULL);

(SELECT p.player_id, p.last_name, COUNT(g.goal_id) AS g
 FROM players p LEFT JOIN goals g ON g.player_id = p.player_id
 GROUP BY p.player_id, p.last_name)
UNION
(SELECT p.player_id, p.last_name, COUNT(g.goal_id) AS g
 FROM goals g LEFT JOIN players p ON p.player_id = g.player_id
 WHERE p.player_id IS NULL
 GROUP BY p.player_id, p.last_name);

(SELECT t.team_id, t.team_name, COUNT(tr.transfer_id) inbound
 FROM teams t LEFT JOIN transfers tr ON tr.to_team_id = t.team_id
 GROUP BY t.team_id, t.team_name)
UNION
(SELECT t.team_id, t.team_name, COUNT(tr.transfer_id) inbound
 FROM transfers tr LEFT JOIN teams t ON tr.to_team_id = t.team_id
 WHERE t.team_id IS NULL
 GROUP BY t.team_id, t.team_name);

(SELECT t.team_name, COUNT(ts.session_id) sessions
 FROM teams t LEFT JOIN training_sessions ts ON ts.team_id = t.team_id
 GROUP BY t.team_name)
UNION
(SELECT t.team_name, COUNT(ts.session_id) sessions
 FROM training_sessions ts LEFT JOIN teams t ON t.team_id = ts.team_id
 WHERE t.team_id IS NULL
 GROUP BY t.team_name);

(SELECT p.player_id, p.last_name, SUM(ps.goals_scored) goals
 FROM players p LEFT JOIN player_statistics ps ON ps.player_id = p.player_id
 GROUP BY p.player_id, p.last_name)
UNION
(SELECT p.player_id, p.last_name, SUM(ps.goals_scored) goals
 FROM player_statistics ps LEFT JOIN players p ON p.player_id = ps.player_id
 WHERE p.player_id IS NULL
 GROUP BY p.player_id, p.last_name);

(SELECT s.season_id, COUNT(m.match_id) AS matches_cnt
 FROM seasons s LEFT JOIN matches m ON m.season_id = s.season_id
 GROUP BY s.season_id)
UNION
(SELECT s.season_id, COUNT(m.match_id) AS matches_cnt
 FROM matches m LEFT JOIN seasons s ON s.season_id = m.season_id
 WHERE s.season_id IS NULL
 GROUP BY s.season_id);

-- Big query

SELECT
  m.match_id,
  m.match_date,
  CONCAT(sh.start_year,'/',sh.end_year) AS season,
  s.stadium_name,
  ht.team_name AS home_team,
  CONCAT(hm.first_name,' ',hm.last_name) AS home_manager,
  at.team_name AS away_team,
  CONCAT(am.first_name,' ',am.last_name) AS away_manager,
  mr.home_score, mr.away_score,
  COUNT(g.goal_id) AS goals_in_match,
  SUM(CASE WHEN g.is_own_goal THEN 1 ELSE 0 END) AS own_goals,
  AVG(ps.matches_played) AS avg_matches_for_scorers
FROM matches m
LEFT JOIN match_results mr ON mr.match_id = m.match_id
INNER JOIN seasons sh ON sh.season_id = m.season_id
LEFT JOIN stadiums s ON s.stadium_id = m.stadium_id
LEFT JOIN teams ht ON ht.team_id = m.home_team_id
LEFT JOIN managers hm ON hm.manager_id = ht.manager_id
LEFT JOIN teams at ON at.team_id = m.away_team_id
LEFT JOIN managers am ON am.manager_id = at.manager_id
LEFT JOIN goals g ON g.match_id = m.match_id
LEFT JOIN players p ON p.player_id = g.player_id
LEFT JOIN player_statistics ps ON ps.player_id = p.player_id AND ps.season_id = sh.season_id
GROUP BY m.match_id, m.match_date, sh.start_year, sh.end_year, s.stadium_name,
         ht.team_name, hm.first_name, hm.last_name,
         at.team_name, am.first_name, am.last_name, mr.home_score, mr.away_score;


-- 3 Group by queries 

SELECT t.team_name, SUM(c.salary) AS total_salary
FROM teams t
LEFT JOIN contracts c ON c.team_id = t.team_id
GROUP BY t.team_id, t.team_name;

SELECT p.last_name, COUNT(g.goal_id) AS goals
FROM players p
LEFT JOIN goals g ON g.player_id = p.player_id
GROUP BY p.player_id, p.last_name;

SELECT CONCAT(s.start_year,'/',s.end_year) AS season, COUNT(m.match_id) AS matches_cnt
FROM seasons s
LEFT JOIN matches m ON m.season_id = s.season_id
GROUP BY s.season_id, s.start_year, s.end_year;

-- 3 Having queries

SELECT t.team_name, SUM(c.salary) AS total_salary
FROM teams t
LEFT JOIN contracts c ON c.team_id = t.team_id
GROUP BY t.team_id, t.team_name
HAVING SUM(c.salary) > 2000000;

SELECT p.player_id, p.last_name, MAX(ps.goals_scored) AS best_season_goals
FROM player_statistics ps
INNER JOIN players p ON p.player_id = ps.player_id
GROUP BY p.player_id, p.last_name
HAVING MAX(ps.goals_scored) >= 2;

SELECT t.team_name, COUNT(ts.session_id) AS sessions
FROM teams t
LEFT JOIN training_sessions ts ON ts.team_id = t.team_id
GROUP BY t.team_id, t.team_name
HAVING COUNT(ts.session_id) > 1;
