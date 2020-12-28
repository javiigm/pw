package es.uco.pw.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import es.uco.pw.business.anuncio.Anuncio;
import es.uco.pw.data.dao.AnuncioDAO;
import es.uco.pw.display.javabean.CustomerBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CrearAnuncio
 */
@WebServlet("/CrearAnuncio")
public class CrearAnuncio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearAnuncio() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String titulo = request.getParameter("titulo");
		String usuario_propietario = request.getParameter("usuario_propietario");
		String cuerpo = request.getParameter("cuerpo");
		String fecha_publicacion = request.getParameter("fecha_de_publicacion");
		String tipo = request.getParameter("tipo");
		
		String fichero = getServletContext().getInitParameter("config.properties");
		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		String fichero2 = getServletContext().getInitParameter("sql.properties");
		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		
		String tema = "";
		String fecha_fin ="";
		String usuario_destinatario = "";
		
		if (tipo.equals("tematico")) {
			
			HttpSession session = request.getSession();
			CustomerBean cb = (CustomerBean)session.getAttribute("customerBean");
			
			out.print("<br /><label id=\"intereses\">Su interes o interes disponible es: " + cb.getInteres() + "</label>");
			out.println("<br /> <label for=\"cuerpo\">Tema: </label>" +
				"<input type=\"text\" id=\"tema\" name=\"tema\" required><br />");
			tema = request.getParameter("tema");
		}
		else if (tipo.equals("flash")){
			out.println("<br /> <label for=\"fecha_fin\">Fecha fin publicacion: </label>" +
					"<input type=\"text\" id=\"fecha_fin\" name=\"fecha_fin\" required><br />");
			fecha_fin = request.getParameter("fecha_fin");
		}
		else if (tipo.equals("individualizado")){
			out.println("<br /> <label for=\"usuario_destinatario\">Usuario destinatario: </label>" +
					"<input type=\"text\" id=\"usuario_destinatario\" name=\"usuario_destinatario\" required><br />");
			usuario_destinatario = request.getParameter("usuario_destinatario");
		}
		
		int identificador = AnuncioDAO.calcularIdentificador(conf,sql);
		Anuncio anuncio = new Anuncio(identificador + 1, titulo, usuario_propietario, usuario_destinatario, cuerpo, fecha_publicacion, fecha_fin, tipo, "editado", tema);
		
		fichero = getServletContext().getInitParameter("config.properties");
		conf = getServletContext().getResourceAsStream(fichero);
		fichero2 = getServletContext().getInitParameter("sql.properties");
		sql = getServletContext().getResourceAsStream(fichero2);
		
		AnuncioDAO.save(anuncio, conf, sql);
		
		response.sendRedirect("./mvc/view/homeView.jsp");
		
	}

}
