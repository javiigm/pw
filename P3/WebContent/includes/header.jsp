<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="es.uco.pw.display.javabean.CustomerBean" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
<link rel="stylesheet" href="../css/header.css">
</head>
<body>
<div class="header">
      <img src="./../../../img/log4.png" alt="logo" style="height:120px;width:150px;float:left">
      <div class="nav">
        <a class="active" href="home.jsp">HOME</a>
        <a href="perfilView.jsp">PERFIL</a>
        <!-- Mostramos la informacion del usuario que ha iniciado sesion -->
        <div style="float:right">
        	<%
	        session = request.getSession();
	        CustomerBean cb = (CustomerBean)session.getAttribute("customerBean");
	        out.println(cb.getNombre() + " " + cb.getApellidos());
	        %>
	        <form method="GET" action="../../Logout">
	        	<button type="submit" id="cerrar_sesion">CERRAR SESIÓN</button>
	        </form>
        </div>
      </div>
      <form method="GET" action="" style="float:left">
        <input type="search" id="busq" placeholder="Search..." name="search">
      </form>
    </div>
</body>
</html>