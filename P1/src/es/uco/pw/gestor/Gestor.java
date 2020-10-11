package es.uco.pw.gestor;

import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

public class Gestor {
	public static void main(String[] args) throws Throwable {
		
		System.out.println("1. Dar de alta");
		System.out.println("2. Dar de baja");
		System.out.println("3. Consultar datos contacto");
		System.out.println("4. Actualizar datos de contacto");
		System.out.println("5. Buscar por email");
		System.out.println("6. Buscar por nombre");
		System.out.println("7. Buscar por apellido");
		System.out.println("8. Buscar por nombre,apellidos");
		System.out.println("9. Buscar por interes");
		System.out.println("10. Buscar por edad");
		
		Contacto c = Contacto.getInstance();
		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		sc.nextLine();
		
		if(opcion == 1) {
				System.out.println("Introduce nombre, apellidos, fecha de nacimiento, email, interes, edad");
				String nombre = sc.nextLine();
				String apellidos = sc.nextLine();
				String fecha_de_nacimiento = sc.nextLine();
				String email = sc.nextLine();

				Properties p = new Properties();
				p.load(new FileReader("D://Archivos de programa/eclipse2/P1/src/properties"));
				System.out.println("Los intereses validos son: 1. " + p.getProperty("interes1") + "\t2. " + p.getProperty("interes2") + "\t3. " + p.getProperty("interes3") + "\t4. " + p.getProperty("interes4") + "\t5. " + p.getProperty("interes5"));
				
				int interes = sc.nextInt();
				int edad = sc.nextInt();
				sc.nextLine();
				sc.close();
				
				c.alta(nombre, apellidos, fecha_de_nacimiento, email, p.getProperty("interes"+interes), edad);
		}
		else if(opcion == 2) {
				System.out.println("Introduce el email para dar de baja");
				String email1 = sc.next();
				sc.close();
				c.baja(email1);
		}
		else if(opcion == 3) {
				System.out.println("Introduce el email para consultar");
				String email2 = sc.next();
				sc.close();
				c.consulta(email2);
		}
		else if(opcion == 4) {
				System.out.println("Introduce el email para actualizar");
				String email3 = sc.next();
				System.out.println("Introduce una opcion. 1. Nombre\t2. Apellidos\t3. Fecha de nacimiento\t4. Email\t5. Interes\t6. Edad");
				int op = sc.nextInt();
				sc.nextLine();
				
				if(op == 1) {
					System.out.println("Introduce el nombre");
					String nombre = sc.next();
					sc.close();
					c.setNombre(nombre);
					c.actualizacion(email3,nombre,op);
				}else if(op == 2) {
					System.out.println("Introduce los apellidos");
					String apellidos = sc.nextLine();
					sc.close();
					c.setApellidos(apellidos);
					c.actualizacion(email3,apellidos,op);
				}else if(op == 3) {
					System.out.println("Introduce la fecha de nacimiento");
					String fecha_de_nacimiento = sc.next();
					sc.close();
					c.setFecha_de_nacimiento(fecha_de_nacimiento);
					c.actualizacion(email3,fecha_de_nacimiento,op);
				}else if(op == 4) {
					System.out.println("Introduce el email");
					String email = sc.next();
					sc.close();
					c.setEmail(email);
					c.actualizacion(email3,email,op);
				}else if(op == 5) {
					System.out.println("Introduce los intereses");
					
					Properties p = new Properties();
					p.load(new FileReader("D://Archivos de programa/eclipse2/P1/src/properties"));
					System.out.println("Los intereses validos son: 1. " + p.getProperty("interes1") + "\t2. " + p.getProperty("interes2") + "\t3. " + p.getProperty("interes3") + "\t4. " + p.getProperty("interes4") + "\t5. " + p.getProperty("interes5"));
					
					int interes = sc.nextInt();
					sc.close();
					c.setInteres(p.getProperty("interes"+interes));
					c.actualizacion(email3,p.getProperty("interes"+interes),op);
				}else if(op == 6) {
					System.out.println("Introduce la edad");
					int edad = sc.nextInt();
					sc.nextLine();
					sc.close();
					c.setEdad(edad);
					c.actualizacion(email3,String.valueOf(edad),op);
				}else {
					System.out.println("Ha introducido una opcion incorrecta");
				}
				
				sc.close();
		}
		else if(opcion == 5) {
				System.out.println("Introduce el email para buscar");
				String email4 = sc.next();
				sc.close();
				c.consulta(email4);
		}
		else if(opcion == 6) {
				System.out.println("Introduce el nombre para buscar");
				String nombre1 = sc.next();
				sc.close();
				c.buscarNombre(nombre1);
		}
		else if(opcion == 7) {
				System.out.println("Introduce el apellido para buscar");
				String apellido = sc.next();
				sc.close();
				c.buscarApellido(apellido);
		}
		else if(opcion == 8) {
			System.out.println("Introduce nombre,apellidos para buscar. Los apellidos dene estar separados por un espacio");
			String nombre_completo = sc.nextLine();
			sc.close();
			c.buscarNombreApellidos(nombre_completo);
		}
		else if(opcion == 9) {
				System.out.println("Introduce los intereses para buscar");
				Properties p = new Properties();
				p.load(new FileReader("D://Archivos de programa/eclipse2/P1/src/properties"));
				System.out.println("Los intereses validos son: 1. " + p.getProperty("interes1") + "\t2. " + p.getProperty("interes2") + "\t3. " + p.getProperty("interes3") + "\t4. " + p.getProperty("interes4") + "\t5. " + p.getProperty("interes5"));
				int interes1 = sc.nextInt();
				sc.nextLine();
				sc.close();
				
				c.buscarInteres(p.getProperty("interes"+interes1));
		}
		else if(opcion == 10) {
				System.out.println("Introduce la edad para buscar");
				int edad1 = sc.nextInt();
				sc.close();
				c.buscarEdad(edad1);
		}
		else {
				System.out.println("Ha introducido el numero incorrecto");
		}
		sc.close();
	}
}
