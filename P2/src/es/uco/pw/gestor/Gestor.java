package es.uco.pw.gestor;

import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.data.dao.UserDAO;

public class Gestor {
	public static void main(String[] args) throws Throwable {
		
		System.out.println("1. Dar de alta");
		System.out.println("2. Dar de baja");
		System.out.println("3. Consultar datos contacto");
		System.out.println("4. Actualizar datos de contacto");
		System.out.println("5. Buscar por email");
		System.out.println("6. Buscar por nombre");
		System.out.println("7. Buscar por apellido");
		System.out.println("8. Buscar por nombre y apellidos");
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
				p.load(new FileReader("src/properties"));
				System.out.println("Los intereses validos son: 1. " + p.getProperty("interes1") + "\t2. " + p.getProperty("interes2") + "\t3. " + p.getProperty("interes3") + "\t4. " + p.getProperty("interes4") + "\t5. " + p.getProperty("interes5"));
				
				int interes = sc.nextInt();
				sc.nextLine();
				int edad = sc.nextInt();
				sc.close();
				
				if(UserDAO.save(nombre, apellidos, fecha_de_nacimiento, email, p.getProperty("interes"+interes), edad) != 0)
					System.out.println("El usuario se ha dado de alta correctamente");
				else 
					System.out.println("No se ha podido dar de alta al usuario");
		}
		else if(opcion == 2) {
				System.out.println("Introduce el email para dar de baja");
				String email1 = sc.nextLine();
				sc.close();
				
				if(UserDAO.delete(email1) != 0)
					System.out.println("El usuario se ha dado de baja correctamente");
				else 
					System.out.println("No se ha podido dar de baja al usuario");
		}
		else if(opcion == 3) {
				System.out.println("Introduce el email para consultar");
				String email2 = sc.nextLine();
				sc.close();
				java.util.Hashtable<String,String> resul = UserDAO.consulta(email2);
				if (resul==null)
					System.out.println("Se consulta el usuario con email = NULO");
				
		}
		else if(opcion == 4) {
				System.out.println("Introduce el email para actualizar");
				String email3 = sc.nextLine();
				System.out.println("Introduce una opcion. 1. Nombre\t2. Apellidos\t3. Fecha de nacimiento\t4. Email\t5. Interes\t6. Edad");
				int op = sc.nextInt();
				sc.nextLine();
				
				if(op == 1) {
					System.out.println("Introduce el nombre");
					String nombre = sc.nextLine();
					sc.close();
					c.setNombre(nombre);
					if(UserDAO.update(email3,nombre,op) != 0)
						System.out.println("El usuario se ha actualizado correctamente");
					else 
						System.out.println("No se ha podido actualizar al usuario");
					
				}else if(op == 2) {
					System.out.println("Introduce los apellidos");
					String apellidos = sc.nextLine();
					sc.close();
					c.setApellidos(apellidos);
					if(UserDAO.update(email3,apellidos,op) != 0)
						System.out.println("El usuario se ha actualizado correctamente");
					else 
						System.out.println("No se ha podido actualizar al usuario");
					
				}else if(op == 3) {
					System.out.println("Introduce la fecha de nacimiento. yyyy-mm-dd");
					String fecha_de_nacimiento = sc.nextLine();
					sc.close();
					c.setFecha_de_nacimiento(fecha_de_nacimiento);
					if(UserDAO.update(email3,fecha_de_nacimiento,op) != 0)
						System.out.println("El usuario se ha actualizado correctamente");
					else 
						System.out.println("No se ha podido actualizar al usuario");
					
				}else if(op == 4) {
					System.out.println("Introduce el email");
					String email = sc.nextLine();
					sc.close();
					c.setEmail(email);
					if(UserDAO.update(email3,email,op) != 0)
						System.out.println("El usuario se ha actualizado correctamente");
					else 
						System.out.println("No se ha podido actualizar al usuario");
					
				}else if(op == 5) {
					System.out.println("Introduce los intereses");
					
					Properties p = new Properties();
					p.load(new FileReader("src/properties"));
					System.out.println("Los intereses validos son: 1. " + p.getProperty("interes1") + "\t2. " + p.getProperty("interes2") + "\t3. " + p.getProperty("interes3") + "\t4. " + p.getProperty("interes4") + "\t5. " + p.getProperty("interes5"));
					
					int interes = sc.nextInt();
					sc.nextLine();
					sc.close();
					c.setInteres(p.getProperty("interes"+interes));
					if(UserDAO.update(email3,p.getProperty("interes"+interes),op) != 0)
						System.out.println("El usuario se ha actualizado correctamente");
					else 
						System.out.println("No se ha podido actualizar al usuario");
					
				}else if(op == 6) {
					System.out.println("Introduce la edad");
					int edad = sc.nextInt();
					sc.nextLine();
					sc.close();
					c.setEdad(edad);
					if(UserDAO.update(email3,String.valueOf(edad),op) != 0)
						System.out.println("El usuario se ha actualizado correctamente");
					else 
						System.out.println("No se ha podido actualizar al usuario");
					
				}else {
					System.out.println("Ha introducido una opcion incorrecta");
				}
				
				sc.close();
		}
		else if(opcion == 5) {
				System.out.println("Introduce el email para buscar");
				String email4 = sc.nextLine();
				sc.close();
				
				java.util.Hashtable<String,String> resul = UserDAO.consulta(email4);
				if (resul==null)
					System.out.println("Se consulta el usuario con email = NULO");
		}
		else if(opcion == 6) {
				System.out.println("Introduce el nombre para buscar");
				String nombre1 = sc.nextLine();
				sc.close();
				java.util.Hashtable<String,String> resul = UserDAO.buscarNombre(nombre1);
				if (resul==null)
					System.out.println("Se consulta el usuario con nombre = NULO");
			
		}
		else if(opcion == 7) {
				System.out.println("Introduce los apellidos para buscar");
				String apellidos2 = sc.next();
				sc.close();

				java.util.Hashtable<String,String> resul = UserDAO.buscarApellidos(apellidos2);
				if (resul==null)
					System.out.println("Se consulta el usuario con apellidos = NULO");
		}
		else if(opcion == 8) {
				System.out.println("Introduce nombre y apellidos para buscar, cada uno en una linea correspondiente. "
						+ "\nLos apellidos deben estar separados por un espacio");
				String nombre2 = sc.nextLine();
				String apellidos2 =sc.nextLine();
				sc.close();
				java.util.Hashtable<String,String> resul = UserDAO.buscarNombreApellidos(nombre2,apellidos2);
				if (resul==null)
					System.out.println("Se consulta el usuario con (nombre o apellidos) = NULO");
		}
		else if(opcion == 9) {
				System.out.println("Introduce los intereses para buscar");
				Properties p = new Properties();
				p.load(new FileReader("src/properties"));
				System.out.println("Los intereses validos son: 1. " + p.getProperty("interes1") + "\t2. " + p.getProperty("interes2") + "\t3. " + p.getProperty("interes3") + "\t4. " + p.getProperty("interes4") + "\t5. " + p.getProperty("interes5"));
				int interes1 = sc.nextInt();
				sc.nextLine();
				sc.close();
				
				java.util.Hashtable<String,String> resul = UserDAO.buscarInteres(p.getProperty("interes"+interes1));
				if (resul==null)
					System.out.println("Se consulta el usuario con interes = NULO");
		}
		else if(opcion == 10) {
				System.out.println("Introduce la edad para buscar");
				String edad1 = sc.nextLine();
				sc.close();
				
				java.util.Hashtable<String,String> resul = UserDAO.buscarEdad(edad1);
				if (resul==null)
					System.out.println("Se consulta el usuario con edad = NULO");
		}
		else {
				System.out.println("Ha introducido el numero incorrecto");
		}
		sc.close();
	}
}
