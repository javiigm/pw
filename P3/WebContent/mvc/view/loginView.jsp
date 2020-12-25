<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>	
	<link rel="stylesheet" href="../../css/loginView.css">
	<script>
	function valida(){
		var correo = document.getElementById("email").value;
		if ((correo == "") || (document.getElementById("password").value == ""))
			alert("Debe rellenar todos los campos");
		else if ( !((correo.value.indexOf("@") > 0)) || /[^a-zA-Z0-9\.\@\_\-]/.test(correo)){
			alert("Formato de email incorrecto");
		}
		else{
			window.location.assign("/P3/ComprobarLogin");
		}
	}
	function volver(){
		if (window.location.pathname == "/P3/ComprobarLogin")
			window.location.assign("./index.jsp");
		else
			window.location.assign("../../index.jsp");
	}
	</script>
</head>
<body>	
	<form method="post" action="/P3/ComprobarLogin" onsubmit="valida()">
	
	  <div class="container">
		<label for="email"><b>Email </b></label>
		<input type="text" placeholder="Enter Email" id="email" name="email" required><br />
		<br /> 
		
		<label for="password"><b>Password</b></label>
		<input type="password" placeholder="Enter Password" id="password" name="password" required><br /> 
		<br /> 
		
		<input type="submit" value="Entrar">
		
		<label>
			<input type="checkbox" checked="checked" name="remember"> Remember me
    	</label>
		
		<br /><br />
		<input type="button" value="Volver a la pagina inicial" onclick="volver()" />
		
	   </div>

	</form>

</body>
</html>