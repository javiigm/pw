package es.uco.pw.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.display.javabean.LoginBean;
import es.uco.pw.business.user.User;
import es.uco.pw.data.dao.UserDAO;

/**
 * Servlet implementation class ComprobarLogin
 */

public class ComprobarLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		LoginBean intentos = (LoginBean)session.getAttribute("intentos");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String fichero = getServletContext().getInitParameter("config.properties");
		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		String fichero2 = getServletContext().getInitParameter("sql.properties");
		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		
		
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("")) {
			UserDAO userDAO = new UserDAO();
			User user;
			user = userDAO.consulta(email, password, conf, sql);
			if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password) && !user.getInteres().equals("")) {
				customerBean = new CustomerBean(user.getEmail(),user.getPassword(),user.getNombre(),user.getApellidos(),user.getFecha_de_nacimiento(),user.getInteres(),user.getEdad());
				session.setAttribute("customerBean",customerBean);
				/*RequestDispatcher disp = request.getRequestDispatcher("home.jsp");
				disp.forward(request, response);*/
				response.sendRedirect("mvc/view/home.jsp");
			} else {
				if (intentos != null && intentos.getContador() < 3) {
				out.printf("Error al validar el usuario y la contrasena. Le quedan %d intentos", 3 - intentos.getContador());
				intentos.setContador(intentos.getContador() + 1);
				session.setAttribute("intentos", intentos);
		        RequestDispatcher rd=request.getRequestDispatcher("./mvc/view/loginView.jsp");  
		        rd.include(request,response);	
				} else if (intentos == null) {
					intentos = new LoginBean();
					intentos.setContador(1);
					session.setAttribute("intentos", intentos);	
					out.printf("Error al validar el usuario y la contrasena. Le quedan %d intentos", 3 - intentos.getContador());
					RequestDispatcher rd=request.getRequestDispatcher("./mvc/view/loginView.jsp");  
			        rd.include(request,response);	
				} else {
					response.sendRedirect("https://www.uco.es");
				}
				
			}
		} else {
			response.sendRedirect("mvc/view/home.jsp");
		}
	}

}
