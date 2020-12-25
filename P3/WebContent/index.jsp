<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="es.uco.pw.display.javabean.CustomerBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Practica 3 Servlets -- Index</title>
<!-- Load icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/index.css">
</head>
<body>
<%
session = request.getSession();
CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("")) {
	// Usuario no logado -> Se invoca al controlador de la funcionalidad
%>

<div class="topnav">
  <a href="/P3/mvc/view/loginView.jsp">Acceder</a>
  <a href="/P3/mvc/view/registerView.jsp">Registrarse</a>
	<% } else { 
		response.sendRedirect("./home.jsp");
	 } %>  <a href="#contact">Contact</a>
  <a href="#about">About</a>
</div>

<form class="example" action="/action_page.php" style="margin:auto;max-width:300px">
  <input type="text" placeholder="Search.." name="search2">
  <button type="submit"><i class="fa fa-search"></i></button>
</form>

</body>
</html>