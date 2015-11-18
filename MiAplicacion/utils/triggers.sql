CREATE SEQUENCE obra_seq START WITH 1 INCREMENT BY 1;
 
CREATE OR REPLACE TRIGGER obra_autoincrement
BEFORE INSERT ON Obra
FOR EACH ROW
BEGIN
	IF :NEW.ID IS NULL THEN 
    	SELECT obra_seq.NEXTVAL INTO :new.id FROM dual;
	END IF;
END;

-------------------

CREATE SEQUENCE persona_seq START WITH 1 INCREMENT BY 1;
 
CREATE OR REPLACE TRIGGER persona_autoincrement
BEFORE INSERT ON Persona
FOR EACH ROW
BEGIN
	IF :NEW.ID IS NULL THEN 
    	SELECT persona_seq.NEXTVAL INTO :new.id FROM dual;
	END IF;
END;

-------------------

CREATE SEQUENCE trabaja_seq START WITH 1 INCREMENT BY 1;
 
CREATE OR REPLACE TRIGGER trabaja_autoincrement
BEFORE INSERT ON Trabaja
FOR EACH ROW
BEGIN
	IF :NEW.ID IS NULL THEN 
    	SELECT trabaja_seq.NEXTVAL INTO :new.id FROM dual;
	END IF;
END;

-------------------

CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;
 
CREATE OR REPLACE TRIGGER user_autoincrement
BEFORE INSERT ON Usuario
FOR EACH ROW
BEGIN
	IF :NEW.ID IS NULL THEN 
    	SELECT user_seq.NEXTVAL INTO :new.id FROM dual;
	END IF;
END;

-------------------

CREATE SEQUENCE accion_seq START WITH 1 INCREMENT BY 1;

-- NO USAR NO USAR NO USAR NO USAR
CREATE OR REPLACE TRIGGER accion_autoincrement
BEFORE INSERT ON Accion
FOR EACH ROW
BEGIN
	IF :NEW.ID IS NULL THEN 
    	SELECT accion_seq.NEXTVAL INTO :new.id FROM dual;
	END IF;
END;
-- NO USAR NO USAR NO USAR NO USAR

-------------------

CREATE SEQUENCE accion_obra_seq START WITH 1 INCREMENT BY 1;
 
CREATE OR REPLACE TRIGGER accion_obra_autoincrement
BEFORE INSERT ON Accion_obra
FOR EACH ROW
BEGIN
	IF :NEW.ID IS NULL THEN 
    	SELECT accion_obra_seq.NEXTVAL INTO :new.id FROM dual;
	END IF;
END;

-------------------

DROP SEQUENCE obra_seq;
DROP SEQUENCE persona_seq;
DROP SEQUENCE trabaja_seq;
DROP SEQUENCE accion_seq;
DROP SEQUENCE accion_obra_seq;
DROP TRIGGER obra_autoincrement;
DROP TRIGGER persona_autoincrement;
DROP TRIGGER trabaja_autoincrement;
DROP TRIGGER accion_autoincrement;
DROP TRIGGER accion_obra_autoincrement;


INSERT into obra(id, nombre, fecha_emision, puntuacion, duracion, capitulos, nacionalidad, ruta_imagen) 
values (13, 'Test', TO_DATE('1999-01-01', 'YYYY-MM-DD'), 5, 122, 1, 'Estados Unidos', 'images/matrix_rev.jpg');

alter table obra MODIFY nombre VARCHAR2(100);
alter table obra add ruta_imagen VARCHAR2(100);
alter table obra MODIFY plot VARCHAR2(2000);
alter table obra MODIFY imdb_votes NUMBER(16);


SELECT * FROM Persona WHERE id IN (SELECT nombre_persona FROM Trabaja WHERE nombre_obra='59')

SELECT nombre_obra from trabaja