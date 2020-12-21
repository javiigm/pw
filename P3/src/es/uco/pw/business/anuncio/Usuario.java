package es.uco.pw.business.anuncio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import es.uco.pw.data.dao.AnuncioDAO;
import es.uco.pw.gestor.Contacto;
import es.uco.pw.to.Estado;

public class Usuario extends Contacto {
	
	    //add constructor que se cree de un contacto c
		//add constructor que se cree con datos como si fuera un contacto
		//optional tener un contador de los anuncios que tienes
		/**
		 * edita el anuncio
		 * @param a
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public void EditarAnuncio (Anuncio a, String usuario,InputStream config, InputStream sql) throws FileNotFoundException, IOException {
			if(a.getUsuario_propietario().equals(usuario)) {
				a.setEstado(Estado.editado);
		   		
				AnuncioDAO.save(a,config,sql);
		        System.out.println("Anuncio editado correctamente");
			}
			else {
				System.out.println("El usuario propietario no es la persona que ha iniciado sesion");
			}
		}
		/**
		 * guarda el anuncio
		 * @param a
		 * @throws IOException
		 */
		public void GuardarAnuncio (Anuncio a, String usuario,InputStream config, InputStream sql) throws IOException {
			if(a.getUsuario_propietario().equals(usuario)) {
				a.setEstado(Estado.en_espera);
				AnuncioDAO.update(a.getIdentificador(), a.getEstado().toString(), 1,config,sql);
		        System.out.println("Anuncio guardado correctamente");
			}
			else {
				System.out.println("El usuario propietario no es la persona que ha iniciado sesion");
			}
		}
		/**
		 * publicar anuncio
		 * @param a
		 */
		public void PublicarAnuncio (Anuncio a, String usuario,InputStream config, InputStream sql) {
			if(a.getUsuario_propietario().equals(usuario)) {
				a.setEstado(Estado.publicado);
				AnuncioDAO.update(a.getIdentificador(), a.getEstado().toString(), 2,config,sql);
		        System.out.println("Anuncio publicado correctamente");
			}
			else {
				System.out.println("El usuario propietario no es la persona que ha iniciado sesion");
			}
		}
		/**
		 * archivar anuncio
		 * @param a
		 */
		public void ArchivarAnuncio (Anuncio a, String usuario,InputStream config, InputStream sql) {
			if(a.getUsuario_propietario().equals(usuario)) {
				a.setEstado(Estado.archivado);
				AnuncioDAO.update(a.getIdentificador(), a.getEstado().toString(), 3,config,sql);
		        System.out.println("Anuncio archivado correctamente");
			}
			else {
				System.out.println("El usuario propietario no es la persona que ha iniciado sesion");
			}
		}	
		/**
		 * buscar fecha
		 * @param fecha
		 * @throws IOException
		 */
		public void BuscarFecha(String fecha,InputStream config, InputStream sql) throws IOException {
			Hashtable<String,String> resul = AnuncioDAO.buscarFecha(fecha,config,sql);
			if (resul==null)
				System.out.println("No se ha encontrado ningun anuncio por esa fecha");
		}
		/**
		 * buscar interes
		 * @param Interes
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public void BuscarInteres(String Interes,InputStream config, InputStream sql) throws FileNotFoundException, IOException {
			Hashtable<String,String> resul = AnuncioDAO.buscarTemas(Interes,config,sql);
			if (resul==null)
				System.out.println("No se ha encontrado ningun anuncio por ese interes o tema");
			
		}
		/**
		 * buscar propietario
		 * @param propietario
		 * @throws IOException
		 */
		public void BuscarPropietario(String propietario,InputStream config, InputStream sql) throws IOException  {
			Hashtable<String,String> resul = AnuncioDAO.buscarUsuarioPropietario(propietario,config,sql);
			if (resul==null)
				System.out.println("No se ha encontrado ningun anuncio por ese propietario");
		}
		/**
		 * buscar destinatario
		 * @param destinatario
		 * @throws IOException
		 */
		public void BuscarDestinatario(String destinatario,InputStream config, InputStream sql) throws IOException  {
			Hashtable<String,String> resul = AnuncioDAO.buscarUsuarioDestinatario(destinatario,config,sql);
			if (resul==null)
				System.out.println("No se ha encontrado ningun anuncio por ese destinatario");
		}
	/**
	 * mostrar anuncio
	 * @param estado
	 * @throws IOException
	 */
	public void MostrarAnuncio (Estado estado,InputStream config, InputStream sql) throws IOException {
		Hashtable<String,String> resul = AnuncioDAO.mostrarAnuncio(estado.toString(),config,sql);
		if (resul==null)
			System.out.println("No se ha encontrado ningun anuncio por ese estado");
	}
	/**
	 * buscar anuncio
	 * @param identificador
	 * @return Anuncio
	 * @throws IOException
	 */
	public Anuncio BuscarAnuncio (int identificador,InputStream config, InputStream sql) throws IOException {
		Anuncio a = AnuncioDAO.buscarAnuncio(identificador,config,sql);
		if ( a != null) {
			return a;
		}
		return null;
	}
}
