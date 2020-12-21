<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.user.User,es.uco.pw.data.dao.UserDAO"%>
<%@ page import="es.uco.pw.business.user.User,es.uco.pw.data.dao.InteresDAO"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede de la vista 
		b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
	*/

//Caso 1. Si esta logado vuelve al index, no deberia estar aqui
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("")) {
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
	String fecha_de_nacimiento = request.getParameter("fecha_de_nacimiento");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String interes = request.getParameter("interes");
	String edad = request.getParameter("edad");
	
	//Obtenemos el fichero config.properties obteniendo la ruta de web.xml
	String fichero = application.getInitParameter("config.properties");
	java.io.InputStream conf = application.getResourceAsStream(fichero);
	String fichero2 = application.getInitParameter("sql.properties");
	java.io.InputStream sql = application.getResourceAsStream(fichero2);
	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (email != null && password != null && nombre != null && apellidos != null && fecha_de_nacimiento != null && interes != null && edad != null && Integer.parseInt(edad) > 0) {
		//Se accede a bases de datos para obtener el usuario
		User user = new User(nombre,apellidos,fecha_de_nacimiento,email,password,interes,Integer.parseInt(edad));
		InteresDAO indao = new InteresDAO();
		//Comprobamos que sea un interes valido
		if ( indao.comprobarInteres(interes,conf,sql) == 1) {
			
			UserDAO userDAO = new UserDAO();
			int status = userDAO.comprobarUsuario(user.getEmail(), conf, sql);
			
			fichero = application.getInitParameter("config.properties");
			conf = application.getResourceAsStream(fichero);
			fichero2 = application.getInitParameter("sql.properties");
			sql = application.getResourceAsStream(fichero2);
			
			if (status == 1) {
				UserDAO userdao = new UserDAO(); 
				status = userdao.registro(user,conf,sql);
			}
			//Se realizan todas las comprobaciones necesarias del dominio
			//Aquí sólo comprobamos que exista el usuario
			if (status != 0) {
				// Usuario válido		
	%>
				<jsp:setProperty property="emailUser" value="<%=email%>"
					name="customerBean" />
				<jsp:setProperty property="password" value="<%=password %>"
					name="customerBean" />
				<jsp:setProperty property="nombre" value="<%=nombre%>"
					name="customerBean" />
				<jsp:setProperty property="apellidos" value="<%=apellidos %>"
					name="customerBean" />
				<jsp:setProperty property="fecha_de_nacimiento"
					value="<%=fecha_de_nacimiento%>" name="customerBean" />
				<jsp:setProperty property="interes" value="<%=interes%>"
					name="customerBean" />
				<jsp:setProperty property="edad" value="<%=Integer.parseInt(edad)%>"
					name="customerBean" />
	<%
			} else {
				// Usuario no válido
				nextPage = "../view/registerView.jsp";
				mensajeNextPage = "El usuario que ha indicado ya se encuentra registrado o ha ocurrido algun problema con los datos introducidos." 
				+ System.getProperty("line.separator") + "Vuelva a intentarlo.";
			}
		} else {
			//Interes no valido
			fichero = application.getInitParameter("config.properties");
			conf = application.getResourceAsStream(fichero);
			fichero2 = application.getInitParameter("sql.properties");
			sql = application.getResourceAsStream(fichero2);
			
			InteresDAO idao = new InteresDAO();
			String intereses = idao.consultaInteres(conf, sql);
			nextPage = "../view/registerView.jsp";
			mensajeNextPage = "Intentalo de nuevo!! El interes introducido no es valido, introduce uno de los siguientes candidatos:"
			+ System.getProperty("line.separator") + intereses.substring(0, intereses.length()-2);
		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../view/registerView.jsp";		
	}
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message" />
</jsp:forward>