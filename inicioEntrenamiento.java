package operadores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class inicioEntrenamiento {
public int idEntrenamiento;
public int idOperador;
public int idEquipo;
public String fechaSolicitud;
private static ArrayList<inicioEntrenamiento> Registro = new ArrayList<inicioEntrenamiento>();
Scanner scanner = new Scanner(System.in);



public inicioEntrenamiento(int idEntrenamiento, int idOperador, int idEquipo, String fechaSolicitud) {
	this.idEntrenamiento = idEntrenamiento;
	this.idOperador = idOperador;
	this.idEquipo = idEquipo;
	this.fechaSolicitud = fechaSolicitud;
}

public int getIdEntrenamiento() {
	return idEntrenamiento;
}

public void setIdEntrenamiento(int idEntrenamiento) {
	this.idEntrenamiento = idEntrenamiento;
}

public int getIdOperador() {
	return idOperador;
}

public void setIdOperador(int idOperador) {
	this.idOperador = idOperador;
}

public int getIdEquipo() {
	return idEquipo;
}

public void setIdEquipo(int idEquipo) {
	this.idEquipo = idEquipo;
}

public String getFechaSolicitud() {
	return fechaSolicitud;
}

public void setFechaSolicitud(String fechaSolicitud) {
	this.fechaSolicitud = fechaSolicitud;
}



public void crearEntrenamiento(operador operador, equipo equipo) throws ParseException, excepcionOperadores, SQLException{
	int b = 0;
	System.out.println("Ingrese el numero de dni del operador a inicia entrenamiento:");
	Integer dniOpe = scanner.nextInt();
	System.out.println("Ingrese el equipo en el que entrenara el operador:");
	Integer codEquipo=scanner.nextInt();
	int clave=dniOpe;
	int claveE=codEquipo;
    if(operador.existeOperador(clave) && equipo.existeEquipo(claveE)) {
				idOperador=dniOpe;
				idEquipo=codEquipo;
				System.out.println("Ingrese la fecha de incio de entrenamiento");
				String fechaSolicitud = scanner.next();
				SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			        Date parsed = formato.parse(fechaSolicitud);
			        java.sql.Date sql = new java.sql.Date(parsed.getTime());
			        try {
			        	   PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("INSERT INTO inicioEntrenamiento VALUES (?,?,?,?)");
						   stmt.setInt(1,inicioEntrenamiento.idIncrementable());
			               stmt.setInt(2,idOperador);
			               stmt.setInt(3,idEquipo);
			               stmt.setDate(4,sql);
			            // execute insert SQL stetement
			               int retorno = stmt.executeUpdate();
			               if (retorno>0)
			                System.out.println("Inicio de entrenamiento registrado correctamente");   
		                 conexionDB.desconectar();  //se cierra la conexion a la base de datos
		    }
		    catch(Exception e)
		    {
		       e.printStackTrace();
		    }}
    }

public static int idIncrementable() {
	int id=1;
	try {
	PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("SELECT MAX(idEntrenamiento) FROM inicioEntrenamiento");
	ResultSet rs = stmt.executeQuery();
	while(rs.next()) {
		id=rs.getInt(1) + 1;

	}
} catch (SQLException e) {
	e.printStackTrace();
} 
	return id;
}


public void verRegistroInicioEntrenamiento() {
    for(int i = 0; i < Registro.size(); i++){ 

        inicioEntrenamiento inicioEntrenamiento =  Registro.get(i); 
        
        System.out.println("Codigo de Entrenamiento: " + inicioEntrenamiento.getIdEntrenamiento());
        System.out.println("Id del operador: " + inicioEntrenamiento.getIdOperador());
        System.out.println("Id de equipo: " + inicioEntrenamiento.getIdEquipo());
        System.out.println("Fecha de inicio:" + inicioEntrenamiento.getFechaSolicitud());
        
    }
}


public boolean existeEntrenamiento(int claveG) {
    boolean disponible = false;
    try {
    	PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("SELECT count(*) FROM inicioEntrenamiento WHERE idEntrenamiento='" + claveG + "'");
	    disponible = true;
    } catch (SQLException e) {
        System.out.println(e);
    }
    return disponible;

}
}