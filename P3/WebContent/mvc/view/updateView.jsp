<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.data.dao.InteresDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Update</title>
	<link rel="stylesheet" href="../../css/index.css">
	<link rel="stylesheet" href="../../css/CrearAnuncio.css">
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


<div class="topnav">
  <a href="/P3/index.jsp">Inicio</a>
  <a href="/P3/mvc/view/aboutView.jsp">Sobre Nosotros</a>
</div>

<div class="container">
	<p>Rellena los campos deseados para actualizar los datos de contacto</p>
	<form method="post" action="/P3/EditarPerfil" onsubmit="valida()">
    <div class="row">
      <div class="col-25">
		<label for="nombre">Nombre: </label> 
      </div>
      <div class="col-75">
		<input type="text" id="nombre" name="nombre" required><br />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
		<label for="apellidos">Apellidos: </label> 
      </div>
      <div class="col-75">
		<input type="text" id="apellidos" name="apellidos" required><br />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
		<br /> <label for="fecha_de_nacimiento">Fecha de nacimiento: </label>
      </div>
      <div class="col-75">
		<input type="date" id="fecha_de_nacimiento" name="fecha_de_nacimiento" placeholder="yyyy-mm-dd" required><br />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
		<label for="email">Email: </label> 
      </div>
      <div class="col-75">
		<input type="text" id="email" name="email" required><br />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
		<label for="password">Password: </label> 
      </div>
      <div class="col-75">
		<input type="password" id="password" name="password" required><br />
      </div>
    </div>
    	<%
		String fichero = application.getInitParameter("config.properties");
		java.io.InputStream conf = application.getResourceAsStream(fichero);
		String fichero2 = application.getInitParameter("sql.properties");
		java.io.InputStream sql = application.getResourceAsStream(fichero2);
		
		InteresDAO idao = new InteresDAO();
		String intereses = idao.consultaInteres(conf, sql);
		%>
      <div class="row">
      <div class="col-25">
      	<label id="intereses">La lista de intereses posibles son: <%=intereses.substring(0, intereses.length()-2)%></label>
      </div>
      <div class="col-75">
		<input type="text" id="interes" name="interes" required><br />
      </div>
    </div>  
    <div class="row">
      <div class="col-25">
		<label for="edad">Edad: </label> 
      </div>
      <div class="col-75">
		<input type="number" id="edad" name="edad" required><br />
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