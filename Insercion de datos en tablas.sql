use efip1;

insert into operador(dniOperador, nombreyApellido,grupoTrabajo)
values
(37303910,'Dante Martinez', 'A'),
(34879526, 'Angelina Velasquez', 'B'),
(27548398, 'Orlando Escudero', 'B');

insert into equipo (codigoEquipo, nombreEquipo,tipoEquipo)
values
(101, 'Komatsu hd785', 'Equipo de acarreo'),
(601, 'komatsu w900', 'Equipo de carguio');

insert into inicioEntrenamiento(idEntrenamiento, 
idOperador,idEquipo,fechaSolicitud)
values
(1,37303910,601,'2024-06-05');

insert into registroHoras(idRegistroHoras, 
idOpEntrenar,fechaEntrenamiento,cantidadHoras,tipoEntrenamiento)
values
(001,10,'2024-06-06',8,'En sala');


