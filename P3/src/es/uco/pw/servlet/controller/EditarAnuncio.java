package es.uco.pw.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.anuncio.Anuncio;
import es.uco.pw.data.dao.AnuncioDAO;

/**
 * Servlet implementation class EditarPerfil
 */
@WebServlet("/EditarAnuncio")
public class EditarAnuncio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAnuncio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String titulo = request.getParameter("titulo");
		String cuerpo = request.getParameter("cuerpo");
		String fecha_de_publicacion = request.getParameter("fecha_de_publicacion");
		
		String fichero = getServletContext().getInitParameter("config.properties");
		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		String fichero2 = getServletContext().getInitParameter("sql.properties");
		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		
		HttpSession session = request.getSession();
		Anuncio anuncio = (Anuncio) session.getAttribute("anuncio");
		if (anuncio == null || anuncio.getTitulo().equals("")) {
			RequestDispatcher rd=request.getRequestDispatcher("./index.jsp");  
	        rd.include(request,response);
		} else { 
			int status = 0;
			AnuncioDAO anunciodao = new AnuncioDAO();
			if (titulo != null && !titulo.isEmpty()){
				status = anunciodao.editarAnuncio(anuncio.getIdentificador(),titulo,1,conf,sql);
				if (status != 0)
					anuncio.setTitulo(titulo);
				else {
					out.print("Error al editar el titulo");
					RequestDispatcher rd=request.getRequestDispatcher("./mvc/view/editarAnuncioView.jsp");  
			        rd.include(request,response);
				}
			}
			if (cuerpo != null && !cuerpo.isEmpty()){
				status = anunciodao.editarAnuncio(anuncio.getIdentificador(),cuerpo,2,conf,sql);
				if (status != 0)
					anuncio.setCuerpo(cuerpo);
				else {
					out.print("Error al editar el cuerpo");
					RequestDispatcher rd=request.getRequestDispatcher("./mvc/view/editarAnuncioView.jsp");  
			        rd.include(request,response);
				}
			}
			if (fecha_de_publicacion != null && !fecha_de_publicacion.isEmpty()){
				status = anunciodao.editarAnuncio(anuncio.getIdentificador(),fecha_de_publicacion,3,conf,sql);
				if (status != 0)
					anuncio.setFecha_de_publicacion(fecha_de_publicacion);
				else {
					out.print("Error al editar la fecha de publicacion");
					RequestDispatcher rd=request.getRequestDispatcher("./mvc/view/editarAnuncioView.jsp");  
			        rd.include(request,response);
				}
			}
			
			session.setAttribute("anuncio", anuncio);
			
	        response.sendRedirect("./mvc/view/perfilView.jsp");  
		}
	}

}
