<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import ="java.util.LinkedList" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="java.text.ParseException" %>
<%@ page import ="java.util.Date" %>
<%@ page import ="es.uco.pw.data.dao.AnuncioDAO" %>
<%@ page import ="es.uco.pw.display.javabean.CustomerBean" %>
<%@ page import ="es.uco.pw.business.anuncio.Anuncio" %>
<%@ page import ="javax.servlet.http.HttpSession" %>
<%@ page import ="es.uco.pw.to.Type" %>
<%@ page import ="es.uco.pw.to.Estado" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mi perfil</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="../../css/perfil.css">
<link rel="stylesheet" href="../../css/index.css">

</head>

<body>

<div class="topnav">
  <a href="/P3/index.jsp">Inicio</a>
  <a href="#">Crear Anuncio</a>
  <a href="/P3/editarPerfilView.jsp">Editar Perfil</a>
  <a href="#">Cerrar Sesion</a>
  <div class="search-container">
    <form action="/action_page.php">
      <input type="text" placeholder="Search.." name="search">
      <button type="submit"><i class="fa fa-search"></i></button>
    </form>
  </div>
</div>

<div class="row">
  <div class="side">
           <img src="https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14046.jpg" alt="persona1" width="150" height="150">
    <%
	    session = request.getSession();
	    CustomerBean customer = (CustomerBean)session.getAttribute("customerBean");
	 %>
	 
    <h3>Información</h3>
    <p><%=customer.getNombre() %> <%=customer.getApellidos() %>, <%=customer.getEdad() %></p>
    <p><%=customer.getFecha_de_nacimiento() %></p>
    <p><%=customer.getEmailUser() %></p>
    <p><%=customer.getInteres() %></p>
  </div>
  
  <div class="main">
    <h2>Mis Anuncios</h2>
        <%
	    String fichero = getServletContext().getInitParameter("config.properties");
		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		String fichero2 = getServletContext().getInitParameter("sql.properties");
		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		   		
	    LinkedList<Anuncio> anuncios = AnuncioDAO.buscarUsuarioPropietario(customer.getEmailUser(),conf,sql);
	    if (anuncios.size() == 0)
			out.print("No tiene anuncios creados");
	   	for(int i=0; i<anuncios.size(); i++){
	   	%>
	   	
    	<div class="anbackground">
    		<p><%out.println(anuncios.get(i).getTitulo());%></p>
    		<p><%out.println(anuncios.get(i).getCuerpo());%></p>
			<p>
    			<%out.println(anuncios.get(i).getFecha_de_publicacion());%>	
	              <%if (anuncios.get(i).getType().equals(Type.flash)) {%>
			           ---- <%out.println(anuncios.get(i).getFecha_fin());%>
			      <%} %>
   			</p> 	
			<p>
    			Tipo: <%out.println(anuncios.get(i).getType()); %>				
	              <% if (anuncios.get(i).getType().equals(Type.individualizado)) {%>
	              	---- Usuarios Destinatarios: <%out.println(anuncios.get(i).getUsuarios_destinatarios()); %>
	              <% } %>    		</p> 
			<p>
    			Estado: <%out.println(anuncios.get(i).getEstado()); %> 				
	              <% if (anuncios.get(i).getType().equals(Type.tematico)){ %>
	              ---- Tema: <%out.println(anuncios.get(i).getTema()); %>
	              	<%} %>   			  
	        </p> 
	             <%if (anuncios.get(i).getEstado().equals(Estado.editado)){ 
	         		
	            	 SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd"); 
	                 Date fecha_anuncio = objSDF.parse(anuncios.get(i).getFecha_de_publicacion()); 
	                 Date actual = new Date();
	                 String strDate = objSDF.format(actual);
	                 %><form method="post" action="/P3/ActualizarEstado">
	                 <%if (fecha_anuncio.compareTo(objSDF.parse(strDate)) > 0) {  
	                	 %><button name="en_espera_anuncio" value="<%=anuncios.get(i).getIdentificador()%>">Publicar Anuncio</button><%
	                 }
	                 else {  
	                     %><button name="publicar_anuncio" value="<%=anuncios.get(i).getIdentificador()%>">Publicar Anuncio</button><%
	                 } 
	                 %>
	                 </form>
	             	<button name="editar_anuncio" onclick="window.location.assign('./editarAnuncioView.jsp');">Editar Anuncio</button>
	             <%} else if (anuncios.get(i).getEstado().equals(Estado.en_espera)) {
	            	 String fichero3 = getServletContext().getInitParameter("config.properties");
	         		 java.io.InputStream conf2 = getServletContext().getResourceAsStream(fichero3);
	         		 String fichero4 = getServletContext().getInitParameter("sql.properties");
	         		 java.io.InputStream sql2 = getServletContext().getResourceAsStream(fichero4);
	         		 
	         		 SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd"); 
	                 Date fecha_anuncio = objSDF.parse(anuncios.get(i).getFecha_de_publicacion()); 
	                 Date actual = new Date();
	                 String strDate = objSDF.format(actual);
	                 if (fecha_anuncio.compareTo(objSDF.parse(strDate)) > 0)
	                	 AnuncioDAO.update(anuncios.get(i).getIdentificador(), "publicado", conf2, sql2);
	             
	               } else if (anuncios.get(i).getEstado().equals(Estado.publicado)) {%>
		             <form method="post" action="/P3/ActualizarEstado">
		             	<button type="submit" name="archivar_anuncio" value="<%=anuncios.get(i).getIdentificador()%>">Archivar Anuncio</button>
		             </form>
	             <%} else if (anuncios.get(i).getEstado().equals(Estado.archivado)) {%>
	             	<form method="post" action="/P3/ActualizarEstado">
		             	<button name="editado_anuncio" value="<%=anuncios.get(i).getIdentificador()%>">Recuperar Anuncio</button>
		             	<button name="eliminar_anuncio" value="<%=anuncios.get(i).getIdentificador()%>">Eliminar</button>
		             </form>
		         <%} %>
		<% } %>
	        
   	 	</div>
        <br>


  </div>
</div>
</body>

</html>