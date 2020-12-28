<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="es.uco.pw.display.javabean.CustomerBean" %>
<%@ page import="es.uco.pw.data.dao.AnuncioDAO" %>
<%@ page import="es.uco.pw.business.anuncio.Anuncio" %>
<%@ page import="es.uco.pw.to.Type" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
<link rel="stylesheet" href="../css/header.css">
<script>
function mostrarFiltros(boton){
	if(boton.innerText=="MOSTRAR FILTROS"){
		boton.innerText="OCULTAR FILTROS"
	}
	else{
		boton.innerText="MOSTRAR FILTROS"
	}

  var x = document.getElementById("filtros");
  if (x.style.display === "block") {
    x.style.display = "none";
  } else {
    x.style.display = "block";
  }
}
function filtrar(){
	if(!document.querySelector('input[name="filtro"]:checked')) {
		alert('Error, elige un filtro para poder buscar');
	} else {
		if (document.getElementsByName("filtro").value == "email")
			
	}
}

</script>
</head>
<body>
	<div class="header">
      <img src="./../../../img/log4.png" alt="logo" style="height:120px;width:150px;float:left">
      <div class="nav">
        <a class="active" href="home.jsp">HOME</a>
        <a href="perfilView.jsp">PERFIL</a>
        <!-- Mostramos la informacion del usuario que ha iniciado sesion -->
        <div style="float:right">
        	<%
	        session = request.getSession();
	        CustomerBean cb = (CustomerBean)session.getAttribute("customerBean");
	        out.println(cb.getNombre() + " " + cb.getApellidos());
	        %>
	        <form method="GET" action="../../Logout">
	        	<button type="submit" id="cerrar_sesion">CERRAR SESIÓN</button>
	        </form>
        </div>
      </div>
    </div>
      <form method="GET" action="" style="float:left">
        <input type="search" id="busq" placeholder="Search..." name="search">
      </form>
      <%String cad = request.getParameter("search"); %>
      <div class="anuncios">
    	<table align=center style="width:100%">
    		<%if(cad != null && !cad.isEmpty() && cad.length()!=0){
    			String fichero3 = getServletContext().getInitParameter("config.properties");
		   		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero3);
		   		String fichero4 = getServletContext().getInitParameter("sql.properties");
		   		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero4);
		   		
    			LinkedList<Anuncio> anuncios = AnuncioDAO.filtrarAnuncios(cad, conf, sql);
    			if (anuncios.size() == 0)
    				out.print("No existen coincidencias para esta busqueda");
    		  	for(int i=0; i<anuncios.size(); i++){%>
    		  <tr>
    			<th id="izq"><img src="https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14046.jpg" alt="persona<%out.println(anuncios.get(i).getIdentificador());%>" width="100" height="100"></th>
    			<th align="left">
            	   <ul>
		            <li>IDENTIFICADOR: <%out.println(anuncios.get(i).getIdentificador());%></li>
		            <li>FECHA DE PUBLICACION: <%out.println(anuncios.get(i).getFecha_de_publicacion());%></li>
		            <li>TITULO: <%out.println(anuncios.get(i).getTitulo());%></li>
		            <li>CUERPO: <%out.println(anuncios.get(i).getCuerpo());%></li>
		            <li>USUARIO PROPIETARIO: <%out.println(anuncios.get(i).getUsuario_propietario());%></li>
		            <li>TIPO: <%out.println(anuncios.get(i).getType());%></li>
		            <li>ESTADO: <%out.println(anuncios.get(i).getEstado());%></li>
		            <% if (anuncios.get(i).getType().equals(Type.tematico)){ %>
	              	<li>TEMA: <%out.println(anuncios.get(i).getTema()); %></li>
	              	<%} %>
		         </ul>   
            	</th>
              </tr>
             <%}
    		 }%>
         </table>
      </div>
      
      <!-- <table align="center">
      <tr>
        <th>
          <button onclick="mostrarFiltros(this)">MOSTRAR FILTROS</button>
        </th>
        <th id="filtros">
        <form action="" method="get" onsubmit="filtrar()">
          <input type="radio" name="filtro" value="email"> Email
          <input type="radio" name="filtro" value="fecha_de_publicacion"> Fecha de publicacion
          <input type="radio" name="filtro" value="usuario_propietario"> Usuario propietario
          <input type="radio" name="filtro" value="usuario_destinatario"> Usuario destinatario
          <input type="radio" name="filtro" value="estado"> Estado
          <input type="radio" name="filtro" value="identificador"> Identificador
 		   <button type="submit" >FILTRAR</button>
        </form>  
        </th>
        </tr>
      </table> -->
</body>
</html>