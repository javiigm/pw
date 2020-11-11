package es.uco.pw.tablon;

import java.io.FileReader;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.DAO.UserDAO;

public class Tablon {
	public static void main(String[] args) throws Throwable {
		
		System.out.println("Identifiquese como usuario");
		Scanner sc = new Scanner(System.in);
		String usuario = sc.nextLine();
		Hashtable<String,String> resul = UserDAO.consulta(usuario);
		if(resul != null) {
			System.out.println("El usuario es correcto\n");
		}
		else {
			System.out.println("El usuario es incorrecto\n");
			System.exit(0);
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
		AnuncioFlash af = new AnuncioFlash();
		Usuario u = new Usuario();
		
		int opcion = sc.nextInt();
		sc.nextLine();
		
		if(opcion == 1) {
			System.out.println("Introduce identificador, titulo, usuario propietario, cuerpo, fecha de publicacion, tipo");
			int identificador = sc.nextInt();
			sc.nextLine();
			a.setIdentificador(identificador);
			
			String titulo = sc.nextLine();
			a.setTitulo(titulo);
			
			String usuario_propietario = sc.nextLine();
			a.setUsuario_propietario(usuario_propietario);
			
			String cuerpo = sc .nextLine();
			a.setCuerpo(cuerpo);
			
			System.out.println("Introduce la fecha asi: yyyy-mm-dd");
			String fecha_de_publicacion = sc.nextLine();
			a.setFecha_de_publicacion(fecha_de_publicacion);
			
			System.out.println("Los tipos de anuncios validos son: 1. General\t 2. Tematico\t3. Individualizado\t4. Flash");
			int tipo = sc.nextInt();
			sc.nextLine();
			switch(tipo) {
				case 1: 
					a.setType(Type.general);
					break;
				case 2: 
					a.setType(Type.tematico);
					break;
				case 3: 
					a.setType(Type.individualizado);
					break;
				case 4:
					a.setType(Type.flash);
					break;
				default:
					System.out.println("Ha introducido un numero incorrecto para la seleccion");
					System.exit(0);
			}
			
			
			a.setEstado(Estado.editado);
			
			if(a.getType().equals(Type.tematico)) {
				Properties p = new Properties();
				p.load(new FileReader("src/properties"));
				System.out.println("Los temas de intereses validos son: 1. " + p.getProperty("interes1") + "\t2. " + p.getProperty("interes2") + "\t3. " + p.getProperty("interes3") + "\t4. " + p.getProperty("interes4") + "\t5. " + p.getProperty("interes5"));
				
				int tema = sc.nextInt();
				a.setTema(p.getProperty("interes"+tema));
				
				af.setFecha_ini("");
				af.setFecha_fin("");
				a.setUsuarios_destinatarios("");
			}
			else {
				a.setTema("");
				if(a.getType().equals(Type.flash)) {
					System.out.println("Introduce la fecha de inicio y la fecha de fin de publicacion");
					String fecha_ini = sc.nextLine();
					af.setFecha_ini(fecha_ini);
					String fecha_fin = sc.nextLine();
					af.setFecha_fin(fecha_fin);
					
					a.setUsuarios_destinatarios("");
				}
				else {
					if(a.getType().equals(Type.individualizado)) {
						System.out.println("Introduce el usuario destinatario");
						String usuarios_destinatarios = sc.nextLine();
						a.setUsuarios_destinatarios(usuarios_destinatarios);
						
						af.setFecha_ini("");
						af.setFecha_fin("");
					}
					else {
						a.setUsuarios_destinatarios("");
						af.setFecha_ini("");
						af.setFecha_fin("");
					}
				}
			}
			
		
			sc.close();
			
			u.EditarAnuncio(a);
		}
		else if(opcion == 2) {
			u.MostrarAnuncio(Estado.editado);
			int identificador = sc.nextInt();
			sc.nextLine();
			a=u.BuscarAnuncio(identificador);
			af = (AnuncioFlash) a;
			af.CompararFechas_ini(a, af.getFecha_ini(), af.getFecha_fin());
			u.GuardarAnuncio(a);
		}
		else if(opcion == 3) {
			u.MostrarAnuncio(Estado.editado);
			System.out.println("Introduce el identificador del anuncio");
			int identificador = sc.nextInt();
			sc.nextLine();
			a=u.BuscarAnuncio(identificador);
			af = (AnuncioFlash) a;
			af.CompararFechas_ini(a, af.getFecha_ini(), af.getFecha_fin());
			u.PublicarAnuncio(a);
		}
		else if(opcion == 4) {
			u.MostrarAnuncio(Estado.publicado);
			System.out.println("Introduce el identificador del anuncio");
			int identificador = sc.nextInt();
			sc.nextLine();
			a=u.BuscarAnuncio(identificador);
			af = (AnuncioFlash) a;
			af.CompararFechas_ini(a, af.getFecha_ini(), af.getFecha_fin());
			u.ArchivarAnuncio(a);
		}
		else if(opcion == 5) {
			System.out.println("Introduce la fecha a buscar asi: yyyy-mm-dd");
			String fecha = sc.nextLine();
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
