-----------------------------------------------------
CREATE TABLE Obra
(
	id 				NUMBER(8) NOT NULL,
	nombre 			VARCHAR2(40) NOT NULL,
	fecha_emision	DATE,
	puntuacion 		NUMBER(3),
	duracion 		NUMBER(6),
	genero			VARCHAR2(100),
	nacionalidad 	VARCHAR2(40),
	capitulos 		NUMBER(4),
	ruta_imagen 	VARCHAR2(100),
	plot			VARCHAR2(2000),
	awards			VARCHAR2(300),
	metascore		NUMBER(3),
	imdb_rating		NUMBER(11),
	imdb_votes		NUMBER(16),
	PRIMARY KEY(id)
);

-----------------------------------------------------
CREATE TABLE Persona
(
	id 					NUMBER(8) NOT NULL,
	nombre 				VARCHAR2(40) NOT NULL,
	fecha_nacimiento	DATE,
	sexo 				VARCHAR2(30),
	nacionalidad 		VARCHAR2(40),
	PRIMARY KEY(id)
);

-----------------------------------------------------
CREATE TABLE Trabaja
(
	id 				NUMBER(8) NOT NULL,
	nombre_persona	NUMBER(8) NOT NULL,
	nombre_obra		NUMBER(8) NOT NULL,
	rol			 	VARCHAR2(40),
	PRIMARY KEY(id),
	FOREIGN KEY(nombre_persona) REFERENCES Persona(id),
	FOREIGN KEY(nombre_obra) REFERENCES Obra(id)
);

-----------------------------------------------------
CREATE TABLE Usuario
(
	id 				NUMBER(8) NOT NULL,
	nombre 			VARCHAR2(40) NOT NULL,
	sexo 			VARCHAR2(30),
	telefono		NUMBER(10),
	email			VARCHAR2(192) NOT NULL,
	pass 			VARCHAR2(192) NOT NULL,
	nacimiento		DATE,
	nacionalidad 	VARCHAR2(40),
	PRIMARY KEY(id)
);

-----------------------------------------------------
CREATE TABLE Accion
(
	id 				NUMBER(8) NOT NULL,
	nombre 			VARCHAR2(40) NOT NULL,
	fecha			DATE,
	id_usuario 		NUMBER(8) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_usuario) REFERENCES Usuario(id)
);

-----------------------------------------------------
CREATE TABLE Accion_obra
(
	id 				NUMBER(8) NOT NULL,
	id_obra 		NUMBER(8) NOT NULL,
	id_accion		NUMBER(8),
	comentario 		VARCHAR2(1000),
	puntuacion 		NUMBER(3),
	PRIMARY KEY(id),
	FOREIGN KEY(id_obra) REFERENCES Obra(id),
	FOREIGN KEY(id_accion) REFERENCES Accion(id)
);