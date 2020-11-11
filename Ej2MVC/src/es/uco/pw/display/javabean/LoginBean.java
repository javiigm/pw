package es.uco.pw.display.javabean;

public class LoginBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private int contador = 0;

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
}
