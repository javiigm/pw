package es.uco.pw.data.dao.interes;

import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class InteresDAO {
	
	/**
	 * Funcion que establece la conexion con la base de datos
	 * @return
	 */
	public Connection getConnection(){
		// En primer lugar, obtenemos una instancia del driver de MySQL
		Connection con=null;
		try {
		  Class.forName("com.mysql.jdbc.Driver");
		  Properties p = new Properties();
		  p.load(new FileReader("src/config.properties"));
		  con = DriverManager.getConnection(p.getProperty("servidor") + p.getProperty("puerto") + "/" + p.getProperty("basedatos"),p.getProperty("usuario"),p.getProperty("password"));
		  p.clear();
		} catch(Exception e) {
		  System.out.println(e);
		}
		return con;
	}
	
	/**
	 * Comprueba la existencia del interes pasado por parametro con los existentes en la base de datos.
	 * @param interes
	 * @return 1 o 0 dependiendo del exito de la consulta.
	 */
	public int comprobarInteres (String interes) {
		String interes_tabla = "";
	    
		try {
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			// En consultas, se hace uso de un Statement 
			Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("consultainteres"));
		    p.clear();
		    while (rs.next()) {
		    	interes_tabla = rs.getString("interes");
		    	if (interes_tabla.equals(interes)) {
		    		if (rs != null)
				    	rs.close();
				    if (stmt != null)
				    	stmt.close();
				    if (con != null)
				    	con.close();
		    		return 1;
		    	}
		    }
		    if (rs != null)
		    	rs.close();
		    if (stmt != null)
		    	stmt.close();
		    if (con != null)
		    	con.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		    return 0;
	}
	
	/**
	 * Obtiene todos los intereses que se encuentran en la base de datos.
	 * @return interes_tabla. String con todos los intereses
	 */
	public String consultaInteres(){
		Statement stmt = null; 
		String interes_tabla = "";
		try {
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("consultainteres"));
		    while (rs.next()) {
		    	interes_tabla = interes_tabla + rs.getString("interes") + ", ";
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return interes_tabla;
	}

}