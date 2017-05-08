

CREATE TABLE IF NOT EXISTS  Depto(
  id_depto int primary key not null,
  nom_depto varchar(100) not null,
  porcentaje real not null
);


CREATE TABLE IF NOT EXISTS  Empleado(
  id_empleado int primary key not null,
  nom_empleado varchar(100) not null,
  tipo_contrato varchar(50) not null,
  id_depto int REFERENCES  Depto(id_depto) not null
);



CREATE TABLE IF NOT EXISTS Proyecto(
  id_proyecto int primary key not null,
  nom_proy varchar(100) not null
);

CREATE TABLE IF NOT EXISTS  Recurso(
  id_rec int primary key not null,
  nom_rec  varchar(100) not null,
  id_proyecto int REFERENCES  Proyecto(id_proyecto) not null
);

CREATE TABLE IF NOT EXISTS  DeptoProyecto(
   id int primary key not null,
   id_depto int REFERENCES  Depto(id_depto) not null,
   id_proyecto int REFERENCES  Proyecto(id_proyecto) not null
);


INSERT INTO Depto (id_depto, nom_depto, porcentaje) VALUES (1, 'Conta', 40.0);
INSERT INTO Depto (id_depto, nom_depto, porcentaje) VALUES (2, 'System', 60.0);

INSERT INTO Empleado (id_empleado, nom_empleado, tipo_contrato, id_depto) VALUES (1, 'Julian', 'temp', 1);
INSERT INTO Empleado (id_empleado, nom_empleado, tipo_contrato, id_depto) VALUES (2, 'Mateo', 'perm', 2);
INSERT INTO Empleado (id_empleado, nom_empleado, tipo_contrato, id_depto) VALUES (3, 'Maria', 'temp', 1);
INSERT INTO Empleado (id_empleado, nom_empleado, tipo_contrato, id_depto) VALUES (4, 'Alberto', 'perm', 2);
INSERT INTO Empleado (id_empleado, nom_empleado, tipo_contrato, id_depto) VALUES (5, 'Alicia', 'perm', 1);

INSERT INTO Proyecto (id_proyecto, nom_proy) VALUES (1, 'Graficos');
INSERT INTO Proyecto (id_proyecto, nom_proy) VALUES (2, 'Tablas');

INSERT INTO Recurso (id_rec, nom_rec, id_proyecto) VALUES (1, 'asd', 1);
INSERT INTO Recurso (id_rec, nom_rec, id_proyecto) VALUES (2, 'jcdc', 2);
INSERT INTO Recurso (id_rec, nom_rec, id_proyecto) VALUES (3, 'jc', 2);
INSERT INTO Recurso (id_rec, nom_rec, id_proyecto) VALUES (4, 'cd', 1);
INSERT INTO Recurso (id_rec, nom_rec, id_proyecto) VALUES (5, 'jdc', 2);
INSERT INTO Recurso (id_rec, nom_rec, id_proyecto) VALUES (6, 'yuy', 2);

INSERT INTO DeptoProyecto (id, id_depto, id_proyecto) VALUES (1, 1, 1);
INSERT INTO DeptoProyecto (id, id_depto, id_proyecto) VALUES (2, 2, 1);
INSERT INTO DeptoProyecto (id, id_depto, id_proyecto) VALUES (3, 1, 2);
INSERT INTO DeptoProyecto (id, id_depto, id_proyecto) VALUES (4, 2, 2);
INSERT INTO DeptoProyecto (id, id_depto, id_proyecto) VALUES (5, 2, 1);

select nom_proy, count(*) as total from Proyecto left join Recurso using (id_proyecto) group by nom_proy;
select nom_depto, count(*) as num from Depto natural join Proyecto natural join DeptoProyecto group by nom_depto;
select nom_depto, tipo_contrato, count(*) as total from Depto join Empleado using (id_depto) group by nom_depto, tipo_contrato having count(*)>1;