package operadores;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class operador {
		Scanner scanner = new Scanner(System.in);
		private Integer dniOperador;
		private String nombreyApellido;
		private String grupo;
	



	public operador(Integer dniOperador, String nombreyApellido, String grupo) {
			this.dniOperador = dniOperador;
			this.nombreyApellido = nombreyApellido;
			this.grupo = grupo;
		}


	public Integer getDniOperador() {
		return dniOperador;
	}


	public void setDniOperador(Integer dniOperador) {
		this.dniOperador = dniOperador;
	}


	public String getNombreyApellido() {
		return nombreyApellido;
	}


	public void setNombreyApellido(String nombreyApellido) {
		this.nombreyApellido = nombreyApellido;
	}


	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public void agregarOperador() {
		

		System.out.println("Para crear un nuevo operador por favor ingrese los datos solicitados");
		System.out.println("Inserte el numero de DNI del operador:");
		dniOperador = scanner.nextInt();
		System.out.println("Inserte el nombre y apellido del operador:");
		nombreyApellido = scanner.next();
		System.out.println("Inserte el grupo al que pertenece el operador:");
		grupo= scanner.next();

	        try {
	        	   PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("INSERT INTO operador VALUES (?,?,?)");
	               stmt.setInt(1,dniOperador);
	               stmt.setString(2,nombreyApellido);
	               stmt.setString(3,grupo);
	            // execute insert SQL stetement
	               int retorno = stmt.executeUpdate();
	               if (retorno>0)
	                System.out.println("Operador creado correctamente");   
                 conexionDB.desconectar();  //se cierra la conexion a la base de datos
    }
    catch(Exception e)
    {
       e.printStackTrace();
    }

	}


	public void verRegistroOperador() {
		ArrayList<operador> List = new ArrayList<operador>();
		 try {
			PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("SELECT * FROM operador");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				List.add(new operador(rs.getInt("dniOperador"),rs.getString("nombreyApellido"),rs.getString("grupoTrabajo")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
        for(operador operador:List) {
		        
		        System.out.println("Dni: " + operador.getDniOperador());
		        System.out.println("Nombre y Apellido: " + operador.getNombreyApellido());
		        System.out.println("Grupo: " + operador.getGrupo());
		        
		        
		    }
        

		}
	
	public void modificarOperador(Integer dniOperador,String nombreyApellido, String grupo) throws excepcionOperadores {
		System.out.println("Ingrese el numero de dni que desea modificar:");
		Integer dni = scanner.nextInt();

	 try {  
         PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("UPDATE operador SET nombreyApellido=?, grupoTrabajo=?"
		+ "WHERE dniOperador="+dni+"");
			System.out.println("Ingrese el nombre correcto del operador:");
			nombreyApellido=scanner.next();
			System.out.println("Ingrese el nuevo grupo del operador");
			grupo=scanner.next();
        stmt.setString(1, nombreyApellido);
        stmt.setString(2, grupo);

        
        if(stmt.executeUpdate() > 0){
        
            System.out.println("Los datos del operador han sido modificados con éxito."); 
                                          
            
        }else{
        
        	System.out.println("Los datos del operador no se pudieron modificar."); 
        
        }
				 }  catch(Exception e){
				       e.printStackTrace();
				    }
				 }

	
	
	public void eliminarOperador(int dniOperador,String nombreyApellido, String grupo) throws excepcionOperadores {
		System.out.println("Ingrese el numero de dni que desea eliminar:");
		Integer dni = scanner.nextInt();
				 try {   
					    PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("DELETE FROM operador "+ "WHERE dniOperador = ?");         
				        stmt.setInt(1, dni);
				        int retorno = stmt.executeUpdate();
			               if (retorno>0) {
				        System.out.println("El operador ha sido eliminado exitosamente"); 
				        conexionDB.desconectar();
			               }
			               else {
			            System.out.println("El operador no se encuantra registrado");
			               }

				        	}
				   catch(Exception e){
				       e.printStackTrace();
				    }
	}
	

	public boolean existeOperador(int clave) {
        boolean disponible = false;
        try {
        	PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("SELECT count(*) FROM operador WHERE dniOperador='" + clave + "'");
		    disponible = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return disponible;
    }
	}
	



