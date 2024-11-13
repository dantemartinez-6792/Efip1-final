use efip1;
create table operador (
dniOperador INT PRIMARY KEY,
nombreyApellido varchar (250),
grupoTrabajo varchar (50)
);
create table equipo (
codigoEquipo int primary key,
nombreEquipo varchar (100),
tipoEquipo varchar (100)
);

create table inicioEntrenamiento (
idEntrenamiento int primary key,
idOperador int,
idEquipo int,
fechaSolicitud date,
foreign key(idOperador) references operador(dniOperador),
foreign key (idEquipo) references equipo(codigoEquipo)
);

create table registroHoras (
idRegistroHoras int primary Key,
idOpEntrenar int,
fechaEntrenamiento date,
cantidadHoras double,
tipoEntrenamiento varchar (100),
foreign key(idOpEntrenar) references inicioentrenamiento(idEntrenamiento)
);
