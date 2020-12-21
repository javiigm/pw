<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session"
	class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="loginBean" scope="session"
	class="es.uco.pw.display.javabean.LoginBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>	
</head>
<body>
	
	
	
	<form method="post" action="/P3/ComprobarLogin">
		<label for="email">Email: </label> <input type="text" name="email"><br />
		<br /> <label for="password">Password: </label> <input type="password"
			name="password"><br /> <br /> <input type="submit"
			value="Entrar">
		<br /><br />
	</form>
	<form>
		<input type="button" value="Volver a la pagina inicial" onclick="../../index.jsp" />
	</form>

</body>
</html>