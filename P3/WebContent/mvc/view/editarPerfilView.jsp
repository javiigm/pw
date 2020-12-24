<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.data.dao.InteresDAO" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Editar Perfil</title>
	<%
		String fichero = application.getInitParameter("config.properties");
		java.io.InputStream conf = application.getResourceAsStream(fichero);
		String fichero2 = application.getInitParameter("sql.properties");
		java.io.InputStream sql = application.getResourceAsStream(fichero2);
		
		InteresDAO idao = new InteresDAO();
		String intereses = idao.consultaInteres(conf, sql);
	%>
	<script>
	function valida(){
		var interes = "<%=intereses.substring(0, intereses.length()-2) %>";
		if (!interes.includes(document.getElementById("interes").value)) {
			alert("El interes no coincide");
		}
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
	
	<form method="post" action="/P3/EditarPerfil" onsubmit="valida()" >
		<label for="nombre">Nombre: </label> 
		<input type="text" id="nombre" name="nombre"><br />
		<br /> 
		<label for="apellidos">Apellidos: </label> 
		<input type="text" id="apellidos" name="apellidos"><br />
		<br /> 
		<label for="fecha_de_nacimiento">Fecha de nacimiento: </label>
		<input type="date" id="fecha_de_nacimiento" name="fecha_de_nacimiento" placeholder="yyyy-mm-dd"><br />
		<br />
		<label for="password">Password: </label> 
		<input type="password" id="password" name="password"><br />
		<br />
		<label id="intereses">La lista de intereses posibles son: <%=intereses.substring(0, intereses.length()-2)%></label>
		<br /> <label for="interes">Interes: </label> 
		<input type="text" id="interes" name="interes"><br />
		<br /> <label for="edad">Edad: </label> 
		<input type="text" id="edad" name="edad"><br />
		<br /> 
		<input type="submit" value="Actualizar">
		
		<br /><br />
		<input type="button" value="Volver al perfil" onclick="volver()" />
	</form>

</body>
</html>