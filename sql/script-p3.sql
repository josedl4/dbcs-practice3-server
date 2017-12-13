CREATE TABLE "USUARIO" ( 
"ID" VARCHAR(9) not NULL primary key,  
"NOMBRE" VARCHAR(20) default NULL, 
"CLAVE" VARCHAR(20) not NULL 
);

INSERT INTO USUARIO (id,nombre,clave) VALUES  
('primero','Primero','111'), 
('segundo','Segundo','222'), 
('tercero','Tercero','333'); 
 
CREATE TABLE "JUGADOR" ( 
"ID" INTEGER NOT NULL primary key 
GENERATED ALWAYS AS IDENTITY 
(START WITH 1, INCREMENT BY 1),  
"NAME" VARCHAR(50) default NULL, 
"POSITION" VARCHAR(20) DEFAULT NULL, 
"JERSEYNUMBER" integer DEFAULT 0, 
"DATEOFBIRTH" VARCHAR(20) DEFAULT NULL, 
"NATIONALITY" VARCHAR(20) DEFAULT NULL, 
"CONTRACTUNTIL" VARCHAR(20) DEFAULT NULL, 
"MARKETVALUE" VARCHAR(20) DEFAULT NULL 
);

INSERT INTO JUGADOR (name, position, jerseyNumber, dateOfBirth, nationality, contractUntil, marketValue) 
VALUES 
('jugador1','pos1',1,'fecha1','nacion1','hasta1','1 E'), 
('jugador2','pos2',2,'fecha2','nacion2','hasta2','2 E'), 
('jugador3','pos3',3,'fecha3','nacion3','hasta3','3 E'); 
 
 
CREATE TABLE "EQUIPO" ( 
"ID" INTEGER NOT NULL primary key 
GENERATED ALWAYS AS IDENTITY 
(START WITH 1, INCREMENT BY 1),  
"US_ID" VARCHAR(9) not NULL, 
"JUG_ID" INTEGER UNIQUE,  
CONSTRAINT EQ_FK1 
 
 FOREIGN KEY (US_Id) 
 
 REFERENCES usuario (id), 
CONSTRAINT EQ_FK2 
    FOREIGN KEY (JUG_Id) 
    REFERENCES JUGADOR (id) 
); 
INSERT INTO EQUIPO (us_id,jug_id) VALUES  
('primero',1), 
('primero',2), 
('segundo',3); 
