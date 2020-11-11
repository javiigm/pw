<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean  id="loginBean" scope="session" class="es.uco.pw.display.javabean.LoginBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
</head>
<body>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede del controlador /con mensaje 
		b) No hay parámetros en el request -> procede del controlador /sin mensaje
	*/
String nextPage = "../controller/loginController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) messageNextPage = "";


if(loginBean.getContador() == 3)
	response.sendRedirect("http://www.uco.es");
else {
	if (customerBean != null && !customerBean.getEmailUser().equals("") && !customerBean.getPassword().equals("") && !customerBean.getInteres().equals("")) {
		//No debería estar aquí -> flujo salta a index.jsp
		nextPage = "../../index.jsp";
	}
	%>
	<%= messageNextPage %><br/><br/>
	<form method="post" action="../controller/loginController.jsp">
		<label for="email">Email: </label>
		<input type="text" name="email"><br/><br/>
		<label for="password">Password: </label>
		<input type="password" name="password"><br/>
		<br/>
		<input type="submit" value="Submit">
	</form>
<%
}
%>

</body>
</html>