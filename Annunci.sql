DROP DATABASE IF EXISTS  Annunci;
CREATE DATABASE Annunci;
USE Annunci; 

DROP USER IF EXISTS 'vittorio'@'localhost';
CREATE USER 'vittorio'@'localhost' IDENTIFIED BY 'vittorio';
GRANT ALL ON  Annunci.* TO 'vittorio'@'localhost';

DROP TABLE IF EXISTS utente;

CREATE TABLE utente (	
	email  char(100),
	password char(100) not null,
	nome char(30) not null ,
	cognome char(30) not null,
	indirizzo char(100),
	ruolo enum('UTENTE','AMMINISTRATORE','CORRIERE') default 'UTENTE',
    primary key(email)
);


DROP TABLE IF EXISTS categoria;
	CREATE TABLE categoria(
    nome char(100),
	primary key(nome)
);
DROP TABLE IF EXISTS regione;

CREATE TABLE regione (
	nome char(30),
    primary key(nome)
);

DROP TABLE IF EXISTS universita;
CREATE TABLE universita(
    sigla char(100),
    localita char(100),
	primary key(sigla),
    foreign key(localita) references regione (nome) 
    on delete cascade on update cascade
);



DROP TABLE IF EXISTS annuncio;
CREATE TABLE annuncio (
	email_utente char(100) not null,
    nome_categoria char(100),
    valutazione int,
    sigla_uni char(100),
    titolo char(100) not null,
	tipo enum('vendita','cercasi', 'offerta'),
	descrizione text(10000),
    acquisto_online boolean,
    primary key(email_utente, titolo),
    foreign key(email_utente) references utente (email) 
    on delete cascade on update cascade,
    foreign key(nome_categoria) references categoria (nome)
    on delete cascade on update cascade,
    foreign key(sigla_uni) references universita (sigla)
    on delete cascade on update cascade
);

DROP TABLE IF EXISTS immagine;
CREATE TABLE immagine (
	id INT  NOT NULL AUTO_INCREMENT,
    email_utente_immagine char(100) not null ,
    titolo_annuncio char(100) not null ,
	nomeImmagine char(100)  not null,
    immagine mediumblob,
    primary key(id),
    
    foreign key(email_utente_immagine,titolo_annuncio) references annuncio(email_utente,titolo)
    on delete cascade on update cascade
);

DROP TABLE IF EXISTS corriere;
CREATE TABLE corriere (
	agenzia char(30)  not null,
    email_utente char(100),
    password char(100) not null,
    ruolo enum('CORRIERE') default 'CORRIERE',
    primary key (agenzia, email_utente),
	foreign key(email_utente) references utente (email)
    on delete cascade on update cascade
);


DROP TABLE IF EXISTS desidera;
CREATE TABLE desidera (
	email_annuncio char(100) ,
    titolo_annuncio char(100) ,
    mail_utente char (100) ,
    primary key(email_annuncio,titolo_annuncio,mail_utente),
	
    foreign key(email_annuncio, titolo_annuncio) references annuncio(email_utente,titolo)
	on delete cascade on update cascade,
    foreign key(mail_utente) references utente(email)
	on delete cascade on update cascade
   );


DROP TABLE IF EXISTS consegna_in;

CREATE TABLE consegna_in(
	prezzo double,
    agenzia_corriere char(100),
    nome_regione char(100),
    email_utente char(100),
    foreign key(agenzia_corriere, email_utente) references corriere(agenzia,email_utente)
    on delete cascade on update cascade,
    foreign key(nome_regione) references regione(nome)
	on delete cascade on update cascade
);

