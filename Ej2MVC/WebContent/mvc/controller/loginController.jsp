<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.user.User,es.uco.pw.data.dao.UserDAO" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean  id="loginBean" scope="session" class="es.uco.pw.display.javabean.LoginBean"></jsp:useBean>
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
	String emailUser = request.getParameter("email");
	String password = request.getParameter("password");
	

	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (emailUser != null && password != null) {
		//Se accede a bases de datos para obtener el usuario
		UserDAO userDAO = new UserDAO();
		User user = userDAO.consulta(emailUser,password);

		//Se realizan todas las comprobaciones necesarias del dominio
		//Aquí sólo comprobamos que exista el usuario
		if (user != null && user.getEmail().equalsIgnoreCase(emailUser) && user.getPassword().equalsIgnoreCase(password) && !user.getInteres().equals("")) {
			// Usuario válido		
%>
<jsp:setProperty property="emailUser" value="<%=user.getEmail()%>" name="customerBean"/>
<jsp:setProperty property="password" value="<%=user.getPassword()%>" name="customerBean"/>
<jsp:setProperty property="nombre" value="<%=user.getNombre()%>" name="customerBean"/>
<jsp:setProperty property="apellidos" value="<%=user.getApellidos()%>" name="customerBean"/>
<jsp:setProperty property="fecha_de_nacimiento" value="<%=user.getFecha_de_nacimiento()%>" name="customerBean"/>
<jsp:setProperty property="interes" value="<%=user.getInteres()%>" name="customerBean"/>
<jsp:setProperty property="edad" value="<%=user.getEdad()%>" name="customerBean"/>
<%
		} else {
			// Usuario no válido
			int cont = loginBean.getContador();
			loginBean.setContador(cont++);
			%> <jsp:setProperty property="contador" value="<%=cont++%>" name="loginBean"/> <% 
			nextPage = "../view/loginView.jsp";
			int intentos = 3 - loginBean.getContador();
			if (intentos == 0)
				nextPage = "../view/loginView.jsp";
			mensajeNextPage = "El usuario que ha indicado no existe o no es v&aacute;lido.\nLe quedan " + intentos + " intentos.";
			
		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../view/loginView.jsp";		
	}
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>