<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.data.dao.InteresDAO" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>
	<script>
	function valida(){
		var correo = document.getElementById("email").value;
		if ( (document.getElementById("nombre").value == "") || (document.getElementById("apellidos").value == "")
				(correo == "") || (document.getElementById("password").value == "") || 
				(document.getElementById("interes").value == "") || (document.getElementById("edad").value.text() == "") )
					alert("Debe rellenar todos los campos");
		else if ( !((correo.value.indexOf("@") > 0)) || /[^a-zA-Z0-9\.\@\_\-]/.test(correo))
			alert("Formato de email incorrecto");
		else
			window.location.assign("/P3/ComprobarRegistro");
	}
	function volver(){
		if (window.location.pathname == "/P3/ComprobarRegistro")
			window.location.assign("./index.jsp");
		else
			window.location.assign("../../index.jsp");
	}
	</script>
</head>
<body>
	<form method="post" action="/P3/ComprobarRegistro" onsubmit="valida()">
		<label for="nombre">Nombre: </label> 
		<input type="text" id="nombre" name="nombre" required><br />
		<br /> <label for="apellidos">Apellidos: </label> 
		<input type="text" id="apellidos" name="apellidos" required><br />
		<br /> <label for="fecha_de_nacimiento">Fecha de nacimiento: </label>
		<input type="date" id="fecha_de_nacimiento" name="fecha_de_nacimiento" placeholder="yyyy-mm-dd" required><br />
		<br /> <label for="email">Email: </label> 
		<input type="text" id="email" name="email" required><br />
		<br /> <label for="password">Password: </label> 
		<input type="password" id="password" name="password" required><br />
		<%
		String fichero = application.getInitParameter("config.properties");
		java.io.InputStream conf = application.getResourceAsStream(fichero);
		String fichero2 = application.getInitParameter("sql.properties");
		java.io.InputStream sql = application.getResourceAsStream(fichero2);
		
		InteresDAO idao = new InteresDAO();
		String intereses = idao.consultaInteres(conf, sql);
		%>
		<br />
		<label id="intereses">La lista de intereses posibles son: <%=intereses.substring(0, intereses.length()-2)%></label>
		<br /> <label for="interes">Interes: </label> 
		<input type="text" id="interes" name="interes" required><br />
		<br /> <label for="edad">Edad: </label> 
		<input type="number" id="edad" name="edad" required><br />
		<br /> 
		<input type="submit" value="Registrarse">
		
		<br /><br />
		<input type="button" value="Volver a la pagina inicial" onclick="volver()" />
	</form>

</body>
</html>