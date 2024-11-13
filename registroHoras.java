package operadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class registroHoras {
	 private static final ArrayList<registroHoras> RegistroH = null;
	public Integer idRegistroHoras;
	 public Integer idOpEntrenar;
	 public Date fechaEntrenamiento;
	 public Integer cantidadHoras;
	 public String tipoEntrenamiento;
	
	 Scanner scanner = new Scanner(System.in);
	 int g;
	 double sumaHoras;
	 String estadoEntrenamiento;
	private ArrayList<registroHoras> registroH;
	 
	public registroHoras(Integer idRegistroHoras, Integer idOpEntrenar, Date fechaEntrenamiento, Integer cantidadHoras,
			String tipoEntrenamiento) {
		super();
		this.idRegistroHoras = idRegistroHoras;
		this.idOpEntrenar = idOpEntrenar;
		this.fechaEntrenamiento = fechaEntrenamiento;
		this.cantidadHoras = cantidadHoras;
		this.tipoEntrenamiento= tipoEntrenamiento;
	}
	

	public Integer getIdRegistroHoras() {
		return idRegistroHoras;
	}

	public void setIdRegistroHoras(Integer idRegistroHoras) {
		this.idRegistroHoras = idRegistroHoras;
	}

	public Integer getIdOpEntrenar() {
		return idOpEntrenar;
	}

	public void setIdOpEntrenar(Integer idOpEntrenar) {
		this.idOpEntrenar = idOpEntrenar;
	}

	public Date getFechaEntrenamiento() {
		return fechaEntrenamiento;
	}

	public void setFechaEntrenamiento(Date fechaEntrenamiento) {
		this.fechaEntrenamiento = fechaEntrenamiento;
	}

	public double getCantidadHoras() {
		return cantidadHoras;
	}

	public void setCantidadHoras(Integer cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	public String getTipoEntrenamiento() {
		return tipoEntrenamiento;
	}

	public void setTipoEntrenamiento(String tipoEntrenamiento) {
		this.tipoEntrenamiento = tipoEntrenamiento;
	}

	public void registrarHoras(inicioEntrenamiento inicioEntrenamiento) throws ParseException {
		g++;
		System.out.println("Ingrese el id de entrenamiento de operador:");
		Integer idOpe = scanner.nextInt();
		int claveG=idOpe;
	    		if(inicioEntrenamiento.existeEntrenamiento(claveG)) {
					idOpEntrenar=idOpe;
					System.out.println("Ingrese la fecha de incio de entrenamiento");
					String fechaSolicitud = scanner.next();
					SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			        Date parsed = formato.parse(fechaSolicitud);
			        java.sql.Date sql = new java.sql.Date(parsed.getTime());
					System.out.println("Ingrese la cantidad de horas:");
					cantidadHoras= scanner.nextInt();
					System.out.println("Ingrese el tipo de entrenamiento:");
					tipoEntrenamiento=scanner.next();
			        try {
			        	   PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("INSERT INTO registroHoras VALUES (?,?,?,?,?)");
			               stmt.setInt(1,registroHoras.idIncrementable());
			               stmt.setInt(2,idOpEntrenar);
			               stmt.setDate(3,sql);
			               stmt.setInt(4, cantidadHoras);
			               stmt.setString(5,tipoEntrenamiento);
			            // execute insert SQL stetement
			               int retorno = stmt.executeUpdate();
			               if (retorno>0)
			                System.out.println("Registro de horas insertado correctamente");   
		                 conexionDB.desconectar();  //se cierra la conexion a la base de datos
		    }
		    catch(Exception e)
		    {
		       e.printStackTrace();
		    }



	    }
	    }
	
	public static int idIncrementable() {
		int id=1;
		try {
		PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("SELECT MAX(idRegistroHoras) FROM registroHoras");
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			id=rs.getInt(1) + 1;

		}
	} catch (SQLException e) {
		e.printStackTrace();
	} 
		return id;
	}



	public void modificarHoras(Integer idRegistroHoras, Integer idOpEntrenar, Date fechaEntrenamiento, Integer cantidadHoras,
			String tipoEntrenamiento) throws ParseException, excepcionOperadores {
		System.out.println("Ingrese el numero del registro de horas a modificar:");
		Integer idReg = scanner.nextInt();
		 try {  
	         PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("UPDATE registroHoras SET fechaEntrenamiento=?, cantidadHoras=?, tipoEntrenamiento=?"
			+ "WHERE idRegistroHoras="+idReg+"");
				System.out.println("Ingrese la fecha de entrenamiento:");
				String fechaComoTexto = scanner.next();
				String formato="dd/mm/yyyy";
				SimpleDateFormat df = new SimpleDateFormat(formato);
				 fechaEntrenamiento = df.parse(fechaComoTexto);
				System.out.println("Ingrese cantidad de horas");
				cantidadHoras=scanner.nextInt();
				System.out.println("Ingrese tipo de entrenamiento:");
				tipoEntrenamiento=scanner.next();
	
	        stmt.setDate(1, (java.sql.Date) fechaEntrenamiento);
	        stmt.setInt(2, cantidadHoras);
	        stmt.setString(3, tipoEntrenamiento);

	        
	        if(stmt.executeUpdate() > 0){
	        
	            System.out.println("Los datos del registro de horas han sido modificados con éxito."); 
	                                          
	            
	        }else{
	        
	        	System.out.println("Los datos del registro de horas no se pudieron modificar."); 
	        
	        }
					 }  catch(Exception e){
					       e.printStackTrace();
					    }
	}
	
	public void eliminarRegistroHoras(Integer idRegistroHoras, Integer idOpEntrenar, Date fechaEntrenamiento, Integer cantidadHoras,
			String tipoEntrenamiento) throws excepcionOperadores {
		System.out.println("Ingrese el id del registro de horas a eliminar:");
		Integer idRegis = scanner.nextInt();
			 try {   
				    PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("DELETE FROM registroHoras "+ "WHERE idRegistroHoras = ?");         
			        stmt.setInt(1, idRegis);
			        int retorno = stmt.executeUpdate();
		               if (retorno>0) {
			        System.out.println("El registro de horas ha sido eliminado exitosamente"); 
			        conexionDB.desconectar();
		               }
		               else {
		            System.out.println("El registro de horas no se encuentra en la base de datos");
		               }

			        	}
			   catch(Exception e){
			       e.printStackTrace();
			    }
		}

	public void verRegistroHoras() {
		 ArrayList<registroHoras> RegistroH = new ArrayList<registroHoras>();
		 try {
			PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("SELECT * FROM registroHoras");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				RegistroH.add(new registroHoras(rs.getInt("idRegistroHoras"),rs.getInt("idOpEntrenar"),rs.getDate("fechaEntrenamiento"),rs.getInt("cantidadHoras"),rs.getString("tipoEntrenamiento")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}         

	    for(registroHoras registroHoras: RegistroH){ 
	        
	        System.out.println("Id de registro: " + registroHoras.getIdRegistroHoras());
	        System.out.println("Id operador a Entrenar: " + registroHoras.getIdOpEntrenar());
	        System.out.println("Fecha de entrenamiento: " + registroHoras.getFechaEntrenamiento());
	        System.out.println("Cantidad de horas:"+ registroHoras.getCantidadHoras());
	        System.out.println("Tipo entrenamiento:" + registroHoras.getTipoEntrenamiento());
	        
	    }
		}
	
	public String estadoEntrenamiento(double sumaHoras2) {
			if(sumaHoras2< 50) {
				estadoEntrenamiento= "Capacitacion en Sala";
			}
			if(sumaHoras2> 50 && sumaHoras2< 150) {
			   estadoEntrenamiento= "Capacitacion en campo con operador con experiencia";	
			}
			if(sumaHoras2>150){
			   estadoEntrenamiento= "Capacitacion practica solo";
			}
			return estadoEntrenamiento;
		}
		
		        
		    


	public void verAvanceEntrenamientos() throws excepcionOperadores {
		System.out.println("Ingrese el numero de Id del operador:");
		Integer idOpera = scanner.nextInt();
		 ArrayList<registroHoras> RegistroH = new ArrayList<registroHoras>();
		 try {
			PreparedStatement stmt = conexionDB.conexionDB().prepareStatement("SELECT * FROM registroHoras WHERE idOpEntrenar="+idOpera+"");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				RegistroH.add(new registroHoras(rs.getInt("idRegistroHoras"),rs.getInt("idOpEntrenar"),rs.getDate("fechaEntrenamiento"),rs.getInt("cantidadHoras"),rs.getString("tipoEntrenamiento")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}         
		sumaHoras=0;
		for(registroHoras registroHoras:RegistroH) {
        sumaHoras+=registroHoras.getCantidadHoras();
	    }
        System.out.println("Codigo de Entrenamiento: " + idOpera);
        System.out.println("Total Horas: " + sumaHoras);
        System.out.println("Estado Entrenamiento:" + estadoEntrenamiento(sumaHoras) );
		}
	


}
