package operadores;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class conexionDB {
	public static Connection conexionDB(){
		Connection conexion = null;	
		String host="jdbc:mysql://localhost:3306/";
		String user="root";
		String pass="";
		String db="efip1";
		
		try {
			conexion= DriverManager.getConnection(host+db,user,pass);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conexion;
	}
	   public static void desconectar(){
		      Object conexion = null;
		   }


}
