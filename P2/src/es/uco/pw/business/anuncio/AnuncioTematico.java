package es.uco.pw.business.anuncio;

/**
 * A concrete announcement that has a topic of interest
 * @author Carlos Ayuso Sanchez
 * @author Javier Gil
 * */

public class AnuncioTematico extends Anuncio {

	private String Intereses;
	/**
	 * constructor vacio
	 */
	public AnuncioTematico() {
		
	}
	/**
	 * getter intereses
	 * @return Intereses
	 */
	public String getInteres() {
		return Intereses;
	}
	/**
	 * setter intereses
	 * @param Intereses
	 */
	public void setIntereses(String Intereses) {
		this.Intereses = Intereses;
	}
	
}