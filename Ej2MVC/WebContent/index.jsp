<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejercicio 2 MVC</title>
</head>
<body>

<%
if (request.getParameter("reset") != null) {
%>
<jsp:setProperty property="emailUser" value="" name="customerBean"/>
<jsp:setProperty property="password" value="" name="customerBean"/>
<%
}
if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("") || customerBean.getInteres().equals("")) {
	// Usuario no logado -> Se invoca al controlador de la funcionalidad
%>
<a href="/Ej2MVC/mvc/controller/loginController.jsp">Acceder</a>
<br/>
<a href="/Ej2MVC/mvc/controller/registerController.jsp">Registrarse</a>
<% } else { %>
	<p>¡¡Bienvenido <jsp:getProperty property="nombre" name="customerBean"/> <jsp:getProperty property="apellidos" name="customerBean"/>!!</p> 
	<br/>
	<a href="/Ej2MVC/mvc/controller/logoutController.jsp">Desconectar</a>
<% } %>

</body>
</html>