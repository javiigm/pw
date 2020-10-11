package es.uco.pw.gestor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Properties;

public class Contacto {

	/* Attributes */
	
	private static Contacto instance = null;
	
	private String nombre;
	
	private String apellidos;
	
	private String fecha_de_nacimiento;
	
	private String email;
	
	private String interes;
	
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
		this.interes=null;
		this.edad=0;
	}
	
	public Contacto(String nombre, String apellidos, String fecha_de_nacimiento, String email, String interes, int edad) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.fecha_de_nacimiento=fecha_de_nacimiento;
		this.email=email;
		this.interes=interes;
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

	public String getInteres() {
		return interes;
	}

	public void setInteres(String interes) {
		this.interes = interes;
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
	
	
	
	public Contacto alta(String nombre, String apellidos, String fecha_de_nacimiento, String email, String interes, int edad) throws IOException {
		if(instance != null) {
			instance = new Contacto(nombre, apellidos, fecha_de_nacimiento, email, interes, edad);
	        Writer output = new BufferedWriter(new FileWriter(ficheroContactos(), true));
	        String[] ap=apellidos.split(" ");
	        output.append("\n" + nombre + "," + ap[0] + "," + ap[1] + "," + fecha_de_nacimiento + "," + email + "," + interes + "," + String.valueOf(edad));
	            
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
		    		br = new BufferedReader(new FileReader(ficheroContactos()));
		            while((linea = br.readLine()) != null){
		                String[] lineaComas = linea.split(",");
		                if(!email.equals(lineaComas[4])) {
		                	lineasAcopiar.add(linea);
		                }
		            }
		            br.close();
		            //RECORREMOS EL VECTOR Y GUARDAMOS LA LINEAS EN EL FICHERO
		            bw = new BufferedWriter(new FileWriter(ficheroContactos()));
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
	        System.out.printf("\ninteres: " + c.getInteres());
	        System.out.printf("\nedad: " + c.getEdad());
		}
		return null;
	}
	
	public Contacto actualizacion(String email,String cadena,int op) throws IOException {
		if(instance != null) {
			Contacto c = buscarEmail(email);
			
				if(op == 1) {
					c.modificar(email,cadena,op-1);
				}else if(op == 2) {
					c.modificar(email,cadena,op-1);
				}else if(op == 3) {
					c.modificar(email,cadena,op);
				}else if(op == 4) {
					c.modificar(email,cadena,op);
				}else if(op == 5) {
					c.modificar(email,cadena,op);
				}else if(op == 6) {
					c.modificar(email,cadena,op);
				}
			
		}
		return null;
	}
	
	public Contacto buscarEmail(String email) throws IOException {
		if(instance != null) {
			Contacto c = instance;
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroContactos())));
			String line;
			Boolean existe_email=false;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(email.equals(linea[4])){
	                c.setNombre(linea[0]);
	                c.setApellidos(linea[1] + " " + linea[2]);
	                c.setFecha_de_nacimiento(linea[3]);
	                c.setEmail(linea[4]);
	                c.setInteres(linea[5]);
	                c.setEdad(Integer.valueOf(linea[6]));
	                existe_email=true;
	            }
	        }
			reader.close();
			if(!existe_email) {
				System.out.println("No se ha encontrado ningun contacto por ese email");
				System.exit(0);
			}
			return c;
		}
		return null;
	}
	
	public Contacto buscarNombre(String nombre) throws IOException {
		if(instance != null) {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroContactos())));
			String line;
			Boolean existe_nombre=false;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(nombre.equals(linea[0])){
	            	System.out.printf("nombre: " + linea[0]);
	                System.out.printf("\napellidos: " + linea[1] + " " + linea[2]);
	                System.out.printf("\nfecha de nacimiento: " + linea[3]);
	                System.out.printf("\nemail: " + linea[4]);
	                System.out.printf("\ninteres: " + linea[5]);
	                System.out.printf("\nedad: " + linea[6]);
	                System.out.println("\n");
	                existe_nombre=true;
	            }
			}
			reader.close();
			if(!existe_nombre)
				System.out.println("No se ha encontrado ningun contacto por ese nombre");
		}
		return null;
	}
	
	public Contacto buscarNombreApellidos(String nombre_completo) throws IOException {
		if(instance != null) {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroContactos())));
			String line;
			Boolean existe_nombre_apellidos=false;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(nombre_completo.equals(linea[0] + "," + linea[1] + " " + linea[2])){
	            	System.out.printf("nombre: " + linea[0]);
	                System.out.printf("\napellidos: " + linea[1] + " " + linea[2]);
	                System.out.printf("\nfecha de nacimiento: " + linea[3]);
	                System.out.printf("\nemail: " + linea[4]);
	                System.out.printf("\ninteres: " + linea[5]);
	                System.out.printf("\nedad: " + linea[6]);
	                System.out.println("\n");
	                existe_nombre_apellidos=true;
	            }
			}
			reader.close();
			if(!existe_nombre_apellidos) {
				System.out.println("No se ha encontrado ningun contacto por ese nombre y apellidos");
				System.exit(0);
			}
		}
		return null;
	}
	
	
	public Contacto buscarApellido(String apellido) throws IOException {
		if(instance != null) {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroContactos())));
			String line;
			Boolean existe_apellido=false;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(apellido.equals(linea[1]) || apellido.equals(linea[2])){
	            	System.out.printf("nombre: " + linea[0]);
	                System.out.printf("\napellidos: " + linea[1] + " " + linea[2]);
	                System.out.printf("\nfecha de nacimiento: " + linea[3]);
	                System.out.printf("\nemail: " + linea[4]);
	                System.out.printf("\ninteres: " + linea[5]);
	                System.out.printf("\nedad: " + linea[6]);
	                System.out.println("\n");
	                existe_apellido=true;
	            }
			}
			reader.close();
			if(!existe_apellido) {
				System.out.println("No se ha encontrado ningun contacto con ese apellido");
				System.exit(0);
			}
		}
		return null;
	}
	
	public Contacto buscarInteres(String interes) throws IOException {
		if(instance != null) {
			Boolean existe_interes = false;
			Properties p = new Properties();
			p.load(new FileReader("D://Archivos de programa/eclipse2/P1/src/properties"));
			for(int i=1; i<6; i++)
				if(interes.equals(p.getProperty("interes"+i)))
					existe_interes=true;
			if(existe_interes) {
				BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroContactos())));
				String line;
				while((line = reader.readLine()) != null) {
					String[] linea=line.split(",");
		            if(interes.equals(linea[5])){
		            	System.out.printf("nombre: " + linea[0]);
		                System.out.printf("\napellidos: " + linea[1] + " " + linea[2]);
		                System.out.printf("\nfecha de nacimiento: " + linea[3]);
		                System.out.printf("\nemail: " + linea[4]);
		                System.out.printf("\ninteres: " + linea[5]);
		                System.out.printf("\nedad: " + linea[6]);
		                System.out.println("\n");
		            }
				}
				reader.close();
			}else {
				System.out.println("No se ha encontrado el interes buscado");
				System.exit(0);
			}
		}
		return null;
	}
	
	public Contacto buscarEdad(int edad) throws IOException {
		if(instance != null) {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroContactos())));
			String line;
			Boolean existe_edad = false;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(edad == Integer.valueOf(linea[6])){
	            	System.out.printf("nombre: " + linea[0]);
	                System.out.printf("\napellidos: " + linea[1] + " " + linea[2]);
	                System.out.printf("\nfecha de nacimiento: " + linea[3]);
	                System.out.printf("\nemail: " + linea[4]);
	                System.out.printf("\ninteres: " + linea[5]);
	                System.out.printf("\nedad: " + linea[6]);
	                System.out.println("\n");
	                existe_edad=true;
	            }
			}
			reader.close();
			if(!existe_edad) {
				System.out.println("No se ha encontrado ningun contacto con esa edad");
				System.exit(0);
			}
		}
		return null;
	}
	
	@SuppressWarnings("static-access")
	public Contacto modificar(String email,String cadena,int op) {
		if(instance != null) {
			String[] cad=null;
			if(op == 1) {
				cad = cadena.split(" ");
			}
			
	        String linea="";
	    	BufferedReader br = null;
	        BufferedWriter bw = null;
	    	ArrayList<String> lineasAcopiar = new ArrayList<String>();
	    	try{
	    		br = new BufferedReader(new FileReader(ficheroContactos()));
	            while((linea = br.readLine()) != null){
	                String[] lineaComas = linea.split(",");
	                if(!email.equals(lineaComas[4])) {
	                	lineasAcopiar.add(linea);
	                }
	                else {
	                	if(op==1) {
	                		lineaComas[op]=cad[0];
	                		lineaComas[op+1]=cad[1];
	                	}else {
	                		lineaComas[op] = cadena;
	                	}
	                	
	                	linea=linea.join(",",lineaComas[0],lineaComas[1],lineaComas[2],lineaComas[3],lineaComas[4],lineaComas[5],lineaComas[6]);
	                	lineasAcopiar.add(linea);
	                }
	            }
	            br.close();
	            //RECORREMOS EL VECTOR Y GUARDAMOS LA LINEAS EN EL FICHERO
	            bw = new BufferedWriter(new FileWriter(ficheroContactos()));
	            for(int l=0;l<lineasAcopiar.size();l++){
	                linea = (String)lineasAcopiar.get(l);
	                bw.write(linea);
	                if(l!=lineasAcopiar.size()-1)
	                	bw.newLine();
	            }
	            bw.close();
	            System.out.println("El contacto con email " + email + " ha sido actualizado");
	
	        }catch(Exception e){System.out.println("Error: " + e);}
	    	
	}
	return null;
	}
	
	public String ficheroContactos() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader("D://Archivos de programa/eclipse2/P1/src/properties"));
		return p.getProperty("path1");
	}
}

