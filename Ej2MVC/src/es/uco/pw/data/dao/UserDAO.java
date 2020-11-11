package es.uco.pw.data.dao;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import es.uco.pw.business.user.User;

public class UserDAO {
	
	public static Connection getConnection(){
		// En primer lugar, obtenemos una instancia del driver de MySQL
		Connection con=null;
		try {
		  Class.forName("com.mysql.jdbc.Driver");
		  // Introducir correctamente el nombre y datos de conexión - Idealmente, estos datos se 
		  // indican en un fichero de propiedades
		  
		  //System.out.println(new File("properties/config.properties").getAbsolutePath());
		  Properties p = new Properties();
		  p.load(new FileReader(new File("D:\\Archivos de programa\\eclipse\\Ej2MVC\\WebContent\\properties\\config.properties")));
		  con= DriverManager.getConnection(p.getProperty("servidor") + p.getProperty("puerto") + "/" + p.getProperty("basedatos"),p.getProperty("usuario"),p.getProperty("password"));
		// Importante capturar 
		} catch(Exception e) {
		  System.out.println(e);
		}
		return con;
	}
	
	
	public int registro(String nombre, String apellidos, String fecha_de_nacimiento, String email, String password, String interes, int edad){
		int status=0;
		if(consulta(email,password) != null) {
			try{
				Connection con=getConnection();
				// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
				// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
				Properties p = new Properties();
				p.load(new FileReader(new File("D:\\Archivos de programa\\eclipse\\Ej2MVC\\WebContent\\properties\\sql.properties")));
				PreparedStatement ps=con.prepareStatement(p.getProperty("registrar"));
				// El orden de los parámetros debe coincidir con las '?' del código SQL
				ps.setString(1, nombre);
				ps.setString(2, apellidos);
				ps.setString(3, fecha_de_nacimiento);
				ps.setString(4,email);
				ps.setString(5,password);
				ps.setString(6, interes);
				ps.setInt(7, edad);
				status = ps.executeUpdate();
			// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
			// podemos capturar directamente SQLException
			}catch(Exception e){System.out.println(e);}
		}
		// El invocante siempre debería tener información del resultado de la sentencia SQL
		return status;
	}
	
	public static int update(String email, String cadena, int opcion){
		int status=0;
		try{
			Connection con=getConnection();
			PreparedStatement ps = null;
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			
			if(opcion == 1) {
				ps=con.prepareStatement(p.getProperty("actualizausuarionombre"));
				ps.setString(1,cadena);
				ps.setString(2,email);
			}
			else if(opcion == 2) {
				ps=con.prepareStatement(p.getProperty("actualizausuarioapellidos"));
				ps.setString(1,cadena);
				ps.setString(2,email);
			}
			else if(opcion == 3) {
				ps=con.prepareStatement(p.getProperty("actualizausuariofechanacimiento"));
				ps.setString(1,cadena);
				ps.setString(2,email);
			}
			else if(opcion == 4) {
				ps=con.prepareStatement(p.getProperty("actualizausuariointeres"));
				ps.setString(1,cadena);
				ps.setString(2,email);
			}
			else if(opcion == 5) {
				ps=con.prepareStatement(p.getProperty("actualizausuarioedad"));
				ps.setString(1,cadena);
				ps.setString(2,email);
			}
			else {
				System.out.println("Error al introducir la opcion para actualizar");
				System.exit(0);
			}
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	public User consulta (String email, String password) {
		Statement stmt = null; 
		String nombre = "";
	    String apellidos = "";
	    String fecha_de_nacimiento = "";
	    String interes = "";
	    int edad = 0;
		try {
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader(new File("D:\\Archivos de programa\\eclipse\\Ej2MVC\\WebContent\\properties\\sql.properties")));
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("consultausuario") + "'" + email + "'and password= '" + password + "'");
		    while (rs.next()) {
		    	nombre = rs.getString("nombre");
		        apellidos = rs.getString("apellidos");
		        fecha_de_nacimiento = rs.getString("fecha_de_nacimiento");
		        interes = rs.getString("interes");
		        edad = rs.getInt("edad");
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		    return new User(email,password,nombre,apellidos,fecha_de_nacimiento,interes,edad);
		} catch (Exception e) {
			System.out.println(e);
		} 
		return null;
	} 

}
