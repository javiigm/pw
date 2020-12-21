<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session"
	class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="updateBean" scope="session"
	class="es.uco.pw.display.javabean.UpdateBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update</title>
</head>
<body>
	<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede del controlador /con mensaje 
		b) No hay parámetros en el request -> procede del controlador /sin mensaje
	*/
String nextPage = "../controller/updateController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) messageNextPage = "";

if (customerBean != null && !customerBean.getEmailUser().equals("") && !customerBean.getPassword().equals("") && !customerBean.getInteres().equals("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	//nextPage = "../../index.jsp";
	if(updateBean.getContador() > 1){%>
	<%= messageNextPage %><br />
	<br />
	<% } %>

	<p>Rellena los campos deseados para actualizar los datos de
		contacto</p>

	<form method="post" action="../controller/updateController.jsp">
		<label for="nombre_u">Nombre: </label> <input type="text"
			name="nombre_u"><br />
		<br /> <label for="apellidos_u">Apellidos: </label> <input type="text"
			name="apellidos_u"><br />
		<br /> <label for="fecha_de_nacimiento_u">Fecha de nacimiento:
		</label> <input type="text" name="fecha_de_nacimiento_u"
			placeholder="yyyy-mm-dd"><br />
		<br /> <label for="password_u">Password: </label> <input
			type="password" name="password_u"><br />
		<br /> <label for="interes_u">Interes: </label> <input type="text"
			name="interes_u"><br />
		<br /> <label for="edad_u">Edad: </label> <input type="number"
			name="edad_u"><br /> <br /> <input type="submit"
			formnovalidate="formnovalidate" value="Submit">
	</form>
	<br />
	<form action="../../index.jsp">
		<input type="submit" value="Volver a la pagina inicial" />
	</form>

	<%
} 
%>

</body>
</html>