<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session"
	class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	
	<form method="post" action="/P3/ComprobarRegistro">
		<label for="nombre">Nombre: </label> <input type="text" name="nombre"><br />
		<br /> <label for="apellidos">Apellidos: </label> <input type="text"
			name="apellidos"><br />
		<br /> <label for="fecha_de_nacimiento">Fecha de nacimiento: </label>
		<input type="text" name="fecha_de_nacimiento" placeholder="yyyy-mm-dd"><br />
		<br /> <label for="email">Email: </label> <input type="text"
			name="email"><br />
		<br /> <label for="password">Password: </label> <input type="password"
			name="password"><br />
		<br /> <label for="interes">Interes: </label> <input type="text"
			name="interes"><br />
		<br /> <label for="edad">Edad: </label> <input type="number" name="edad"><br />
		<br /> <input type="submit" value="Registrarse">
		
		<br /><br />
		<input type="button" value="Volver a la pagina inicial" onclick="../../index.jsp" />
	</form>
	
}

</body>
</html>