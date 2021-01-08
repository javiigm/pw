<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="es.uco.pw.display.javabean.CustomerBean" %>
<%@ page import="es.uco.pw.to.Type" %>
<%@ page import="es.uco.pw.data.dao.InteresDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Formulario Crear Anuncio</title>
	<link rel="stylesheet" href="../../css/index.css">
	<link rel="stylesheet" href="../../css/CrearAnuncio.css">
	<script>
	function valida(){
		var fecha = document.getElementById("fecha_de_nacimiento").value;
		valor = new Date(fecha.substr(0,3),fecha.substr(5,6),fecha.substr(8,9));
		if (!isNaN(valor))
			alert("La fecha es incorrecta");
		else
			window.location.assign("/P3/CrearAnuncio");
	}
	function volver(){
		if (window.location.pathname == "/P3/CrearAnuncio")
			window.location.assign("./mvc/view/home.jsp");
		else
			window.location.assign("./home.jsp");
	}
	</script>
</head>
<body>

	<%
		session = request.getSession();
		CustomerBean cb = (CustomerBean)session.getAttribute("customerBean");
	%>
	
<div class="topnav">
  <a href="/P3/index.jsp">Inicio</a>
  <a href="/P3/mvc/view/aboutView.jsp">Sobre Nosotros</a>
</div>

<div class="container">
  <form method="post" action="/P3/CrearAnuncio" onsubmit="valida()">
    <div class="row">
      <div class="col-25">
        <label for="titulo">Título</label>
      </div>
      <div class="col-75">
		<input type="text" id="titulo" name="titulo" placeholder="Escribe el titulo.." required>
		<input type="hidden" id="usuario_propietario" name="usuario_propietario" value="<%=cb.getEmailUser()%>"><br />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="lname">Fecha de publicación</label>
      </div>
      <div class="col-75">
        <input type="text" id="fecha_de_publicacion" name="fecha_de_publicacion" placeholder="yyyy-mm-dd" required>        
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="country">Tipo de Anuncio</label>
      </div>
      <div class="col-75">
        <select id="tipo" name="tipo" required>
          <option value="general">General</option>
          <option value="tematico">Tematico</option>
          <option value="individualizado ">Individualizado </option>
		  <option value="flash ">Flash </option>

        </select>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="subject">Cuerpo</label>
      </div>
      <div class="col-75">
        <textarea id="cuerpo" name="cuerpo" placeholder="Escribe el anuncio.." style="height:200px" required></textarea>
      </div>
    </div>
    <div class="row">
      <input type="submit" value="Crear Anuncio">
      <input type="button" value="Volver a la pagina inicial" onclick="volver()" />
      
    </div>
  </form>
</div>
		
</body>
</html>