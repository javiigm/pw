package es.uco.pw.display.javabean;

/**
 * Bean que almacena la informacino de sesion de un usuario
 */
public class CustomerBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String emailUser = "";
	
	private String password = "";
	
	private String nombre = "";
	
	private String apellidos = "";
	
	private String fecha_de_nacimiento ="";
	
	private String interes = "";
	
	private int edad = 0;

	/**
	 * Constructor de CustomerBean
	 * @param emailUser
	 * @param password
	 * @param nombre
	 * @param apellidos
	 * @param fecha_de_nacimiento
	 * @param interes
	 * @param edad
	 */
	public CustomerBean(String emailUser, String password, String nombre, String apellidos, String fecha_de_nacimiento, String interes, int edad) {
		super();
		this.emailUser = emailUser;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.interes = interes;
		this.edad = edad;
	}
	
	/**
	 * Getter de la variable emailUser
	 * @return emailUser
	 */
	public String getEmailUser() {
		return emailUser;
	}
	/**
	 * Setter de la variable emailUser
	 * @param emailUser
	 */
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
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
