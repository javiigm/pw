package es.uco.pw.business.anuncio;

import es.uco.pw.to.Estado;
import es.uco.pw.to.Type;

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
	private String fecha_fin;
	private Type type;
	private Estado estado;
	private String tema;
	/**
	 * constructor vacio
	 */
	public Anuncio() {
		
	}
	/**
	 * Constructor que permite crear un anuncio con todos sus parametros
	 * @param identificador
	 * @param titulo
	 * @param usuario_propietario
	 * @param usuarios_destinatarios
	 * @param cuerpo
	 * @param fecha_de_publicacion
	 * @param fecha_fin
	 * @param type
	 * @param estado
	 * @param tema
	 */
	public Anuncio(int identificador, String titulo, String usuario_propietario, String usuarios_destinatarios, String cuerpo, String fecha_de_publicacion, String fecha_fin, String type, String estado, String tema) {
		this.identificador = identificador;
		this.titulo = titulo;
		this.usuario_propietario = usuario_propietario;
		this.usuarios_destinatarios = usuarios_destinatarios;
		this.cuerpo = cuerpo;
		this.fecha_de_publicacion = fecha_de_publicacion;
		this.fecha_fin = fecha_fin;
		this.type = Type.valueOf(type);
		this.estado = Estado.valueOf(estado);
		this.tema = tema;
	}
	/**
	 * getter identificador
	 * @return identificador
	 */
	public int getIdentificador() {
		return identificador;
	}
	/**
	 * setter identificador
	 * @param identificador
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	/**
	 * getter titulo
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * setter titulo
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * getter usuario propietario
	 * @return usuario_propietario
	 */
	public String getUsuario_propietario() {
		return usuario_propietario;
	}
	/**
	 * setter usuario propietario
	 * @param usuario_propietario
	 */
	public void setUsuario_propietario(String usuario_propietario) {
		this.usuario_propietario = usuario_propietario;
	}
	/**
	 * getter usuarios destinatarios
	 * @return usuarios_destinatarios
	 */
	public String getUsuarios_destinatarios() {
		return usuarios_destinatarios;
	}
	/**
	 * setter usuarios destinatarios
	 * @param usuarios_destinatarios
	 */
	public void setUsuarios_destinatarios(String usuarios_destinatarios) {
		this.usuarios_destinatarios = usuarios_destinatarios;
	}
	/**
	 * getter cuerpo
	 * @return cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}
	/**
	 * setter cuerpo
	 * @param cuerpo
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	/**
	 * getter fecha de publicacion
	 * @return fecha_de_publicacion
	 */
	public String getFecha_de_publicacion() {
		return fecha_de_publicacion;
	}
	/**
	 * setter fecha de publicacion
	 * @param fecha_de_publicacion
	 */
	public void setFecha_de_publicacion(String fecha_de_publicacion) {
		this.fecha_de_publicacion = fecha_de_publicacion;
	}
	/**
	 * getter fecha fin
	 * @return fecha_fin
	 */
	public String getFecha_fin() {
		return fecha_fin;
	}
	/**
	 * setter fecha fin
	 * @param fecha_fin
	 */
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	/**
	 * getter type
	 * @return type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * setter type
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * getter estado
	 * @return estado
	 */
	public Estado getEstado() {
		return estado;
	}
	/**
	 * setter estado
	 * @param estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	/**
	 * getter tema
	 * @return tema
	 */
	public String getTema() {
		return tema;
	}
	/**
	 * setter tema
	 * @param tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}
	
}