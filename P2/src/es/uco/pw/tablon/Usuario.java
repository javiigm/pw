package es.uco.pw.tablon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import es.uco.pw.gestor.Contacto;
import es.uco.pw.vo.Estado;
import es.uco.pw.vo.Type;

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
		public void EditarAnuncio (Anuncio a) throws FileNotFoundException, IOException {
			if(a.getUsuario_propietario()==getEmail()) {
				a.setEstado(Estado.editado);
				Writer output = new BufferedWriter(new FileWriter(ficheroAnuncios(), true));
		        output.append("\n" + a.getIdentificador() + "," + a.getTitulo() + "," + a.getUsuario_propietario() + "," + a.getUsuarios_destinatarios() + "," + a.getCuerpo() + "," + a.getFecha_de_publicacion() + "," + a.getType() + "," + a.getEstado() + "," + a.getTema());
		            
		        output.close();
		        System.out.println("Anuncio editado correctamente correctamente");
			}
		}
		/**
		 * guarda el anuncio
		 * @param a
		 * @throws IOException
		 */
		public void GuardarAnuncio (Anuncio a) throws IOException {
			if(a.getUsuario_propietario()==getEmail()) {
				a.setEstado(Estado.en_espera);
				Writer output = new BufferedWriter(new FileWriter(ficheroAnuncios(), true));
		        output.append("\n" + a.getIdentificador() + "," + a.getTitulo() + "," + a.getUsuario_propietario() + "," + a.getUsuarios_destinatarios() + "," + a.getCuerpo() + "," + a.getFecha_de_publicacion() + "," + a.getType() + "," + a.getEstado() + "," + a.getTema());
		            
		        output.close();
		        System.out.println("Anuncio editado correctamente correctamente");
			}
		}
		/**
		 * publicar anuncio
		 * @param a
		 */
		public void PublicarAnuncio (Anuncio a) {
			if(a.getUsuario_propietario()==getEmail())
			a.setEstado(Estado.publicado);
		}
		/**
		 * archivar anuncio
		 * @param a
		 */
		public void ArchivarAnuncio (Anuncio a) {
			if(a.getUsuario_propietario()==getEmail())
			a.setEstado(Estado.archivado);
		}	
		/**
		 * buscar fecha
		 * @param fecha
		 * @throws IOException
		 */
		public void BuscarFecha(String fecha) throws IOException {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroAnuncios())));
			String line;
			Boolean existe_fecha=false;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(fecha.equals(linea[5])){
	                existe_fecha=true;
	            	System.out.printf("identificador: " + linea[0]);
	                System.out.printf("\ntitulo: " + linea[1]);
	                System.out.printf("\npropietario: " + linea[2]);
	                System.out.printf("\ndestinatarios: " + linea[3]);
	                System.out.printf("\ncuerpo: " + linea[4]);
	                System.out.printf("\nfecha de publicacion: " + linea[5]);
	                System.out.printf("\ntype: " + linea[6]);
	                System.out.printf("\nestado: " + linea[7]);
	                System.out.printf("\ntemas: " + linea[8]);
	                System.out.println("\n");	            
	                }
	        }
			reader.close();
			if(!existe_fecha) {
				System.out.println("No se ha encontrado ningun anuncio con esa fecha");
				System.exit(0);
			}
		}
		/**
		 * buscar interes
		 * @param Interes
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public void BuscarInteres(String Interes) throws FileNotFoundException, IOException {
			//se tiene que definir antes el constructor de intereses
			//seria como un buscador pero habria que tener en cuenta que type.equals(tematico)
			Boolean existe_interes = false;
			Properties p = new Properties();
			p.load(new FileReader("src/properties"));
			for(int i=1; i<6; i++)
				if(Interes.equals(p.getProperty("interes"+i)))
					existe_interes=true;
			if(existe_interes) {
				BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroAnuncios())));
				String line;
				while((line = reader.readLine()) != null) {
					String[] linea=line.split(",");
		            if(Interes.equals(linea[5])){
		            	System.out.printf("identificador: " + linea[0]);
		                System.out.printf("\ntitulo: " + linea[1]);
		                System.out.printf("\npropietario: " + linea[2]);
		                System.out.printf("\ndestinatarios: " + linea[3]);
		                System.out.printf("\ncuerpo: " + linea[4]);
		                System.out.printf("\nfecha de publicacion: " + linea[5]);
		                System.out.printf("\ntype: " + linea[6]);
		                System.out.printf("\nestado: " + linea[7]);
		                System.out.printf("\ntemas: " + linea[8]);
		                System.out.println("\n");
		            }
				}
				reader.close();
			}else {
				System.out.println("No se ha encontrado el interes buscado");
				System.exit(0);
			}
		}
		/**
		 * buscar propietario
		 * @param propietario
		 * @throws IOException
		 */
		public void BuscarPropietario(String propietario) throws IOException  {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroAnuncios())));
			String line;
			Boolean existe_propietario=false;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(propietario.equals(linea[2])){
	                existe_propietario=true;
	            	System.out.printf("identificador: " + linea[0]);
	                System.out.printf("\ntitulo: " + linea[1]);
	                System.out.printf("\npropietario: " + linea[2]);
	                System.out.printf("\ndestinatarios: " + linea[3]);
	                System.out.printf("\ncuerpo: " + linea[4]);
	                System.out.printf("\nfecha de publicacion: " + linea[5]);
	                System.out.printf("\ntype: " + linea[6]);
	                System.out.printf("\nestado: " + linea[7]);
	                System.out.printf("\ntemas: " + linea[8]);
	                System.out.println("\n");	            
	                }
	        }
			reader.close();
			if(!existe_propietario) {
				System.out.println("No se ha encontrado ningun anuncio con ese destinatario");
				System.exit(0);
			}
		}
		/**
		 * buscar destinatario
		 * @param destinatario
		 * @throws IOException
		 */
		public void BuscarDestinatario(String destinatario) throws IOException  {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroAnuncios())));
			String line;
			Boolean existe_destinatario=false;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(destinatario.equals(linea[3])){
	                existe_destinatario=true;
	            	System.out.printf("identificador: " + linea[0]);
	                System.out.printf("\ntitulo: " + linea[1]);
	                System.out.printf("\npropietario: " + linea[2]);
	                System.out.printf("\ndestinatarios: " + linea[3]);
	                System.out.printf("\ncuerpo: " + linea[4]);
	                System.out.printf("\nfecha de publicacion: " + linea[5]);
	                System.out.printf("\ntype: " + linea[6]);
	                System.out.printf("\nestado: " + linea[7]);
	                System.out.printf("\ntemas: " + linea[8]);
	                System.out.println("\n");	            
	                }
	        }
			reader.close();
			if(!existe_destinatario) {
				System.out.println("No se ha encontrado ningun anuncio con ese destinatario");
				System.exit(0);
			}
		}
	/**
	 * mostrar anuncio
	 * @param estado
	 * @throws IOException
	 */
	public void MostrarAnuncio (Estado estado) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroAnuncios())));
		String line;
		Boolean existe_estado=false;
		while((line = reader.readLine()) != null) {
			String[] linea=line.split(",");
            if(estado.equals(Estado.valueOf(linea[7]))){
                existe_estado=true;
            	System.out.printf("identificador: " + linea[0]);
                System.out.printf("\ntitulo: " + linea[1]);
                System.out.printf("\npropietario: " + linea[2]);
                System.out.printf("\ndestinatarios: " + linea[3]);
                System.out.printf("\ncuerpo: " + linea[4]);
                System.out.printf("\nfecha de publicacion: " + linea[5]);
                System.out.printf("\ntype: " + linea[6]);
                System.out.printf("\nestado: " + linea[7]);
                System.out.printf("\ntemas: " + linea[8]);
                System.out.println("\n");	            
                }
        }
		reader.close();
		if(!existe_estado) {
			System.out.println("No se ha encontrado ningun contacto por ese email");
			System.exit(0);
		}
}
	/**
	 * buscar anuncio
	 * @param identificador
	 * @return Anuncio
	 * @throws IOException
	 */
	public Anuncio BuscarAnuncio (int identificador) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroAnuncios())));
		Anuncio a = new Anuncio();
		String line;
		Boolean existe_identificador=false;
		while((line = reader.readLine()) != null) {
			String[] linea=line.split(",");
            if(identificador == Integer.valueOf(linea[0])){
                existe_identificador=true;
            	a.setIdentificador(Integer.valueOf(linea[0]));
                a.setTitulo(linea[1]);
                a.setUsuario_propietario(linea[2]);
                a.setUsuarios_destinatarios(linea[3]);
                a.setCuerpo(linea[4]);
                a.setFecha_de_publicacion(linea[5]);
                a.setType(Type.valueOf(linea[6]));
                a.setEstado(Estado.valueOf(linea[7]));
                a.setTema(linea[8]);
                }
        }
		reader.close();
		if(!existe_identificador) {
			System.out.println("No se ha encontrado ningun anuncio por ese email");
			System.exit(0);
		}
		return a;
	}
	/**
	 * obtiene el fichero de anuncios
	 * @return fichero
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String ficheroAnuncios() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader("src/properties"));
		return p.getProperty("path2");
	}
}
