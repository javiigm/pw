package es.uco.pw.DAO;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Hashtable;
import java.util.Properties;

//Sería recomendable tener una clase DAO que guardara los métodos comunes (p.ej. getConnection()) y 
//de la que heredase esta clase y el resto de DAOs
public class UserDAO {
	// Método que establece la conexión con la base de datos
	// NOTA: Los métodos estáticos no son obligatorios (ni siquiera los más apropiados):
	// Se ha escrito de esta manera únicamente para facilitar la ejecución
	public static Connection getConnection(){
		// En primer lugar, obtenemos una instancia del driver de MySQL
		Connection con=null;
		try {
		  Class.forName("com.mysql.jdbc.Driver");
		  // Introducir correctamente el nombre y datos de conexión - Idealmente, estos datos se 
		  // indican en un fichero de propiedades
		  Properties p = new Properties();
		  p.load(new FileReader("src/config.properties"));
		  con= DriverManager.getConnection(p.getProperty("servidor") + p.getProperty("puerto") + "/" + p.getProperty("basedatos"),p.getProperty("usuario"),p.getProperty("password"));
		// Importante capturar 
		} catch(Exception e) {
		  System.out.println(e);
		}
		return con;
	}
	
	// Método para insertar una fila
	// En ningún caso es recomendable el paso por parámetro de los valores individuales
	// Se recomienda utilizar el UserBean o una clase envoltorio User que tenga estas propiedades
	public static int save(String nombre, String apellidos, String fecha_de_nacimiento, String email, String interes, int edad){
		int status=0;
		try{
			Connection con=getConnection();
			// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
			// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			PreparedStatement ps=con.prepareStatement(p.getProperty("altausuario"));
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			ps.setString(1,nombre);
			ps.setString(2,apellidos);
			ps.setString(3,fecha_de_nacimiento);
			ps.setString(4,email);
			ps.setString(5,interes);
			ps.setInt(6,edad);
			status = ps.executeUpdate();
		// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
		// podemos capturar directamente SQLException
		}catch(Exception e){System.out.println(e);}
		// El invocante siempre debería tener información del resultado de la sentencia SQL
		return status;
	}
	
	//Método para actualizar un usuario
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
				ps=con.prepareStatement(p.getProperty("actualizausuarioemail"));
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
	
	//Para la consulta, se ha tomado una estructura Hash (columna-tabla, valor)
	public static Hashtable<String,String> consulta (String email) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("consultausuario") + "'" + email + "'");
		    while (rs.next()) {
		    	String nombre = rs.getString("nombre");
		        String apellidos = rs.getString("apellidos");
		        String fecha_de_nacimiento = rs.getString("fecha_de_nacimiento");
		        String interes = rs.getString("interes");
		        int edad = rs.getInt("edad");
		        resul = new Hashtable<String,String>();
		        resul.put("nombre", nombre);
		        resul.put("apellidos", apellidos);
		        resul.put("fecha_de_nacimiento", fecha_de_nacimiento);
		        resul.put("email", email);
		        resul.put("interes", interes);
		        resul.put("edad", Integer.toString(edad));
		        System.out.println("Nombre: " + resul.get("nombre") + "\n" + 
						   "Apellidos: " + resul.get("apellidos") + "\n" + 
						   "Fecha de nacimiento: " + resul.get("fecha_de_nacimiento") + "\n" + 
						   "Email " + resul.get("email") + "\n" +
						   "Interes: " + resul.get("interes") + "\n" +
						   "Edad: " + resul.get("edad"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	public static Hashtable<String,String> buscarNombre (String nombre) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("buscanombre") + "'" + nombre + "'");
		    while (rs.next()) {
		    	String email = rs.getString("email");
		    	nombre=rs.getString("nombre");
		        String apellidos = rs.getString("apellidos");
		        String fecha_de_nacimiento = rs.getString("fecha_de_nacimiento");
		        String interes = rs.getString("interes");
		        int edad = rs.getInt("edad");
		        resul = new Hashtable<String,String>();
		        resul.put("nombre", nombre);
		        resul.put("apellidos", apellidos);
		        resul.put("fecha_de_nacimiento", fecha_de_nacimiento);
		        resul.put("email", email);
		        resul.put("interes", interes);
		        resul.put("edad", Integer.toString(edad));  
		        System.out.println("Nombre: " + resul.get("nombre") + "\n" + 
						   "Apellidos: " + resul.get("apellidos") + "\n" + 
						   "Fecha de nacimiento: " + resul.get("fecha_de_nacimiento") + "\n" + 
						   "Email " + resul.get("email") + "\n" +
						   "Interes: " + resul.get("interes") + "\n" +
						   "Edad: " + resul.get("edad"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	public static Hashtable<String,String> buscarApellidos (String apellidos) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("buscaapellidos") + "'" + apellidos + "'");
		    while (rs.next()) {
		    	String email = rs.getString("email");
		    	String nombre = rs.getString("nombre");
		    	apellidos = rs.getString("apellidos");
		        String fecha_de_publicacion = rs.getString("fecha_de_publicacion");
		        String interes = rs.getString("interes");
		        int edad = rs.getInt("edad");
		        resul = new Hashtable<String,String>();
		        resul.put("nombre", nombre);
		        resul.put("apellidos", apellidos);
		        resul.put("fecha_de_publicacion", fecha_de_publicacion);
		        resul.put("email", email);
		        resul.put("interes", interes);
		        resul.put("edad", Integer.toString(edad));        
		        System.out.println("Nombre: " + resul.get("nombre") + "\n" + 
						   "Apellidos: " + resul.get("apellidos") + "\n" + 
						   "Fecha de nacimiento: " + resul.get("fecha_de_nacimiento") + "\n" + 
						   "Email " + resul.get("email") + "\n" +
						   "Interes: " + resul.get("interes") + "\n" +
						   "Edad: " + resul.get("edad"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	public static Hashtable<String,String> buscarNombreApellidos (String nombre, String apellidos) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("buscanombre") + "'" + nombre + "' and apellidos='" + apellidos + "'");
		    
		    while (rs.next()) {
		    	String email = rs.getString("email");
		    	nombre = rs.getString("nombre");
		    	apellidos = rs.getString("apellidos");
		        String fecha_de_nacimiento = rs.getString("fecha_de_nacimiento");
		        String interes = rs.getString("interes");
		        int edad = rs.getInt("edad");
		        resul = new Hashtable<String,String>();
		        resul.put("nombre", nombre);
		        resul.put("apellidos", apellidos);
		        resul.put("fecha_de_nacimiento", fecha_de_nacimiento);
		        resul.put("email", email);
		        resul.put("interes", interes);
		        resul.put("edad", Integer.toString(edad));
		        System.out.println("Nombre: " + resul.get("nombre") + "\n" + 
						   "Apellidos: " + resul.get("apellidos") + "\n" + 
						   "Fecha de nacimiento: " + resul.get("fecha_de_nacimiento") + "\n" + 
						   "Email " + resul.get("email") + "\n" +
						   "Interes: " + resul.get("interes") + "\n" +
						   "Edad: " + resul.get("edad"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	public static Hashtable<String,String> buscarInteres (String interes) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("buscainteres") + "'" + interes + "'");
		    while (rs.next()) {
		    	String email = rs.getString("email");
		    	String nombre = rs.getString("nombre");
		        String apellidos = rs.getString("apellidos");
		        String fecha_de_nacimiento = rs.getString("fecha_de_nacimiento");
		        interes = rs.getString("interes");
		        int edad = rs.getInt("edad");
		        resul = new Hashtable<String,String>();
		        resul.put("nombre", nombre);
		        resul.put("apellidos", apellidos);
		        resul.put("fecha_de_nacimiento", fecha_de_nacimiento);
		        resul.put("email", email);
		        resul.put("interes", interes);
		        resul.put("edad", Integer.toString(edad));
		        System.out.println("Nombre: " + resul.get("nombre") + "\n" + 
						   "Apellidos: " + resul.get("apellidos") + "\n" + 
						   "Fecha de nacimiento: " + resul.get("fecha_de_nacimiento") + "\n" + 
						   "Email " + resul.get("email") + "\n" +
						   "Interes: " + resul.get("interes") + "\n" +
						   "Edad: " + resul.get("edad"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	public static Hashtable<String,String> buscarEdad (String edad) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("buscaedad") + edad);
		    while (rs.next()) {
		    	String email = rs.getString("email");
		    	String nombre = rs.getString("nombre");
		        String apellidos = rs.getString("apellidos");
		        String fecha_de_nacimiento = rs.getString("fecha_de_nacimiento");
		        String interes = rs.getString("interes");
		        resul = new Hashtable<String,String>();
		        resul.put("nombre", nombre);
		        resul.put("apellidos", apellidos);
		        resul.put("fecha_de_nacimiento", fecha_de_nacimiento);
		        resul.put("email", email);
		        resul.put("interes", interes);
		        resul.put("edad", edad);
		        System.out.println("Nombre: " + resul.get("nombre") + "\n" + 
						   "Apellidos: " + resul.get("apellidos") + "\n" + 
						   "Fecha de nacimiento: " + resul.get("fecha_de_nacimiento") + "\n" + 
						   "Email " + resul.get("email") + "\n" +
						   "Interes: " + resul.get("interes") + "\n" +
						   "Edad: " + resul.get("edad"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	public static int delete(String email){
		int status=0;
		try{
			Connection con=getConnection();
			Properties p = new Properties();
			p.load(new FileReader("src/sql.properties"));
			PreparedStatement ps=con.prepareStatement(p.getProperty("bajausuario"));
			ps.setString(1,email);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
	
		return status;
	}
}