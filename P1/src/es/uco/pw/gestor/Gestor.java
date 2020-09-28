package es.uco.pw.gestor;

import java.io.IOException;
import java.util.Scanner;

public class Gestor {
	public static void main(String[] args) throws IOException {
		
		System.out.println("1. Dar de alta");
		System.out.println("2. Dar de baja");
		System.out.println("3. Consultar datos contacto");
		System.out.println("4. Actualizar datos de contacto");
		System.out.println("5. Buscar por email");
		System.out.println("6. Buscar por nombre");
		System.out.println("7. Buscar por apellido");
		System.out.println("8. Buscar por interes");
		System.out.println("9. Buscar por edad");
		
		Contacto c = Contacto.getInstance();
		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		sc.nextLine();
		
		
		if(opcion == 1) {
				System.out.println("Introduce nombre, apellidos, fecha de nacimiento, email, intereses, edad");
				String nombre = sc.nextLine();
				String apellidos = sc.nextLine();
				String fecha_de_nacimiento = sc.nextLine();
				String email = sc.nextLine();
				String intereses = sc.nextLine();
				int edad = sc.nextInt();
				sc.nextLine();
				sc.close();
				
				c.alta(nombre, apellidos, fecha_de_nacimiento, email, intereses, edad);
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
				sc.close();
				c.actualizacion(email3);
		}
		else if(opcion == 5) {
				System.out.println("Introduce el email para buscar");
				String email4 = sc.next();
				sc.close();
				c.buscarEmail(email4);
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
				System.out.println("Introduce los intereses para buscar");
				String intereses1 = sc.next();
				sc.close();
				c.buscarIntereses(intereses1);
		}
		else if(opcion == 9) {
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
