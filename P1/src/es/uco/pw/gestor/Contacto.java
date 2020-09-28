package es.uco.pw.gestor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class Contacto {

	/* Attributes */
	
	private static Contacto instance = null;
	
	private String nombre;
	
	private String apellidos;
	
	private String fecha_de_nacimiento;
	
	private String email;
	
	private String intereses;
	
	private int edad;
		
	/* Constructors */
	
	/**
	 * Empty (default) constructor
	 * */
	public Contacto() {
		this.nombre=null;
		this.apellidos=null;
		this.fecha_de_nacimiento=null;
		this.email=null;
		this.intereses=null;
		this.edad=0;
	}
	
	public Contacto(String nombre, String apellidos, String fecha_de_nacimiento, String email, String intereses, int edad) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.fecha_de_nacimiento=fecha_de_nacimiento;
		this.email=email;
		this.intereses=intereses;
		this.edad=edad;
	}

	/* Getters and setters */
	// Eclipse can generate these methods automatically...

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntereses() {
		return intereses;
	}

	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	/* Other methods */

	public static Contacto getInstance() {
		if(instance == null) {
			instance = new Contacto();
		}
		return instance;
	}
	
	
	
	public Contacto alta(String nombre, String apellidos, String fecha_de_nacimiento, String email, String intereses, int edad) throws IOException {
		if(instance != null) {
			instance = new Contacto(nombre, apellidos, fecha_de_nacimiento, email, intereses, edad);
	        Writer output = new BufferedWriter(new FileWriter("D:\\Archivos de programa\\eclipse2\\P1\\src\\es\\uco\\pw\\contactos.txt", true));
	        String[] ap=apellidos.split(" ");
	        output.append("\n" + nombre + "," + ap[0] + "," + ap[1] + "," + fecha_de_nacimiento + "," + email + "," + intereses + "," + String.valueOf(edad));
	            
	        output.close();
	        System.out.println("Contacto dado de alta correctamente");
		}
		return null;
	}
	
	public Contacto baja(String email) {
		if(instance != null) {
			
		        String linea="";
		    	BufferedReader br = null;
		        BufferedWriter bw = null;
		    	ArrayList<String> lineasAcopiar = new ArrayList<String>();
		    	try{
		    		br = new BufferedReader(new FileReader("D:\\Archivos de programa\\eclipse2\\P1\\src\\es\\uco\\pw\\contactos.txt"));
		            while((linea = br.readLine()) != null){
		                String[] lineaComas = linea.split(",");
		                if(!email.equals(lineaComas[4])) {
		                	lineasAcopiar.add(linea);
		                }
		            }
		            br.close();
		            //RECORREMOS EL VECTOR Y GUARDAMOS LA LINEAS EN EL FICHERO
		            bw = new BufferedWriter(new FileWriter("D:\\Archivos de programa\\eclipse2\\P1\\src\\es\\uco\\pw\\contactos.txt"));
		            for(int l=0;l<lineasAcopiar.size();l++){
		                linea = (String)lineasAcopiar.get(l);
		                bw.write(linea);
		                if(l!=lineasAcopiar.size()-1)
		                	bw.newLine();
		            }
		            bw.close();
		            System.out.println("El contacto con email " + email + " ha sido borrado");
		
		        }catch(Exception e){System.out.println("Error: " + e);}
		    	
		}
		return null;
	}
	
	public Contacto consulta(String email) throws IOException {
		if(instance != null) {
			Contacto c = buscarEmail(email);
	        System.out.printf("nombre: " + c.getNombre());
	        System.out.printf("\napellidos: " + c.getApellidos());
	        System.out.printf("\nfecha de nacimiento: " + c.getFecha_de_nacimiento());
	        System.out.printf("\nemail: " + email);
	        System.out.printf("\nintereses: " + c.getIntereses());
	        System.out.printf("\nedad: " + c.getEdad());
		}
		return null;
	}
	
	public Contacto actualizacion(String email) throws IOException {
		if(instance != null) {
			Contacto ct = buscarEmail(email);
			
			System.out.println("¿Que desea actualizar?");
			System.out.println("1. Nombre\t2. Apellidos\t3. Fecha de nacimiento\t4. Email\t5. Intereses\t6. Edad");
			Scanner sc = new Scanner(System.in);
			int opcion = Integer.parseInt(sc.nextLine());
			
			if(opcion == 1) {
				System.out.println("Introduce el nombre");
				String nombre = sc.nextLine();
				sc.close();
				ct.setNombre(nombre);
			}else if(opcion == 2) {
				System.out.println("Introduce los apellidos");
				String apellidos = sc.nextLine();
				sc.close();
				ct.setApellidos(apellidos);
			}else if(opcion == 3) {
				System.out.println("Introduce la fecha de nacimiento");
				String fecha_de_nacimiento = sc.nextLine();
				sc.close();
				ct.setFecha_de_nacimiento(fecha_de_nacimiento);
			}else if(opcion == 4) {
				System.out.println("Introduce el email");
				email = sc.nextLine();
				sc.close();
				ct.setEmail(email);
			}else if(opcion == 5) {
				System.out.println("Introduce los intereses");
				String intereses = sc.nextLine();
				sc.close();
				ct.setIntereses(intereses);
			}else if(opcion == 6) {
				System.out.println("Introduce la edad");
				int edad = sc.nextInt();
				sc.nextLine();
				sc.close();
				ct.setEdad(edad);
			}else {
				System.out.println("Ha introducido una opcion incorrecta");
			}
			sc.close();
		}
		return null;
	}
	
	public Contacto buscarEmail(String email) throws IOException {
		if(instance != null) {
			Contacto c = instance;
			BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\Archivos de programa\\eclipse2\\P1\\src\\es\\uco\\pw\\contactos.txt")));
			String line;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(email.equals(linea[4])){
	                c.setNombre(linea[0]);
	                c.setApellidos(linea[1] + " " + linea[2]);
	                c.setFecha_de_nacimiento(linea[3]);
	                c.setEmail(linea[4]);
	                c.setIntereses(linea[5]);
	                c.setEdad(Integer.valueOf(linea[6]));
	            }
	        }
			reader.close();
			return c;
		}
		return null;
	}
	
	public Contacto buscarNombre(String nombre) throws IOException {
		if(instance != null) {
			BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\Archivos de programa\\eclipse2\\P1\\src\\es\\uco\\pw\\contactos.txt")));
			String line;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(nombre.equals(linea[0])){
	            	System.out.printf("nombre: " + linea[0]);
	                System.out.printf("\napellidos: " + linea[1] + " " + linea[2]);
	                System.out.printf("\nfecha de nacimiento: " + linea[3]);
	                System.out.printf("\nemail: " + linea[4]);
	                System.out.printf("\nintereses: " + linea[5]);
	                System.out.printf("\nedad: " + linea[6]);
	                System.out.println("\n");
	            }
			}
			reader.close();
		}
		return null;
	}
	
	public Contacto buscarApellido(String apellido) throws IOException {
		if(instance != null) {
			BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\Archivos de programa\\eclipse2\\P1\\src\\es\\uco\\pw\\contactos.txt")));
			String line;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(apellido.equals(linea[1]) || apellido.equals(linea[2])){
	            	System.out.printf("nombre: " + linea[0]);
	                System.out.printf("\napellidos: " + linea[1] + " " + linea[2]);
	                System.out.printf("\nfecha de nacimiento: " + linea[3]);
	                System.out.printf("\nemail: " + linea[4]);
	                System.out.printf("\nintereses: " + linea[5]);
	                System.out.printf("\nedad: " + linea[6]);
	                System.out.println("\n");
	            }
			}
			reader.close();
		}
		return null;
	}
	
	public Contacto buscarIntereses(String intereses) throws IOException {
		if(instance != null) {
			BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\Archivos de programa\\eclipse2\\P1\\src\\es\\uco\\pw\\contactos.txt")));
			String line;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(intereses.equals(linea[5])){
	            	System.out.printf("nombre: " + linea[0]);
	                System.out.printf("\napellidos: " + linea[1] + " " + linea[2]);
	                System.out.printf("\nfecha de nacimiento: " + linea[3]);
	                System.out.printf("\nemail: " + linea[4]);
	                System.out.printf("\nintereses: " + linea[5]);
	                System.out.printf("\nedad: " + linea[6]);
	                System.out.println("\n");
	            }
			}
			reader.close();
		}
		return null;
	}
	
	public Contacto buscarEdad(int edad) throws IOException {
		if(instance != null) {
			BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\Archivos de programa\\eclipse2\\P1\\src\\es\\uco\\pw\\contactos.txt")));
			String line;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(edad == Integer.valueOf(linea[6])){
	            	System.out.printf("nombre: " + linea[0]);
	                System.out.printf("\napellidos: " + linea[1] + " " + linea[2]);
	                System.out.printf("\nfecha de nacimiento: " + linea[3]);
	                System.out.printf("\nemail: " + linea[4]);
	                System.out.printf("\nintereses: " + linea[5]);
	                System.out.printf("\nedad: " + linea[6]);
	                System.out.println("\n");
	            }
			}
			reader.close();
		}
		return null;
	}
}

