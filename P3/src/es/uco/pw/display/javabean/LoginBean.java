package es.uco.pw.display.javabean;

/**
 * Bean que determina el numero de veces que se ha intentado iniciar sesion sin exito
 */
public class LoginBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private int contador = 0;

	/**
	 * Getter de la variable contador
	 * @return contador
	 */
	public int getContador() {
		return contador;
	}
	/**
	 * Setter de la variable contador
	 * @param contador
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}
}
