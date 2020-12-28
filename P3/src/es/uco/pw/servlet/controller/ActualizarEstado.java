package es.uco.pw.servlet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.data.dao.AnuncioDAO;

/**
 * Servlet implementation class ActualizarEstado
 */
@WebServlet("/ActualizarEstado")
public class ActualizarEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarEstado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String en_espera_anuncio = request.getParameter("en_espera_anuncio");
		String publicar_anuncio = request.getParameter("publicar_anuncio");
		String archivar_anuncio = request.getParameter("archivar_anuncio");
		String editado_anuncio = request.getParameter("editado_anuncio");
		String eliminar_anuncio = request.getParameter("eliminar_anuncio");
		
		String fichero = getServletContext().getInitParameter("config.properties");
		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		String fichero2 = getServletContext().getInitParameter("sql.properties");
		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		
		if(request.getParameter("en_espera_anuncio") != null) {
			AnuncioDAO.update(Integer.parseInt(en_espera_anuncio),"en_espera",conf,sql);
		}
		else if(request.getParameter("publicar_anuncio") != null) {
			AnuncioDAO.update(Integer.parseInt(publicar_anuncio),"publicado",conf,sql);
		}
		else if(request.getParameter("archivar_anuncio") != null) {
			AnuncioDAO.update(Integer.parseInt(archivar_anuncio),"archivado",conf,sql);
		}
		else if(request.getParameter("editado_anuncio") != null) {
			AnuncioDAO.update(Integer.parseInt(editado_anuncio),"editado",conf,sql);
		}
		else if(request.getParameter("eliminar_anuncio") != null) {
			AnuncioDAO.delete(Integer.parseInt(eliminar_anuncio),conf,sql);
		}
		response.sendRedirect("./mvc/view/perfilView.jsp");
	}

}
