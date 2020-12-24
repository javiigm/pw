package es.uco.pw.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.user.User;
import es.uco.pw.data.dao.UserDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class ComprobarRegistro
 */

public class ComprobarRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprobarRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String fecha_de_nacimiento = request.getParameter("fecha_de_nacimiento");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String interes = request.getParameter("interes");
		int edad = Integer.parseInt(request.getParameter("edad"));
		
		String fichero = getServletContext().getInitParameter("config.properties");
		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		String fichero2 = getServletContext().getInitParameter("sql.properties");
		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("")) {
			UserDAO userDAO = new UserDAO();
			User user = new User(nombre,apellidos,fecha_de_nacimiento,email,password,interes,edad);
			int status = userDAO.registro(user, conf, sql);
			if (status == 0) {
				customerBean = new CustomerBean(user.getEmail(),user.getPassword(),user.getNombre(),user.getApellidos(),user.getFecha_de_nacimiento(),user.getInteres(),user.getEdad());
				session.setAttribute("customerBean",customerBean);
				RequestDispatcher disp = request.getRequestDispatcher("home.jsp");
				disp.forward(request, response);
			}
			else {
				out.print("Error al introducir los datos del usuario");  
		        RequestDispatcher rd=request.getRequestDispatcher("/mvc/view/registerView.jsp");  
		        rd.include(request,response);
			}
		} else { 
	        RequestDispatcher rd=request.getRequestDispatcher("home.jsp");  
	        rd.include(request,response);
		}
	}

}
