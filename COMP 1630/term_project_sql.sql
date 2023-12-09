CREATE TABLE Team (
    team_id INT PRIMARY KEY IDENTITY(1,1),
    team_name VARCHAR(50),
    team_color VARCHAR(50)
);

CREATE TABLE Player (
    player_id INT PRIMARY KEY IDENTITY(1,1),
    player_first_name VARCHAR(50),
    player_last_name VARCHAR(50),
    player_age INT,
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES Team(team_id)
);

CREATE TABLE Coach (
    coach_id INT PRIMARY KEY IDENTITY(1,1),
    coach_first_name VARCHAR(50),
    coach_last_name VARCHAR(50),
    home_phone_num VARCHAR(50),
	team_id INT,
    FOREIGN KEY (team_id) REFERENCES Team(team_id)
);

CREATE TABLE Parent (
    parent_id INT PRIMARY KEY IDENTITY(1,1),
    parent_last_name VARCHAR(50),
    parent_first_name VARCHAR(50),
    home_phone_num VARCHAR(50),
    home_street VARCHAR(50),
    home_city VARCHAR(50),
    home_state VARCHAR(50),
    home_zip_code VARCHAR(50)
);

CREATE TABLE Register (
    parent_id INT,
    player_id INT,
    PRIMARY KEY (parent_id, player_id),
    FOREIGN KEY (parent_id) REFERENCES Parent(parent_id),
    FOREIGN KEY (player_id) REFERENCES Player(player_id)
);


INSERT INTO Team(team_name, team_color) VALUES ('BCIT', 'red'), ('UBC', 'blue'), ('SFU', 'green');

INSERT INTO Player(player_first_name, player_last_name, player_age, team_id) VALUES ('Jen', 'Kwon', 30, 1);
INSERT INTO Player(player_first_name, player_last_name, player_age, team_id) VALUES ('Yong', 'Eun', 25, 2);
INSERT INTO Player(player_first_name, player_last_name, player_age, team_id) VALUES ('Tracey', 'Kim', 32, 3);

INSERT INTO Coach(coach_first_name, coach_last_name, home_phone_num, team_id) VALUES ('Kevin', 'Tim', '672-673-1234', 1);
INSERT INTO Coach(coach_first_name, coach_last_name, home_phone_num, team_id) VALUES ('Tim', 'Howan', '778-512-1818', 2);
INSERT INTO Coach(coach_first_name, coach_last_name, home_phone_num, team_id) VALUES ('May', 'Adam', '604-123-7878', 3);

INSERT INTO Parent(parent_last_name, parent_first_name, home_phone_num, home_street, home_city, home_state, home_zip_code) 
VALUES ('Jinny', 'Kwon', '672-673-5678', '323 Governors', 'New Westminster', 'BC', 'V3L 5S6');
INSERT INTO Parent(parent_last_name, parent_first_name, home_phone_num, home_street, home_city, home_state, home_zip_code) 
VALUES ('Eunice', 'Eun', '672-123-4567', '523 Governors', 'Coquitlam', 'BC', 'V3J 7W8');
INSERT INTO Parent(parent_last_name, parent_first_name, home_phone_num, home_street, home_city, home_state, home_zip_code) 
VALUES ('Soo', 'Kim', '778-123-7894', '450 Willingdon', 'Burnaby', 'BC', 'V3M 1S3');

INSERT INTO Register(parent_id, player_id) VALUES (1,1);
INSERT INTO Register(parent_id, player_id) VALUES (2,2);
INSERT INTO Register(parent_id, player_id) VALUES (3,3);

SELECT Team.team_name, Coach.coach_first_name, Coach.coach_last_name
FROM Team
INNER JOIN Coach ON Team.team_id = Coach.team_id;

SELECT Team.team_name, Player.player_first_name, Player.player_last_name
FROM Team
INNER JOIN Player ON Team.team_id = Player.team_id;


SELECT Player.player_first_name, Player.player_last_name, Parent.parent_first_name, Parent.parent_last_name
FROM Player
LEFT JOIN Register ON Player.player_id = Register.player_id
LEFT JOIN Parent ON Register.parent_id = Parent.parent_id;


SELECT Team.team_name, COUNT(Player.player_id) AS player_count
FROM Team
LEFT JOIN Player ON Team.team_id = Player.team_id
GROUP BY Team.team_name;

SELECT Team.team_name, Player.player_first_name, Player.player_last_name, Parent.parent_first_name, Parent.parent_last_name
FROM Team
LEFT JOIN Player ON Team.team_id = Player.team_id
LEFT JOIN Register ON Player.player_id = Register.player_id
LEFT JOIN Parent ON Register.parent_id = Parent.parent_id;

SELECT Team.team_name, Player.player_first_name, Player.player_last_name
FROM Team
LEFT JOIN Player ON Team.team_id = Player.team_id
LEFT JOIN Register ON Player.player_id = Register.player_id
WHERE Team.team_name = 'BCIT';