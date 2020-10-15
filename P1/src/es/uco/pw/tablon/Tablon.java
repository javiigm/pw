package es.uco.pw.tablon;

import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.gestor.Contacto;

public class Tablon {
	public static void main(String[] args) throws Throwable {
		
		System.out.println("Identifiquese como usuario");
		Scanner sc = new Scanner(System.in);
		String usuario = sc.nextLine();
		Contacto c = Contacto.getInstance().buscarEmail(usuario);
		if(usuario.equals(c.getEmail())) {
			System.out.println("El usuario es correcto\n");
		}
		
		System.out.println("1. Editar anuncio");
		System.out.println("2. Guardar anuncio");
		System.out.println("3. Publicar anuncio");
		System.out.println("4. Archivar anuncio");
		System.out.println("5. Buscar por fecha");
		System.out.println("6. Buscar por tema de interes");
		System.out.println("7. Buscar por usuario propietario");
		System.out.println("8. Buscar por usuario destinatario");
		
		Anuncio a = new Anuncio();
		Usuario u = new Usuario();
		
		int opcion = sc.nextInt();
		sc.nextLine();
		
		if(opcion == 1) {
			System.out.println("Introduce identificador, titulo, usuario propietario, usuarios destinatarios, cuerpo, fecha de publicacion, type");
			int identificador = sc.nextInt();
			sc.nextLine();
			a.setIdentificador(identificador);
			
			String titulo = sc.nextLine();
			a.setTitulo(titulo);
			
			String usuario_propietario = sc.nextLine();
			a.setUsuario_propietario(usuario_propietario);
			
			String usuarios_destinatarios = sc.nextLine();
			a.setUsuarios_destinatarios(usuarios_destinatarios);
			
			String cuerpo = sc .nextLine();
			a.setCuerpo(cuerpo);
			
			String fecha_de_publicacion = sc.nextLine();
			a.setFecha_de_publicacion(fecha_de_publicacion);
			
			Type type = sc.nextLine();
			a.setType(type);
			
			a.setEstado(Estado.editado);
		/*
			Tema tema = sc.nextLine();
			a.setTema(tema);
		*/
			sc.close();
			
			u.EditarAnuncio(a);
		}
		else if(opcion == 2) {
			u.MostrarAnuncio(Estado.editado);
			int identificador = sc.nextInt();
			sc.nextLine();
			a=u.BuscarAnuncio(identificador);
			u.GuardarAnuncio(a);
		}
		else if(opcion == 3) {
			u.MostrarAnuncio(Estado.editado);
			System.out.println("Introduce el identificador del anuncio");
			int identificador = sc.nextInt();
			sc.nextLine();
			a=u.BuscarAnuncio(identificador);
			u.PublicarAnuncio(a);
		}
		else if(opcion == 4) {
			u.MostrarAnuncio(Estado.publicado);
			System.out.println("Introduce el identificador del anuncio");
			int identificador = sc.nextInt();
			sc.nextLine();
			a=u.BuscarAnuncio(identificador);
			u.ArchivarAnuncio(a);
		}
		else if(opcion == 5) {
			System.out.println("Introduce la fecha a buscar");
			String fecha = sc.next();
			sc.close();
			u.BuscarFecha(fecha);
		}
		else if(opcion == 6) {
			System.out.println("Introduce los intereses para buscar");
			Properties p = new Properties();
			p.load(new FileReader("src/properties"));
			System.out.println("Los intereses validos son: 1. " + p.getProperty("interes1") + "\t2. " + p.getProperty("interes2") + "\t3. " + p.getProperty("interes3") + "\t4. " + p.getProperty("interes4") + "\t5. " + p.getProperty("interes5"));
			int interes1 = sc.nextInt();
			sc.nextLine();
			sc.close();
			
			u.BuscarInteres(p.getProperty("interes"+interes1));
		}
		else if(opcion == 7) {
			System.out.println("Introduce el email del usuario propietario");
			String email1 = sc.next();
			sc.close();
			u.BuscarPropietario(email1);
		}
		else if(opcion == 8) {
			System.out.println("Introduce el email del usuario destinatario");
			String email2 = sc.next();
			sc.close();
			u.BuscarDestinatario(email2);
		}
	}
}
