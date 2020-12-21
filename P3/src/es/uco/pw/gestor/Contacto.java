package es.uco.pw.gestor;


public class Contacto {

	/* Attributes */
	
	private static Contacto instance = null;
	
	private String nombre;
	
	private String apellidos;
	
	private String fecha_de_nacimiento;
	
	private String email;
	
	private String interes;
	
	private int edad;
		
	/* Constructors */
	
	/**
	 * Empty (default) constructor
	 * */
	public Contacto() {
		this.nombre=null;
		this.apellidos=null;
		this.fecha_de_nacimiento=null;
		this.email=null;
		this.interes=null;
		this.edad=0;
	}
	/**
	 * Asigna a cada variable privada su valor al darse de alta
	 * @param nombre
	 * @param apellidos
	 * @param fecha_de_nacimiento
	 * @param email
	 * @param interes
	 * @param edad
	 */
	public Contacto(String nombre, String apellidos, String fecha_de_nacimiento, String email, String interes, int edad) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.fecha_de_nacimiento=fecha_de_nacimiento;
		this.email=email;
		this.interes=interes;
		this.edad=edad;
	}

	/* Getters and setters */
	// Eclipse can generate these methods automatically...
	/**
	 * getter nombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * setter nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * getter apellidos
	 * @return apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * setter apellidos
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * getter fecha de nacimiento
	 * @return fecha_de_nacimiento
	 */
	public String getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}
	/**
	 * setter fecha de nacimiento
	 * @param fecha_de_nacimiento
	 */
	public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}
	/**
	 * getter email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * setter email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * getter interes
	 * @return interes
	 */
	public String getInteres() {
		return interes;
	}
	/**
	 * setter interes
	 * @param interes
	 */
	public void setInteres(String interes) {
		this.interes = interes;
	}
	/**
	 * getter edad
	 * @return edad
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * setter edad
	 * @param edad
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/* Other methods */
	/**
	 * obtiene la instancia de una clase
	 * @return instance
	 */
	public static Contacto getInstance() {
		if(instance == null) {
			instance = new Contacto();
		}
		return instance;
	}
}

