package es.uco.pw.tablon;

/*
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import es.uco.pw.gestor.Contacto;
*/

public class Anuncio {
	
	private int identificador;
	private String titulo;
	private String usuario_propietario;
	private String usuarios_destinatarios;
	private String cuerpo;
	private String fecha_de_publicacion;
	private Type type;
	private Estado estado;
	
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
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setType(Estado estado) {
		this.estado = estado;
	}	

}
	
