<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.data.dao.InteresDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Update</title>
	<script>
	function valida(){
		var fecha = document.getElementById("fecha_de_nacimiento").value;
		valor = new Date(fecha.substr(0,3),fecha.substr(5,6),fecha.substr(8,9));
		if (valor != null && !isNaN(valor))
			alert("La fecha es incorrecta");
		else
			window.location.assign("/P3/EditarPerfil");
	}
	function volver(){
		if (window.location.pathname == "/P3/EditarPerfil")
			window.location.assign("./mvc/view/perfilView.jsp");
		else
			window.location.assign("./perfilView.jsp");
	}
	</script>
</head>
<body>

	<p>Rellena los campos deseados para actualizar los datos de contacto</p>

	<form method="post" action="/P3/EditarPerfil" onsubmit="valida()">
		<label for="nombre">Nombre: </label> 
		<input type="text" name="nombre"><br />
		<br /> 
		<label for="apellidos">Apellidos: </label> 
		<input type="text" name="apellidos"><br />
		<br /> <label for="fecha_de_nacimiento">Fecha de nacimiento:</label> 
		<input type="text" name="fecha_de_nacimiento" placeholder="yyyy-mm-dd"><br />
		<br /> <label for="password">Password: </label> 
		<input type="password" name="password"><br />
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
		<br /> 
		<label for="interes">Interes: </label> <input type="text" name="interes"><br />
		<br /> 
		<label for="edad">Edad: </label> <input type="number" name="edad"><br /> <br /> 
		<input type="submit" value="Actualizar"><br />
		<br />
		<input type="submit" value="Volver al perfil" />
	</form>
	

</body>
</html>