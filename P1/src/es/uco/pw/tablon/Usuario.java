package es.uco.pw.tablon;

import es.uco.pw.gestor.Contacto;

public class Usuario extends Contacto {
	
	//add constructor que se cree de un contacto c
	//add constructor que se cree con datos como si fuera un contacto
	//optional tener un contador de los anuncios que tienes

	public void EditarAnuncio (Anuncio a) {
		if(a.getUsuario_propietario()==getEmail())
		//add cambiar titulo, cuerpo, etc
		a.setType(Type.editado);
	}
	
	public void GuardarAnuncio (Anuncio a) {
		if(a.getUsuario_propietario()==getEmail())
		a.setType(Type.en_espera);
	}
	
	public void PublicarAnuncio (Anuncio a) {
		if(a.getUsuario_propietario()==getEmail())
		a.setType(Type.publicado);
	}

	public void ArchivarAnuncio (Anuncio a) {
		if(a.getUsuario_propietario()==getEmail())
		a.setType(Type.archivado);
	}	
	
	public void BuscarFecha(String fecha) {
		
	}

	public void BuscarInteres(String Interes) {
		
	}

	public void BuscarPropietario(String s) {
		
	}
	
	public void BuscarDestinatario (String s) {
		
	}
}
