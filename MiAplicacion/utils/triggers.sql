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

INSERT into obra(id, nombre, fecha_emision, puntuacion, duracion, capitulos, nacionalidad, ruta_imagen) 
values (13, 'Test', TO_DATE('1999-01-01', 'YYYY-MM-DD'), 5, 122, 1, 'Estados Unidos', 'images/matrix_rev.jpg');

alter table obra add ruta_imagen VARCHAR2(100);

SELECT * FROM Persona WHERE id IN (SELECT nombre_persona FROM Trabaja WHERE nombre_obra='59')

SELECT nombre_obra from trabaja