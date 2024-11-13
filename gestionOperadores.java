package operadores;

import java.sql.Connection;
import java.util.Scanner;



public class gestionOperadores {
    private static Scanner scanner;

public static void main(String[] args) throws Exception {
operador operador= new operador(0, null, null);
equipo equipo= new equipo(0, null, null);
inicioEntrenamiento inicioEntrenamiento= new inicioEntrenamiento(0, 0, 0, null);
registroHoras registroHoras=new registroHoras(null, null, null, 0, null);
scanner = new Scanner(System.in);

boolean ejecutando = true; 
while(ejecutando) {
    System.out.println("_______________________________");
    System.out.println("BIENVENIDO AL MENU PRINCIPAL DEL AREA DE ENTRENAMIENTO DE OPERADORES MINA");
    System.out.println("___________________________");
    System.out.println("Por favor seleccione la opcion que desee realizar:");
    System.out.println("1.Agregar Operador");
    System.out.println("2.Mostrar listado de operadores");
    System.out.println("3.Agregar Equipo");
    System.out.println("4.Mostrar Equipo");
    System.out.println("5.Modificar datos de operador");
    System.out.println("6. Eliminar operador");
    System.out.println("7. Iniciar entrenamiento");
    System.out.println("8. Ver registro de inicios de entrenamiento");
    System.out.println("9. Registrar horas de entrenamiento");
    System.out.println("10.Eliminar horas de entrenamiento");
    System.out.println("11. Modificar horas de entrenamiento");
    System.out.println("12. Ver registro de horas");
    System.out.println("13. Ver avance entrenamiento operador");
    System.out.println("_______________________________");
int opcion = scanner.nextInt();

switch (opcion) {
case 1:
    operador.agregarOperador();
    break;
case 2:
    operador.verRegistroOperador();
    break;
case 3:
    equipo.agregarEquipo();
    break;
case 4:
    equipo.verRegistroEquipo();
    break;
case 5:
    operador.modificarOperador(0, null, null);
    break;
case 6:
    operador.eliminarOperador(0, null, null);
    break;
case 7:
    inicioEntrenamiento.crearEntrenamiento(operador, equipo);
    break;
case 8:
	inicioEntrenamiento.verRegistroInicioEntrenamiento();
	break;
case 9:
	registroHoras.registrarHoras(inicioEntrenamiento);
	break;
case 10:
	registroHoras.eliminarRegistroHoras(opcion, opcion, null, opcion, null);
	break;
case 11:
	registroHoras.modificarHoras(opcion, opcion, null, opcion, null);
	break;
case 12:
	registroHoras.verRegistroHoras();
	break;
case 13:
	registroHoras.verAvanceEntrenamientos();
	break;
}
}
}	
}