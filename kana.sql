create database dbKana;
use dbKana;

CREATE TABLE if not exists RESPONSABLES (
  NumResponsable INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NomResponsable VARCHAR(50) NOT NULL,
  PseudoResponsable VARCHAR(50) NOT NULL,
  PasswordResponsable VARCHAR(50) NOT NULL,
  TelResponsable VARCHAR(20),
  DroitResponsable VARCHAR(10)
);

DESC RESPONSABLES;


CREATE TABLE if not exists REPAS (
  NumRepas INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NomRepas VARCHAR(50),
  PrixRepas INT,
  QtRepas INT
);

DESC REPAS;


CREATE TABLE if not exists COMMANDER (
  NumCommander INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  TarifCommander INT,
  DateCommander VARCHAR(20),
  NomClientCommander VARCHAR(50),
  NumRepas INT,
  NumResponsable INT,
  constraint FkResCom foreign key (NumResponsable) references RESPONSABLES(NumResponsable),
  constraint FkRepCom foreign key (NumRepas) references REPAS(NumRepas)
);

DESC COMMANDER;

CREATE TABLE if not exists REGLEMENTS (
  NumReglement INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  MontantReglement INT,
  EtatReglement VARCHAR(20),
  NumCommander INT,
  constraint FkComReg foreign key (NumCommander) references COMMANDER(NumCommander)
);

DESC REGLEMENTS;

INSERT INTO RESPONSABLES (NomResponsable, PseudoResponsable, PasswordResponsable, TelResponsable,DroitResponsable) VALUES ("nantenaina", "blackran", "iloveyou", "0343949863","SUPERS"),
("Sitraka", "root", "password",NULL,"USERS");

SELECT * FROM RESPONSABLES;
