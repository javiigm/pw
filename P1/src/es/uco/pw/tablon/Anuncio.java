package es.uco.pw.tablon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import es.uco.pw.gestor.Contacto;

public class Anuncio {
	
	private int identificador;
	private String titulo;
	private String usuario_propietario;
	private String usuarios_destinatarios;
	private String cuerpo;
	private String fecha_de_publicacion;
	private String tipo;
	private String estado;
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getUsuario_propietario() {
		return usuario_propietario;
	}
	public void setUsuario_propietario(String usuario_propietario) {
		this.usuario_propietario = usuario_propietario;
	}
	public String getUsuarios_destinatarios() {
		return usuarios_destinatarios;
	}
	public void setUsuarios_destinatarios(String usuarios_destinatarios) {
		this.usuarios_destinatarios = usuarios_destinatarios;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getFecha_de_publicacion() {
		return fecha_de_publicacion;
	}
	public void setFecha_de_publicacion(String fecha_de_publicacion) {
		this.fecha_de_publicacion = fecha_de_publicacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Contacto buscarPropietario(String propietario) throws IOException {
		if(instance != null) {
			BufferedReader reader = new BufferedReader(new FileReader(new File(ficheroContactos())));
			String line;
			while((line = reader.readLine()) != null) {
				String[] linea=line.split(",");
	            if(propietario.equals(linea[2])){
	            	System.out.printf("id: " + linea[0]);
	                System.out.printf("\ntitulo: " + linea[1]);
	                System.out.printf("\npropietario: " + linea[2]);
	                System.out.printf("\ndestinatarios: " + linea[3]);
	                System.out.printf("\ncuerpo: " + linea[4]);
	                System.out.printf("\nfecha de nacimiento: " + linea[5]);
	                System.out.println("\n");
	            }
			}
			reader.close();
			if(!existe_usuario(propietario)) {
				System.out.println("No se ha encontrado ningun contacto con esa edad");
				System.exit(0);
			}
		}
		return null;
	}
	
	public Boolean existe_usuario(String usuario) {
		if(Contacto.buscarEmail(usuario) != null)
			return true;
		return false;
	}
	
	
}
