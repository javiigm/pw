package es.uco.pw.tablon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import es.uco.pw.gestor.Contacto;

public class Usuario extends Contacto {
	
	//add constructor que se cree de un contacto c
	//add constructor que se cree con datos como si fuera un contacto
	//optional tener un contador de los anuncios que tienes

	public void EditarAnuncio (Anuncio a) {
		if(a.getUsuario_propietario()==getEmail())
		//add cambiar titulo, cuerpo, etc
		a.setType(Estado.editado);
	}
	
	public void GuardarAnuncio (Anuncio a) {
		if(a.getUsuario_propietario()==getEmail())
		a.setType(Estado.en_espera);
	}
	
	public void PublicarAnuncio (Anuncio a) {
		if(a.getUsuario_propietario()==getEmail())
		a.setType(Estado.publicado);
	}

	public void ArchivarAnuncio (Anuncio a) {
		if(a.getUsuario_propietario()==getEmail())
		a.setType(Estado.archivado);
	}	
	
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
                System.out.printf("\nfecha: " + linea[5]);
                System.out.println("\n");	            
                }
        }
		reader.close();
		if(!existe_fecha) {
			System.out.println("No se ha encontrado ningun anuncio con esa fecha");
			System.exit(0);
		}
	}

	public void BuscarInteres(String Interes) {
		//se tiene que definir antes el constructor de intereses
		//seria como un buscador pero habria que tener en cuenta que type.equals(tematico)
	}

	public void BuscarDestinatario(String destinatario) throws IOException  {
		BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroAnuncios())));
		String line;
		Boolean existe_destinatario=false;
		while((line = reader.readLine()) != null) {
			String[] linea=line.split(",");
            if(destinatario.equals(linea[0])){
                existe_destinatario=true;
            	System.out.printf("identificador: " + linea[0]);
                System.out.printf("\ntitulo: " + linea[1]);
                System.out.printf("\npropietario: " + linea[2]);
                System.out.printf("\ndestinatarios: " + linea[3]);
                System.out.printf("\ncuerpo: " + linea[4]);
                System.out.printf("\nfecha: " + linea[5]);
                System.out.println("\n");	            
                }
        }
		reader.close();
		if(!existe_destinatario) {
			System.out.println("No se ha encontrado ningun anuncio con ese destinatario");
			System.exit(0);
		}
	}
	
	
	//seria como buscar por usuario propietario
	public void BuscarAnuncio (String identificador) throws IOException {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroAnuncios())));
			String line;
			Boolean existe_id=false;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(identificador.equals(linea[0])){
	                existe_id=true;
	            	System.out.printf("identificador: " + linea[0]);
	                System.out.printf("\ntitulo: " + linea[1]);
	                System.out.printf("\npropietario: " + linea[2]);
	                System.out.printf("\ndestinatarios: " + linea[3]);
	                System.out.printf("\ncuerpo: " + linea[4]);
	                System.out.printf("\nfecha: " + linea[5]);
	                System.out.println("\n");	            
	                }
	        }
			reader.close();
			if(!existe_id) {
				System.out.println("No se ha encontrado ningun anuncio por ese email");
				System.exit(0);
			}
	}
	
	public String ficheroAnuncios() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader("D:\\Archivos de programa\\eclipse2\\P1\\src\\properties"));
		return p.getProperty("path1");
	}
}
