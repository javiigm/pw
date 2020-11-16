package es.uco.pw.tablon;

import es.uco.pw.vo.Type;

/**
 * The concrete factory that creates announcements for
 * clients in the bulletin board.
 * @author Carlos Ayuso Sanchez
 * @author Javier Gil Moya
 * */


public class ConcreteAnuncioCreator extends AnuncioCreator {
	
	
	@Override
	public AnuncioGeneral createAnuncioGeneral() {
		AnuncioGeneral AnuncioGeneral = new AnuncioGeneral();
		AnuncioGeneral.setUsuarios_destinatarios(null);
		AnuncioGeneral.setType(Type.general);
		return AnuncioGeneral;
	}
	
	@Override
	public AnuncioTematico createAnuncioTematico() {
		AnuncioTematico AnuncioTematico = new AnuncioTematico();
		AnuncioTematico.setType(Type.tematico);
		return AnuncioTematico;
	}

	@Override
	public AnuncioIndividualizado createAnuncioIndividualizado() {
		AnuncioIndividualizado AnuncioIndividualizado = new AnuncioIndividualizado();
		AnuncioIndividualizado.setType(Type.individualizado);
		return AnuncioIndividualizado;		
	}
	
	@Override
	public AnuncioFlash createAnuncioFlash() {
		AnuncioFlash AnuncioFlash = new AnuncioFlash();
		AnuncioFlash.setType(Type.flash);
		return AnuncioFlash;		
	}

}