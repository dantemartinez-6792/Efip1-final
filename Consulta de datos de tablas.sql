use efip1;
select
O.dniOperador ,
O.nombreyApellido,
E.nombreEquipo,
I.idEntrenamiento,
I.fechaSolicitud,
B.estadoEntrenamiento,
R.cantidadHoras,
R.fechaEntrenamiento,
R.tipoEntrenamiento
FROM
operador O,equipo E,inicioentrenamiento I,entrenamientooperador B,registrohoras R
where
(o.dniOperador=i.idOperador and e.codigoEquipo=i.idEquipo and 
b.idEntrenar=i.idEntrenamiento and r.idOpEntrenar= b.idEntreOpe)
and
(O.nombreyApellido='Dante Martinez');
