package es.uco.pw.tablon;

/**
 * A concrete announcement that can only be seen at a specific time.
 * @author Carlos Ayuso Sanchez
 * @author Javier Gil Moya
 * */

public class AnuncioFlash extends Anuncio {
	
	private String fecha_inicio;
	private String fecha_final;
	
	public void setfecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getfecha_inicio() {
		return fecha_inicio;
	}

	public void setfecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}
	public String getfecha_final() {
		return fecha_final;
	}
	
}