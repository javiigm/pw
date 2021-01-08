<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.anuncio.Anuncio" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Editar Anuncio</title>
	<link rel="stylesheet" href="../../css/index.css">
	<link rel="stylesheet" href="../../css/CrearAnuncio.css">
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

<div class="topnav">
  <a href="/P3/index.jsp">Inicio</a>
  <a href="/P3/mvc/view/aboutView.jsp">Sobre Nosotros</a>
</div>

<div class="container">
	<form method="post" action="/P3/EditarAnuncio" >
    <div class="row">
      <div class="col-25">
        <label for="titulo">Título</label>
      </div>
      <div class="col-75">
		<input type="text" id="titulo" name="titulo" value="<%=a.getTitulo() %>"><br />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="lname">Fecha de publicación</label>
      </div>
      <div class="col-75">
		<input type="date" id="fecha_de_publicacion" name="fecha_de_publicacion" placeholder="yyyy-mm-dd" value="<%=a.getFecha_de_publicacion()%>"><br />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="subject">Cuerpo</label>
      </div>
      <div class="col-75">
        <textarea id="cuerpo" name="cuerpo"<%=a.getCuerpo() %> placeholder="Escribe el anuncio.." style="height:200px" required></textarea>        
      </div>
    </div>
    <div class="row">
		<input type="submit" value="Actualizar">
      <input type="button" value="Volver a la pagina inicial" onclick="volver()" />
      
    </div>
  </form>
</div>




</body>
</html>