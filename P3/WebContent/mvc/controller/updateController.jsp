<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.user.User,es.uco.pw.data.dao.UserDAO"%>
<%@ page import="es.uco.pw.business.user.User,es.uco.pw.data.dao.InteresDAO"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="updateBean" scope="session"   class="es.uco.pw.display.javabean.UpdateBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) customerBean no está logado: Vuelve al index.jsp
	2) customerBean si está logado (!= null && != "") 
		
	*/

//Caso 1. Si no esta logado
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
//Caso 2. Si esta logado pero quiere modificar sus datos

if (customerBean != null && !customerBean.getEmailUser().equals("") && !customerBean.getPassword().equals("")) {
	
	String nombre_u = request.getParameter("nombre_u");
	String apellidos_u = request.getParameter("apellidos_u");
	String fecha_de_nacimiento_u = request.getParameter("fecha_de_nacimiento_u");
	String password_u = request.getParameter("password_u");
	String interes_u = request.getParameter("interes_u");
	String edad_u = request.getParameter("edad_u");
	
	//Obtenemos el fichero config.properties obteniendo la ruta de web.xml
	String fichero = application.getInitParameter("config.properties");
	java.io.InputStream conf = application.getResourceAsStream(fichero);
	String fichero2 = application.getInitParameter("sql.properties");
	java.io.InputStream sql = application.getResourceAsStream(fichero2);
	
	int status = 0;
	int interes_comprobado = 0;
	
	if (nombre_u != null && !nombre_u.isEmpty()){
		UserDAO userdao = new UserDAO();
		status = userdao.update(customerBean.getEmailUser(),nombre_u,1,conf,sql);
		if (status != 0)
		%>
			<jsp:setProperty property="nombre" value="<%=nombre_u%>" name="customerBean" />
<%	
	}
	if (apellidos_u != null && !apellidos_u.isEmpty()){
		UserDAO userdao = new UserDAO();
		status = userdao.update(customerBean.getEmailUser(),apellidos_u,2,conf,sql);
		if (status != 0)
		%>
			<jsp:setProperty property="apellidos" value="<%=apellidos_u%>" name="customerBean" />
<%	
	}
	if (fecha_de_nacimiento_u != null && !fecha_de_nacimiento_u.isEmpty()){
		UserDAO userdao = new UserDAO();
		status = userdao.update(customerBean.getEmailUser(),fecha_de_nacimiento_u,3,conf,sql);
		if (status != 0)
		%>
			<jsp:setProperty property="fecha_de_nacimiento" value="<%=fecha_de_nacimiento_u%>" name="customerBean" />
<%	
	}
	if (password_u != null && !password_u.isEmpty()){
		UserDAO userdao = new UserDAO();
		status = userdao.update(customerBean.getEmailUser(),password_u,4,conf,sql);
		if (status != 0)
		%>
			<jsp:setProperty property="password" value="<%=password_u%>" name="customerBean" />
<%	
	}
	if (interes_u != null && !interes_u.isEmpty()){
		InteresDAO indao = new InteresDAO();
		interes_comprobado = indao.comprobarInteres(interes_u,conf,sql);
		//Comprobamos que sea un interes valido
		if ( interes_comprobado == 1) {
			UserDAO userdao = new UserDAO();
			status = userdao.update(customerBean.getEmailUser(),interes_u,5,conf,sql);
			if (status != 0) %>
				<jsp:setProperty property="interes" value="<%=interes_u%>" name="customerBean" />
			<%	
		}else {
			//Si el interes no coincide con ninguno de los candidatos.
			interes_comprobado = 1;
			fichero = application.getInitParameter("config.properties");
			conf = application.getResourceAsStream(fichero);
			fichero2 = application.getInitParameter("sql.properties");
			sql = application.getResourceAsStream(fichero2);
			
			InteresDAO idao = new InteresDAO();
			String intereses = idao.consultaInteres(conf, sql);
			nextPage = "../view/updateView.jsp";
			mensajeNextPage = "El interes no corresponde con los candidatos. Todos los demas datos introducidos se han actualizado."
				+ " Los posibles candidatos son:" + System.getProperty("line.separator") + intereses.substring(0, intereses.length()-2);
		}
	}
	if (edad_u != null && !edad_u.isEmpty() && Integer.parseInt(edad_u) > 0){
		UserDAO userdao = new UserDAO();
		status = userdao.update(customerBean.getEmailUser(),edad_u,6,conf,sql);
		if (status != 0)
		%>
			<jsp:setProperty property="edad" value="<%=Integer.parseInt(edad_u) %>" name="customerBean" />
<%	
	}
	/////////////////////////////////////////
	if (status == 0 && interes_comprobado == 0){
		int cont = updateBean.getContador();
		updateBean.setContador(cont++);
		%>
		<jsp:setProperty property="contador" value="<%=cont++%>" name="updateBean" />
<% 
		nextPage = "../view/updateView.jsp";
		mensajeNextPage = "Se ha producido un fallo al actualizar los datos. Vuelva a intentarlo rellenado al menos un campo.";
	}
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message" />
</jsp:forward>