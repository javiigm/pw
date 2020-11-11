package es.uco.pw.tablon;

/**
 * The abstract factory to create announcements
 * @author Carlos Ayuso Sanchez
 * @author Javier Gil
 * */


public abstract class AnuncioCreator {

	/** 
	 * Create an announcement that 
	 * @return 
	 *  */
	public abstract AnuncioGeneral createAnuncioGeneral();
	/** 
	 * Create an announcement that 
	 * @return 
	 *  */
	public abstract AnuncioTematico createAnuncioTematico();
	/** 
	 * Create an announcement that 
	 * @return 
	 *  */
	public abstract AnuncioIndividualizado createAnuncioIndividualizado();
	/** 
	 * Create an announcement that 
	 * @return 
	 *  */
	public abstract AnuncioFlash createAnuncioFlash();

}
