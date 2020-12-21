package es.uco.pw.data.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Properties;

import es.uco.pw.business.anuncio.Anuncio;


public class AnuncioDAO {
	// Método que establece la conexión con la base de datos
	// NOTA: Los métodos estáticos no son obligatorios (ni siquiera los más apropiados):
	// Se ha escrito de esta manera únicamente para facilitar la ejecución
	public static Connection getConnection(InputStream config){
		// En primer lugar, obtenemos una instancia del driver de MySQL
		Connection con=null;
		try {
		  Class.forName("com.mysql.jdbc.Driver");
		  // Introducir correctamente el nombre y datos de conexión - Idealmente, estos datos se 
		  // indican en un fichero de propiedades
		  Properties p = new Properties();
		  p.load(config);
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
	public static int save(Anuncio a,InputStream config, InputStream sql){
		int status=0;
		try{
			Connection con=getConnection(config);
			// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
			// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
			Properties p = new Properties();
			p.load(sql);
			PreparedStatement ps=con.prepareStatement(p.getProperty("editaranuncio"));
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			ps.setInt(1,a.getIdentificador());
			ps.setString(2,a.getTitulo());
			ps.setString(3,a.getUsuario_propietario());
			ps.setString(4,a.getUsuarios_destinatarios());
			ps.setString(5,a.getCuerpo());
			ps.setString(6,a.getFecha_de_publicacion());
			ps.setString(7,a.getType().toString());
			ps.setString(8,a.getEstado().toString());
			ps.setString(9,a.getTema());
			status = ps.executeUpdate();
		// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
		// podemos capturar directamente SQLException
		}catch(Exception e){System.out.println(e);}
		// El invocante siempre debería tener información del resultado de la sentencia SQL
		return status;
	}
	
	//Método para actualizar un anuncio
	public static int update(int identificador, String estado, int opcion, InputStream config, InputStream sql){
		int status=0;
		try{
			Connection con=getConnection(config);
			PreparedStatement ps = null;
			Properties p = new Properties();
			p.load(sql);
			
			if(opcion == 1) {
				ps=con.prepareStatement(p.getProperty("guardaranuncio"));
				ps.setString(1,estado);
				ps.setInt(2,identificador);
			}
			else if(opcion == 2) {
				ps=con.prepareStatement(p.getProperty("publicaranuncio"));
				ps.setString(1,estado);
				ps.setInt(2,identificador);
			}
			else if(opcion == 3) {
				ps=con.prepareStatement(p.getProperty("archivaranuncio"));
				ps.setString(1,estado);
				ps.setInt(2,identificador);
			}
			else if(opcion == 4) {
				ps=con.prepareStatement(p.getProperty("enesperaanuncio"));
				ps.setString(1,estado);
				ps.setInt(2,identificador);
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
	public static Hashtable<String,String> consulta (String email, InputStream config, InputStream sql) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection(config);
			Properties p = new Properties();
			p.load(sql);
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
		
	public static Hashtable<String,String> buscarFecha (String fecha_publicacion, InputStream config, InputStream sql) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("buscarfecha") + "'" + fecha_publicacion + "'");
		    while (rs.next()) {
		    	String identificador = rs.getString("identificador");
		    	String titulo = rs.getString("titulo");
		        String usuario_propietario = rs.getString("usuario_propietario");
		        String usuarios_destinatarios = rs.getString("usuarios_destinatarios");
		        String cuerpo = rs.getString("cuerpo");
		        String fecha_de_publicacion = rs.getString("fecha_de_publicacion");
		        String type = rs.getString("type");
		        String estado = rs.getString("estado");
		        String temas = rs.getString("temas");

		        resul = new Hashtable<String,String>();
		        resul.put("identificador", identificador);
		        resul.put("titulo", titulo);
		        resul.put("usuario_propietario", usuario_propietario);
		        resul.put("usuarios_destinatarios", usuarios_destinatarios);
		        resul.put("cuerpo", cuerpo);
		        resul.put("fecha_de_publicacion", fecha_de_publicacion);
		        resul.put("type", type);
		        resul.put("estado", estado);
		        resul.put("temas", temas);

		        System.out.println("identificador: " + resul.get("identificador") + "\n" + 
						   "titulo: " + resul.get("titulo") + "\n" + 
						   "usuario propietario: " + resul.get("usuario_propietario") + "\n" + 
						   "usuarios destinatarios " + resul.get("usuarios_destinatarios") + "\n" +
						   "cuerpo: " + resul.get("cuerpo") + "\n" +
						   "fecha de publicacion: " + resul.get("fecha_de_publicacion") + "\n" +
						   "type: " + resul.get("type") + "\n" +
						   "estado: " + resul.get("estado") + "\n" +
						   "temas: " + resul.get("temas"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 

	public static Hashtable<String,String> buscarTemas (String temas, InputStream config, InputStream sql) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("buscartema") + "'" + temas + "'");
		    while (rs.next()) {
		    	String identificador = rs.getString("identificador");
		    	String titulo = rs.getString("titulo");
		        String usuario_propietario = rs.getString("usuario_propietario");
		        String usuarios_destinatarios = rs.getString("usuarios_destinatarios");
		        String cuerpo = rs.getString("cuerpo");
		        String fecha_de_publicacion = rs.getString("fecha_de_publicacion");
		        String type = rs.getString("type");
		        String estado = rs.getString("estado");
		        temas = rs.getString("temas");

		        resul = new Hashtable<String,String>();
		        resul.put("identificador", identificador);
		        resul.put("titulo", titulo);
		        resul.put("usuario_propietario", usuario_propietario);
		        resul.put("usuarios_destinatarios", usuarios_destinatarios);
		        resul.put("cuerpo", cuerpo);
		        resul.put("fecha_de_publicacion", fecha_de_publicacion);
		        resul.put("type", type);
		        resul.put("estado", estado);
		        resul.put("temas", temas);

		        System.out.println("identificador: " + resul.get("identificador") + "\n" + 
						   "titulo: " + resul.get("titulo") + "\n" + 
						   "usuario propietario: " + resul.get("usuario_propietario") + "\n" + 
						   "usuarios destinatarios " + resul.get("usuarios_destinatarios") + "\n" +
						   "cuerpo: " + resul.get("cuerpo") + "\n" +
						   "fecha de publicacion: " + resul.get("fecha_de_publicacion") + "\n" +
						   "type: " + resul.get("type") + "\n" +
						   "estado: " + resul.get("estado") + "\n" +
						   "temas: " + resul.get("temas"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 

	public static Hashtable<String,String> buscarUsuarioPropietario (String usuario_propietario, InputStream config, InputStream sql) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("buscarusuariopropietario") + "'" + usuario_propietario + "'");
		    while (rs.next()) {
		    	String identificador = rs.getString("identificador");
		    	String titulo = rs.getString("titulo");
		        usuario_propietario = rs.getString("usuario_propietario");
		        String usuarios_destinatarios = rs.getString("usuarios_destinatarios");
		        String cuerpo = rs.getString("cuerpo");
		        String fecha_de_publicacion = rs.getString("fecha_de_publicacion");
		        String type = rs.getString("type");
		        String estado = rs.getString("estado");
		        String temas = rs.getString("temas");

		        resul = new Hashtable<String,String>();
		        resul.put("identificador", identificador);
		        resul.put("titulo", titulo);
		        resul.put("usuario_propietario", usuario_propietario);
		        resul.put("usuarios_destinatarios", usuarios_destinatarios);
		        resul.put("cuerpo", cuerpo);
		        resul.put("fecha_de_publicacion", fecha_de_publicacion);
		        resul.put("type", type);
		        resul.put("estado", estado);
		        resul.put("temas", temas);

		        System.out.println("identificador: " + resul.get("identificador") + "\n" + 
						   "titulo: " + resul.get("titulo") + "\n" + 
						   "usuario propietario: " + resul.get("usuario_propietario") + "\n" + 
						   "usuarios destinatarios " + resul.get("usuarios_destinatarios") + "\n" +
						   "cuerpo: " + resul.get("cuerpo") + "\n" +
						   "fecha de publicacion: " + resul.get("fecha_de_publicacion") + "\n" +
						   "type: " + resul.get("type") + "\n" +
						   "estado: " + resul.get("estado") + "\n" +
						   "temas: " + resul.get("temas"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 

	public static Hashtable<String,String> buscarUsuarioDestinatario (String usuarios_destinatarios, InputStream config, InputStream sql) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("buscarusuariodestinatario") + "'" + usuarios_destinatarios + "'");
		    while (rs.next()) {
		    	String identificador = rs.getString("identificador");
		    	String titulo = rs.getString("titulo");
		    	String usuario_propietario = rs.getString("usuario_propietario");
		    	usuarios_destinatarios = rs.getString("usuarios_destinatarios");
		        String cuerpo = rs.getString("cuerpo");
		        String fecha_de_publicacion = rs.getString("fecha_de_publicacion");
		        String type = rs.getString("type");
		        String estado = rs.getString("estado");
		        String temas = rs.getString("temas");

		        resul = new Hashtable<String,String>();
		        resul.put("identificador", identificador);
		        resul.put("titulo", titulo);
		        resul.put("usuario_propietario", usuario_propietario);
		        resul.put("usuarios_destinatarios", usuarios_destinatarios);
		        resul.put("cuerpo", cuerpo);
		        resul.put("fecha_de_publicacion", fecha_de_publicacion);
		        resul.put("type", type);
		        resul.put("estado", estado);
		        resul.put("temas", temas);

		        System.out.println("identificador: " + resul.get("identificador") + "\n" + 
						   "titulo: " + resul.get("titulo") + "\n" + 
						   "usuario propietario: " + resul.get("usuario_propietario") + "\n" + 
						   "usuarios destinatarios " + resul.get("usuarios_destinatarios") + "\n" +
						   "cuerpo: " + resul.get("cuerpo") + "\n" +
						   "fecha de publicacion: " + resul.get("fecha_de_publicacion") + "\n" +
						   "type: " + resul.get("type") + "\n" +
						   "estado: " + resul.get("estado") + "\n" +
						   "temas: " + resul.get("temas"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	public static Hashtable<String,String> mostrarAnuncio (String estado, InputStream config, InputStream sql) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("mostraranuncioestado") + "'" + estado + "'");
		    while (rs.next()) {
		    	String identificador = rs.getString("identificador");
		    	String titulo = rs.getString("titulo");
		        String usuario_propietario = rs.getString("usuario_propietario");
		        String usuarios_destinatarios = rs.getString("usuarios_destinatarios");
		        String cuerpo = rs.getString("cuerpo");
		        String fecha_de_publicacion = rs.getString("fecha_de_publicacion");
		        String type = rs.getString("type");
		        estado = rs.getString("estado");
		        String temas = rs.getString("temas");

		        resul = new Hashtable<String,String>();
		        resul.put("identificador", identificador);
		        resul.put("titulo", titulo);
		        resul.put("usuario_propietario", usuario_propietario);
		        resul.put("usuarios_destinatarios", usuarios_destinatarios);
		        resul.put("cuerpo", cuerpo);
		        resul.put("fecha_de_publicacion", fecha_de_publicacion);
		        resul.put("type", type);
		        resul.put("estado", estado);
		        resul.put("temas", temas);

		        System.out.println("identificador: " + resul.get("identificador") + "\n" + 
						   "titulo: " + resul.get("titulo") + "\n" + 
						   "usuario propietario: " + resul.get("usuario_propietario") + "\n" + 
						   "usuarios destinatarios " + resul.get("usuarios_destinatarios") + "\n" +
						   "cuerpo: " + resul.get("cuerpo") + "\n" +
						   "fecha de publicacion: " + resul.get("fecha_de_publicacion") + "\n" +
						   "type: " + resul.get("type") + "\n" +
						   "estado: " + resul.get("estado") + "\n" +
						   "temas: " + resul.get("temas"));
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	public static LinkedList<Anuncio> mostrarTodosAnuncios (InputStream config, InputStream sql) {
		Statement stmt = null; 
		LinkedList<Anuncio> anuncios = new LinkedList<Anuncio>();
		try {
			Connection con=getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(p.getProperty("mostrartodosanuncios"));
		    while (rs.next()) {
		    	int identificador = Integer.parseInt(rs.getString("identificador"));
		    	String titulo = rs.getString("titulo");
		        String usuario_propietario = rs.getString("usuario_propietario");
		        String usuarios_destinatarios = rs.getString("usuarios_destinatarios");
		        String cuerpo = rs.getString("cuerpo");
		        String fecha_de_publicacion = rs.getString("fecha_de_publicacion");
		        String type = rs.getString("type");
		        String estado = rs.getString("estado");
		        String temas = rs.getString("temas");

		        Anuncio anuncio = new Anuncio(identificador,titulo,usuario_propietario,usuarios_destinatarios,cuerpo,fecha_de_publicacion,type,estado,temas);
		        anuncios.add(anuncio);

		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return anuncios;
	} 
	
	public static Anuncio buscarAnuncio(int identificador, InputStream config, InputStream sql) {
		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    String titulo="";
	    String usuario_propietario="";
	    String usuarios_destinatarios="";
	    String cuerpo="";
	    String fecha_de_publicacion="";
	    String type="";
	    String estado="";
	    String temas="";
	    
		try {
			con = getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    rs = stmt.executeQuery(p.getProperty("buscaranuncio") + "'" + identificador + "'");
		    while (rs.next()) {
		    	identificador = rs.getInt("identificador");
		    	titulo = rs.getString("titulo");
		        usuario_propietario = rs.getString("usuario_propietario");
		        usuarios_destinatarios = rs.getString("usuarios_destinatarios");
		        cuerpo = rs.getString("cuerpo");
		        fecha_de_publicacion = rs.getString("fecha_de_publicacion");
		        type = rs.getString("type");
		        estado = rs.getString("estado");
		        temas = rs.getString("temas");
		    }
		    if (rs != null)
		    	rs.close();
		    if (stmt != null)
		    	stmt.close();
		    if (con != null)
		    	con.close();
		    return new Anuncio(identificador,titulo,usuario_propietario,usuarios_destinatarios,cuerpo,fecha_de_publicacion,type,estado,temas);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		    return null;
	}
	
	public static int delete(int identificador, InputStream config, InputStream sql){
		int status=0;
		try{
			Connection con=getConnection(config);
			Properties p = new Properties();
			p.load(sql);
			PreparedStatement ps=con.prepareStatement(p.getProperty("eliminaranuncio") + "'" + identificador + "'");
			ps.setInt(1,identificador);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
	
		return status;
	}
}