package es.uco.pw.tablon;

/**
 * A concrete announcement that has a topic of interest
 * @author Carlos Ayuso Sanchez
 * @author Javier Gil
 * */

public class AnuncioTematico extends Anuncio {
	
	public String[] Intereses = {"tenis","futbol","programacion","videojuegos","musica"};

	public AnuncioTematico() {
		
	}

	public String[] getIntereses() {
		return Intereses;
	}

	public void setIntereses(String[] Intereses) {
		this.Intereses = Intereses;
	}
	
}