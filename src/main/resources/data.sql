DROP TABLE IF EXISTS team;
CREATE TABLE team (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    league VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

INSERT INTO team (name, league, country) VALUES ('Real Madrid', 'La Liga', 'España');
INSERT INTO team (name, league, country) VALUES ('FC Barcelona', 'La Liga', 'España');
INSERT INTO team (name, league, country) VALUES ('Manchester United', 'Premier League', 'Inglaterra');
INSERT INTO team (name, league, country) VALUES ('Liverpool FC', 'Premier League', 'Inglaterra');
INSERT INTO team (name, league, country) VALUES ('Juventus FC', 'Serie A', 'Italia');
INSERT INTO team (name, league, country) VALUES ('AC Milan', 'Serie A', 'Italia');
INSERT INTO team (name, league, country) VALUES ('Bayern Munich', 'Bundesliga', 'Alemania');
INSERT INTO team (name, league, country) VALUES ('Borussia Dortmund', 'Bundesliga', 'Alemania');
INSERT INTO team (name, league, country) VALUES ('Paris Saint-Germain', 'Ligue 1', 'Francia');
INSERT INTO team (name, league, country) VALUES ('Olympique de Marseille', 'Ligue 1', 'Francia');
INSERT INTO team (name, league, country) VALUES ('FC Porto', 'Primeira Liga', 'Portugal');
INSERT INTO team (name, league, country) VALUES ('Sporting CP', 'Primeira Liga', 'Portugal');
INSERT INTO team (name, league, country) VALUES ('Ajax Amsterdam', 'Eredivisie', 'Países Bajos');
INSERT INTO team (name, league, country) VALUES ('Feyenoord', 'Eredivisie', 'Países Bajos');
INSERT INTO team (name, league, country) VALUES ('Celtic FC', 'Scottish Premiership', 'Escocia');
INSERT INTO team (name, league, country) VALUES ('Rangers FC', 'Scottish Premiership', 'Escocia');
INSERT INTO team (name, league, country) VALUES ('Galatasaray SK', 'Süper Lig', 'Turquía');
INSERT INTO team (name, league, country) VALUES ('Fenerbahçe SK', 'Süper Lig', 'Turquía');
INSERT INTO team (name, league, country) VALUES ('FC Zenit Saint Petersburg', 'Russian Premier League', 'Rusia');
INSERT INTO team (name, league, country) VALUES ('Spartak Moscow', 'Russian Premier League', 'Rusia');
INSERT INTO team (name, league, country) VALUES ('SL Benfica', 'Primeira Liga', 'Portugal');
INSERT INTO team (name, league, country) VALUES ('Besiktas JK', 'Süper Lig', 'Turquía');
INSERT INTO team (name, league, country) VALUES ('SSC Napoli', 'Serie A', 'Italia');
INSERT INTO team (name, league, country) VALUES ('Atlético Madrid', 'La Liga', 'España');

