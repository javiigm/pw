<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.user.User,es.uco.pw.data.dao.UserDAO" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede de la vista 
		b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
	*/
//Caso 1: Por defecto, vuelve al index
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("")) {
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
	String fecha_de_nacimiento = request.getParameter("fecha_de_nacimiento");
	String emailUser = request.getParameter("email");
	String password = request.getParameter("password");
	String interes = request.getParameter("interes");
	String edad = request.getParameter("edad");

	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (emailUser != null && password != null && nombre != null && apellidos != null && fecha_de_nacimiento != null && interes != null && Integer.parseInt(edad) > 0) {
		//Se accede a bases de datos para obtener el usuario
		UserDAO userDAO = new UserDAO();
		int status = userDAO.registro(nombre, apellidos, fecha_de_nacimiento, emailUser, password, interes, Integer.parseInt(edad));

		//Se realizan todas las comprobaciones necesarias del dominio
		//Aquí sólo comprobamos que exista el usuario
		if (status != 0) {
			// Usuario válido		
%>
<jsp:setProperty property="emailUser" value="<%=emailUser%>" name="customerBean"/>
<jsp:setProperty property="password" value="<%=password %>" name="customerBean"/>
<jsp:setProperty property="nombre" value="<%=nombre%>" name="customerBean"/>
<jsp:setProperty property="apellidos" value="<%=apellidos %>" name="customerBean"/>
<jsp:setProperty property="fecha_De_nacimiento" value="<%=fecha_de_nacimiento%>" name="customerBean"/>
<jsp:setProperty property="interes" value="<%=interes%>" name="customerBean"/>
<jsp:setProperty property="edad" value="<%=Integer.parseInt(edad)%>" name="customerBean"/>
<%
		} else {
			// Usuario no válido
			nextPage = "../view/registerView.jsp";
			mensajeNextPage = "El usuario que ha indicado ya se encuentra registrado";
		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../view/registerView.jsp";		
	}
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>