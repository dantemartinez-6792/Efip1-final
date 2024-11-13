package operadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class equipo {
Scanner scanner = new Scanner(System.in);
private Integer codigoEquipo;
private String nombreEquipo;
private String tipoEquipo;



public equipo(Integer codigoEquipo, String nombreEquipo, String tipoEquipo) {
	super();
	this.codigoEquipo = codigoEquipo;
	this.nombreEquipo = nombreEquipo;
	this.tipoEquipo = tipoEquipo;
}


public Integer getCodigoEquipo() {
	return codigoEquipo;
}


public void setCodigoEquipo(Integer codigoEquipo) {
	this.codigoEquipo = codigoEquipo;
}


public String getNombreEquipo() {
	return nombreEquipo;
}


public void setNombreEquipo(String nombreEquipo) {
	this.nombreEquipo = nombreEquipo;
}


public String getTipoEquipo() {
	return tipoEquipo;
}


public void setTipoEquipo(String tipoEquipo) {
	this.tipoEquipo = tipoEquipo;
}

public void agregarEquipo() {
	System.out.println("Para crear un nuevo equipo por favor ingrese los datos solicitados");
	System.out.println("Inserte el codigo del equipo:");
	codigoEquipo = scanner.nextInt();
	System.out.println("Inserte el nombre del equipo:");
	nombreEquipo = scanner.next();
	System.out.println("Inserte el tipo de equipo:");
	tipoEquipo= scanner.next();

    try {
    	   PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("INSERT INTO equipo VALUES (?,?,?)");
           stmt.setInt(1,codigoEquipo);
           stmt.setString(2,nombreEquipo);
           stmt.setString(3,tipoEquipo);
        // execute insert SQL stetement
           int retorno = stmt.executeUpdate();
           if (retorno>0)
            System.out.println("Equipo creado correctamente");   
         conexionDB.desconectar();  //se cierra la conexion a la base de datos
}
catch(Exception e)
{
e.printStackTrace();
}

}


	

public void verRegistroEquipo() {
	ArrayList<equipo> ListE = new ArrayList<equipo>();
	 try {
		PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("SELECT * FROM equipo");
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			ListE.add(new equipo(rs.getInt("codigoEquipo"),rs.getString("nombreEquipo"),rs.getString("tipoEquipo")));

		}
	} catch (SQLException e) {
		e.printStackTrace();
	}  
	
   for(equipo equipo:ListE) {
	        
	        System.out.println("Codigo equipo: " + equipo.getCodigoEquipo());
	        System.out.println("Nombre del equipo: " + equipo.getNombreEquipo());
	        System.out.println("Tipo de equipo: " + equipo.getTipoEquipo());
	        
	        
	    }
	}


public void modificarEquipo(int codigoEquipo,String nombreEquipo, String tipoEquipo) throws excepcionOperadores {
	System.out.println("Ingrese el numero de Equipo que desea modificar:");
	Integer codigo = scanner.nextInt();
	 try {  
         PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("UPDATE equipo SET nombreEquipo=?, tipoEquipo=?"
		+ "WHERE codigoEquipo="+codigo+"");
			System.out.println("Ingrese el nombre correcto del equipo:");
			nombreEquipo=scanner.next();
			System.out.println("Ingrese el tipo de equipo:");
			tipoEquipo=scanner.next();
        stmt.setString(1, nombreEquipo);
        stmt.setString(2, tipoEquipo);

        
        if(stmt.executeUpdate() > 0){
        
            System.out.println("Los datos del equipo han sido modificados con éxito."); 
                                          
            
        }else{
        
        	System.out.println("Los datos del equipo no se pudieron modificar."); 
        
        }
				 }  catch(Exception e){
				       e.printStackTrace();
				    }
	}
	

public void eliminarEquipo(int codigoEquipo,String nombreEquipo, String tipoEquipo) throws excepcionOperadores {
	System.out.println("Ingrese el numero de equipo que desea modificar:");
	Integer codigo = scanner.nextInt();
	 try {   
		    PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("DELETE FROM equipo "+ "WHERE codigoEquipo = ?");         
	        stmt.setInt(1, codigo);
	        int retorno = stmt.executeUpdate();
            if (retorno>0) {
	        System.out.println("El equipo ha sido eliminado exitosamente"); 
	        conexionDB.desconectar();
            }
            else {
         System.out.println("El equipo no se encuentra registrado");
            }

	        	}
	   catch(Exception e){
	       e.printStackTrace();
	    }
}

public boolean existeEquipo(int claveE) {
    boolean disponible = false;
    try {
    	PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("SELECT count(*) FROM equipo WHERE codigoEquipo='" + claveE + "'");
	    disponible = true;
    } catch (SQLException e) {
        System.out.println(e);
    }
    return disponible;
}

	

}

