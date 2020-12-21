package es.uco.pw.business.user;

public class User {
	String email = "";
	String password = "";
	String nombre = "";
	String apellidos = "";
	String fecha_de_nacimiento = "";
	String interes = "";
	int edad = 0;
	
	/**
	 * Constructor de la clase User, establece todas las claves privadas
	 * @param nombre
	 * @param apellidos
	 * @param fecha_de_nacimiento
	 * @param email
	 * @param password
	 * @param interes
	 * @param edad
	 */
	public User (String nombre, String apellidos, String fecha_de_nacimiento, String email, String password,  String interes, int edad) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.email = email;
		this.password = password;
		this.interes = interes;
		this.edad = edad;
	}
	/**
	 * Constructor vacio de la clase User
	 */
	public User() {
		
	}
	/**
	 * Getter de la variable nombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setter de la variable nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Getter de la variable apellidos
	 * @return apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * Setter de la variable apellidos
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * Getter de la variable fecha_de_nacimiento
	 * @return fecha_de_nacimiento
	 */
	public String getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}
	/**
	 * Setter de la variable fecha_de_nacimiento
	 * @param fecha_de_nacimiento
	 */
	public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}
	/**
	 * Getter de la variable email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter de la variable email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Getter de la variable password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Setter de la variable password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Getter de la variable interes
	 * @return interes
	 */
	public String getInteres() {
		return interes;
	}
	/**
	 * Setter de la variable interes
	 * @param interes
	 */
	public void setInteres(String interes) {
		this.interes = interes;
	}
	/**
	 * Getter de la variable edad
	 * @return edad
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * Setter de la variable edad
	 * @param edad
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
}
