package es.uco.pw.data.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import es.uco.pw.business.user.User;

public class UserDAO {
	
	/**
	 * Funcion que establece la conexion con la base de datos
	 * @param config
	 * @return
	 */
	public Connection getConnection(InputStream config){
		// En primer lugar, obtenemos una instancia del driver de MySQL
		Connection con=null;
		try {
		  Class.forName("com.mysql.jdbc.Driver");
		  Properties p = new Properties();
		  p.load(config);
		  con = DriverManager.getConnection(p.getProperty("servidor") + p.getProperty("puerto") + "/" + p.getProperty("basedatos"),p.getProperty("usuario"),p.getProperty("password"));
		  p.clear();
		} catch(Exception e) {
		  System.out.println(e);
		}
		return con;
	}
	
	/**
	 * Permite el registro de un usuario 
	 * @param user
	 * @param config
	 * @param sql
	 * @return status. Determina si se ha producido o no un error al introducir los datos en la base de datos
	 */
	public int registro(User user, InputStream config, InputStream sql){
		int status=0;
			try{
				Connection con=getConnection(config);
				//InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("sql.properties");
				Properties p = new Properties();
				p.load(sql);
				PreparedStatement ps=con.prepareStatement(p.getProperty("registrar"));
				ps.setString(1, user.getNombre());
				ps.setString(2, user.getApellidos());
				ps.setString(3, user.getFecha_de_nacimiento());
				ps.setString(4, user.getEmail());
				ps.setString(5, user.getPassword());
				ps.setString(6, user.getInteres());
				ps.setInt(7, user.getEdad());
				status = ps.executeUpdate();
				if (ps != null)
			    	ps.close();
			    if (con != null)
			    	con.close();
			}catch(Exception e){System.out.println(e);}
		// El invocante siempre debería tener información del resultado de la sentencia SQL
		return status;
	}
	
	/**
	 * Permite la actualizacion de los datos pertenecientes a un usuario, pasando el email, el dato a actualizar y la opcion para saber que campo se actualiza
	 * @param email
	 * @param cadena
	 * @param opcion
	 * @param config
	 * @param sql
	 * @return status. Determina el exito de la consulta a la base de datos
	 */
	public int update(String email, String cadena, int opcion, InputStream config, InputStream sql){
		int status=0;
		try{
			Connection con=getConnection(config);
			PreparedStatement ps = null;
			Properties p = new Properties();
			p.load(sql);
			
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
				ps=con.prepareStatement(p.getProperty("actualizausuariopassword"));
				ps.setString(1,cadena);
				ps.setString(2,email);
			}
			else if(opcion == 5) {
				ps=con.prepareStatement(p.getProperty("actualizausuariointeres"));
				ps.setString(1,cadena);
				ps.setString(2,email);
			}
			else if(opcion == 6) {
				ps=con.prepareStatement(p.getProperty("actualizausuarioedad"));
				ps.setInt(1,Integer.parseInt(cadena));
				ps.setString(2,email);
			}
			status=ps.executeUpdate();
		    if (ps != null)
		    	ps.close();
		    if (con != null)
		    	con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * Permite hacer una consulta a la base de datos con el email y la contraseña de un usuario
	 * @param email
	 * @param password
	 * @param config
	 * @param sql
	 * @return user o null. Depende del exito de la consulta
	 */
	public User consulta (String email, String password, InputStream config, InputStream sql) {
		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
		String nombre = "";
	    String apellidos = "";
	    String fecha_de_nacimiento = "";
	    String interes = "";
	    int edad = 0;
	    
		try {
			con = getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    rs = stmt.executeQuery(p.getProperty("consultausuario") + "'" + email + "'and password= '" + password + "'");
		    while (rs.next()) {
		    	nombre = rs.getString("nombre");
		        apellidos = rs.getString("apellidos");
		        fecha_de_nacimiento = rs.getString("fecha_de_nacimiento");
		        interes = rs.getString("interes");
		        edad = rs.getInt("edad");
		    }
		    if (rs != null)
		    	rs.close();
		    if (stmt != null)
		    	stmt.close();
		    if (con != null)
		    	con.close();
		    return new User(nombre,apellidos,fecha_de_nacimiento,email,password,interes,edad);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		    return null;
	} 
	
	/**
	 * Permite comprobar si un usuario ya se encuentra registrado
	 * @param email
	 * @param config
	 * @param sql
	 * @return 1 o 0. Depende del exito de la consulta
	 */
	public int comprobarUsuario (String email, InputStream config, InputStream sql) {
		String nombre = "";
	    
		try {
			Connection con = getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			// En consultas, se hace uso de un Statement 
			Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("consultausuario") + "'" + email + "'");
		    p.clear();
		    while (rs.next()) {
		    	nombre = rs.getString("nombre");
		    }
		    if (rs != null)
		    	rs.close();
		    if (stmt != null)
		    	stmt.close();
		    if (con != null)
		    	con.close();
		    if ( nombre.isEmpty() )
			    return 1;
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		    return 0;
	}

}
