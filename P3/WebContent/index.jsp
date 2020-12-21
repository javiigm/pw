<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session"
	class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Practica 3 Servlets -- Index</title>
</head>
<body>
<%
if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("")) {
	// Usuario no logado -> Se invoca al controlador de la funcionalidad
%>
	<a href="/P3/mvc/view/loginView.jsp">Acceder</a>
	<br />
	<a href="/P3/mvc/view/registerView.jsp">Registrarse</a>
	<% } else { 
		response.sendRedirect("/home.jsp");
	 } %>

</body>
</html>