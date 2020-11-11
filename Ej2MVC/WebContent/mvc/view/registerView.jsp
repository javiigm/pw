<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede del controlador /con mensaje 
		b) No hay parámetros en el request -> procede del controlador /sin mensaje
	*/
String nextPage = "../controller/registerController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) messageNextPage = "";

if (customerBean != null && !customerBean.getEmailUser().equals("") && !customerBean.getPassword().equals("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
%>
	<p>Marca la opcion deseada para actualizar los datos de contacto</p>
	
	<form method="post" action="../controller/registerController.jsp">
		<label for="nombre">Nombre: </label>
	<input type="radio" name="nombre"><br/><br/>
	<label for="apellidos">Apellidos: </label>
	<input type="radio" name="apellidos"><br/><br/>
	<label for="fecha_de_nacimiento">Fecha de nacimiento: </label>
	<input type="radio" name="fecha_de_nacimiento" placeholder="yyyy-mm-dd"><br/><br/>
	<label for="password">Password: </label>
	<input type="radio" name="password"><br/><br/>
	<label for="interes">Interes: </label>
	<input type="radio" name="interes"><br/><br/>
	<label for="edad">Edad: </label>
	<input type="radio" name="edad"><br/>
	<br/>
	<input type="submit" value="Submit">
	</form>
} else {
%>
<%= messageNextPage %><br/><br/>
<form method="post" action="../controller/registerController.jsp">
	<label for="nombre">Nombre: </label>
	<input type="text" name="nombre"><br/><br/>
	<label for="apellidos">Apellidos: </label>
	<input type="text" name="apellidos"><br/><br/>
	<label for="fecha_de_nacimiento">Fecha de nacimiento: </label>
	<input type="text" name="fecha_de_nacimiento" placeholder="yyyy-mm-dd"><br/><br/>
	<label for="email">Email: </label>
	<input type="text" name="email"><br/><br/>
	<label for="password">Password: </label>
	<input type="password" name="password"><br/><br/>
	<label for="interes">Interes: </label>
	<input type="text" name="interes"><br/><br/>
	<label for="edad">Edad: </label>
	<input type="text" name="edad"><br/>
	<br/>
	<input type="submit" value="Submit">
</form>
<%
}
%>

</body>
</html>