
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Scanner;

public class Ranking {
	public static final String USER = "DAW_PNDC22_23_DAMO";
	public static final String PWD = "DM123";
	// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	public static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
	 
	
		

	
	public static void select() {
		int contador =1;
		Connection con = null;

		//System.out.println("Intentando conectarse a la base de datos");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);
			//System.out.println("Conectados a la base de datos");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}

		//System.out.println("Conectados a la base de datos");

								
		
		String sql = "SELECT * FROM PARTIDA ORDER BY RONDASSUPERADAS DESC  ";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			//System.out.println(rs.isBeforeFirst());
			//System.out.println(rs.next());
			
							
			if (rs.isBeforeFirst()) {
				contador =1;
			while (rs.next()) {
				
					int ID = rs.getInt("ID");					
					int rondasSuperadas = rs.getInt("RONDASJUGADAS");					
					String nombreJugador = rs.getString("NOMBREJUGADOR");
					String fecha = rs.getString("FECHADEFIN");
					String vacunas = rs.getString("VACUNAS_ENCONTRADAS");
					String resultado = rs.getString("ESTADOPARTIDA");
			   System.out.println("---------------------------------------------------");
			   System.out.println("TOP || " + contador + " ||");
                   System.out.println("NOMBRE DEL JUGADOR: " + nombreJugador);
                   System.out.println("FECHA: " + fecha);
                   System.out.println("RONDAS SUPERADAS: " + rondasSuperadas);
                   System.out.println("VACUNAS ENCONTRADAS: " + vacunas);
                   System.out.println("RESULTADO: " + resultado);
				System.out.println("---------------------------------------------------");
				
					contador = contador +1;
				}
			} else {
				System.out.println("No he encontrado nada");
		}
			
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
