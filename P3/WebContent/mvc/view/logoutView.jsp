<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session"
	class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log out</title>
</head>
<body>
	<%
String nextPage = "../controller/logoutController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) messageNextPage = "";

//Se comprueba primero que el usuario no está logado

if ((customerBean.getEmailUser().equals("") && customerBean.getPassword().equals("")) || customerBean == null) { %>
	<%=messageNextPage %>
	<br />
	<br />
	<a href="../../index.jsp">Volver a la pagina inicial</a>
	<%} %>
</body>
</html>