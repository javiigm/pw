package es.uco.pw.business.user;

public class User {
	String email = "";
	String password = "";
	String nombre = "";
	String apellidos = "";
	String fecha_de_nacimiento = "";
	String interes = "";
	int edad = 0;
	
	public User (String email, String password, String nombre, String apellidos, String fecha_de_nacimiento, String interes, int edad) {
		this.email = email;
		this.nombre = nombre;
		this.password = password;
		this.apellidos = apellidos;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.interes = interes;
		this.edad = edad;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}
	public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}
	public String getInteres() {
		return nombre;
	}
	public void setInteres(String interes) {
		this.interes = interes;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String toString() {
		return "Email: " + this.getEmail() + "; nombre: " + this.getNombre() + "; apellidos: " + this.getApellidos();
	}
}
