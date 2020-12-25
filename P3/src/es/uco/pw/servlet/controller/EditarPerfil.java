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

import es.uco.pw.data.dao.UserDAO;
import es.uco.pw.data.dao.InteresDAO;
import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.display.javabean.UpdateBean;

/**
 * Servlet implementation class EditarPerfil
 */
@WebServlet("/EditarPerfil")
public class EditarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPerfil() {
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
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String fecha_de_nacimiento = request.getParameter("fecha_de_nacimiento");
		String password = request.getParameter("password");
		String interes = request.getParameter("interes");
		String edad = request.getParameter("edad");
		
		String fichero = getServletContext().getInitParameter("config.properties");
		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		String fichero2 = getServletContext().getInitParameter("sql.properties");
		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("")) {
			RequestDispatcher rd=request.getRequestDispatcher("./index.jsp");  
	        rd.include(request,response);
		} else { 
			int status = 0;
			int interes_comprobado = 0;
			
			if (nombre != null && !nombre.isEmpty()){
				UserDAO userdao = new UserDAO();
				status = userdao.update(customerBean.getEmailUser(),nombre,1,conf,sql);
				if (status != 0)
					customerBean.setNombre(nombre);
			}
			if (apellidos != null && !apellidos.isEmpty()){
				UserDAO userdao = new UserDAO();
				status = userdao.update(customerBean.getEmailUser(),apellidos,2,conf,sql);
				if (status != 0)
					customerBean.setApellidos(apellidos);
			}
			if (fecha_de_nacimiento != null && !fecha_de_nacimiento.isEmpty()){
				UserDAO userdao = new UserDAO();
				status = userdao.update(customerBean.getEmailUser(),fecha_de_nacimiento,3,conf,sql);
				if (status != 0)
					customerBean.setFecha_de_nacimiento(fecha_de_nacimiento);
			}
			if (password != null && !password.isEmpty()){
				UserDAO userdao = new UserDAO();
				status = userdao.update(customerBean.getEmailUser(),password,4,conf,sql);
				if (status != 0)
					customerBean.setPassword(password);
			}
			if (interes != null && !interes.isEmpty()){
				InteresDAO indao = new InteresDAO();
				interes_comprobado = indao.comprobarInteres(interes,conf,sql);
				//Comprobamos que sea un interes valido
				if ( interes_comprobado == 1) {
					fichero = getServletContext().getInitParameter("config.properties");
					conf = getServletContext().getResourceAsStream(fichero);
					fichero2 = getServletContext().getInitParameter("sql.properties");
					sql = getServletContext().getResourceAsStream(fichero2);
					
					UserDAO userdao = new UserDAO();
					status = userdao.update(customerBean.getEmailUser(),interes,5,conf,sql);
					if (status != 0) 
						customerBean.setInteres(interes);
				}else {
					//Si el interes no coincide con ninguno de los candidatos.
					interes_comprobado = 1;
					
					out.print("El interes no corresponde con los candidatos.");
					RequestDispatcher rd=request.getRequestDispatcher("./mvc/view/editarPerfilView.jsp");  
			        rd.include(request,response);
				}
			}
			if (edad != null && !edad.isEmpty() && Integer.parseInt(edad) > 0){
				UserDAO userdao = new UserDAO();
				status = userdao.update(customerBean.getEmailUser(),edad,6,conf,sql);
				if (status != 0)
					customerBean.setEdad(Integer.parseInt(edad));
			}
			/////////////////////////////////////////
			if (status == 0 && interes_comprobado == 0){
				UpdateBean updateBean = new UpdateBean();
				int cont = updateBean.getContador();
				updateBean.setContador(cont++);
				session.setAttribute("updateBean", updateBean);
				
				out.print("Se ha producido un fallo al actualizar los datos. Vuelva a intentarlo rellenado al menos un campo.");
				RequestDispatcher rd=request.getRequestDispatcher("./mvc/view/editarPerfilView.jsp");
				rd.include(request,response);
			}
			session.setAttribute("customerBean", customerBean);
			
	        response.sendRedirect("./mvc/view/perfilView.jsp");  
		}
	}

}
