<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.anuncio.Anuncio" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Editar Anuncio</title>
	<script>
	function valida(){
		var fecha = document.getElementById("fecha_de_publicacion").value;
		valor = new Date(fecha.substr(0,3),fecha.substr(5,6),fecha.substr(8,9));
		if (!isNaN(valor))
			alert("La fecha es incorrecta");
		else
			window.location.assign("/P3/EditarAnuncio");
	}
	function volver(){
		if (window.location.pathname == "/P3/EditarAnuncio")
			window.location.assign("./mvc/view/perfilView.jsp");
		else
			window.location.assign("./perfilView.jsp");
	}
	</script>
</head>
<body>
	<%session = request.getSession();
	  Anuncio a = (Anuncio)session.getAttribute("anuncio");
	%>
	<form method="post" action="/P3/EditarAnuncio" >
		<label for="titulo">Titulo: </label> 
		<input type="text" id="titulo" name="titulo" value="<%=a.getTitulo() %>"><br />
		<br /> 
		<label for="cuerpo">Cuerpo: </label>
		<textarea id="cuerpo" name="cuerpo"><%=a.getCuerpo() %></textarea><br />
		<br />
		<label for="fecha_de_publicacion">Fecha de publicacion: </label> 
		<input type="date" id="fecha_de_publicacion" name="fecha_de_publicacion" placeholder="yyyy-mm-dd" value="<%=a.getFecha_de_publicacion()%>"><br />
		<br /> 
		<input type="submit" value="Actualizar">
		
		<br /><br />
		<input type="button" value="Volver al perfil" onclick="volver()" />
	</form>

</body>
</html>