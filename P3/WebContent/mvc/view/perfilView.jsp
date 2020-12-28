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
<link rel="stylesheet" href="../../css/perfil.css">
<link rel="stylesheet" href="../../css/header.css">
<link rel="stylesheet" href="../../css/footer.css">
</head>
<body>
	<div class="container3">
      <%@ include file="../../includes/header.jsp" %>
      <div class="info-perfil" align=center>
        <h1>MI PERFIL</h1>
        <table>
          <tr align=left>
           <th><img src="https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14046.jpg" alt="persona1" width="200" height="200"> </th>
           <th align="left">
           <%
	        session = request.getSession();
	        CustomerBean customer = (CustomerBean)session.getAttribute("customerBean");
	        %>
             <ul>
              <li>NOMBRE: <%=customer.getNombre() %></li>
              <li>APELLIDOS: <%=customer.getApellidos() %></li>
              <li>FECHA DE NACIMIENTO: <%=customer.getFecha_de_nacimiento() %></li>
              <li>CORREO: <%=customer.getEmailUser() %></li>
              <li>INTERES: <%=customer.getInteres() %></li>
              <li>EDAD: <%=customer.getEdad() %></li>
             </ul>
             <button id="botones" onclick="window.location.assign('./editarPerfilView.jsp');">EDITAR PERFIL</button>
          </tr>
        </table>
      </div>
      
      <div class="mis_anuncios">
      	<h2>Anuncios</h2>
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
        <table class="anuncio">
		    <tr align=left>
           <th><img align="right" src="https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14044.jpg" alt="persona2" width="100" height="100"></th>
	           <th align="left">
	             <ul>
	              <li>TITULO: <%out.println(anuncios.get(i).getTitulo());%></li>
	              <li>CUERPO: <%out.println(anuncios.get(i).getCuerpo());%></li>
	              <li>FECHA DE PUBLICACION: <%out.println(anuncios.get(i).getFecha_de_publicacion());%></li>
	              <%if (anuncios.get(i).getType().equals(Type.flash)) {%>
			           <li>FECHA FIN: <%out.println(anuncios.get(i).getFecha_fin());%></li>
			      <%} %>
	              <li>TIPO: <%out.println(anuncios.get(i).getType()); %></li>
	              <% if (anuncios.get(i).getType().equals(Type.individualizado)) {%>
	              	<li>USUARIOS DESTINATARIOS: <%out.println(anuncios.get(i).getUsuarios_destinatarios()); %></li>
	              <% } %>
	              <li>ESTADO: <%out.println(anuncios.get(i).getEstado()); %></li>
	              <% if (anuncios.get(i).getType().equals(Type.tematico)){ %>
	              <li>TEMA: <%out.println(anuncios.get(i).getTema()); %></li>
	              	<%} %>
	             </ul>
	             <%if (anuncios.get(i).getEstado().equals(Estado.editado)){ 
	         		
	            	 SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
	                 Date fecha_anuncio = objSDF.parse(anuncios.get(i).getFecha_de_publicacion()); 
	                 Date actual = new Date();
	                 %><form method="post" action="/P3/ActualizarEstado">
	                 <%if (fecha_anuncio.compareTo(actual) > 0) {  
	                	 %><button name="en_espera_anuncio" value="<%=anuncios.get(i).getIdentificador()%>">Publicar Anuncio</button><%
	                 }
	                 else {  
	                     %><button name="publicar_anuncio" value="<%=anuncios.get(i).getIdentificador()%>">Publicar Anuncio</button><%
	                 } 
	                 %>
	                 </form>
	             	<button name="editar_anuncio" onclick="window.location.assign('./editarAnuncioView.jsp');">Editar Anuncio</button>
	             <%} else if (anuncios.get(i).getEstado().equals(Estado.editado)) {
	            	 String fichero3 = getServletContext().getInitParameter("config.properties");
	         		 java.io.InputStream conf2 = getServletContext().getResourceAsStream(fichero3);
	         		 String fichero4 = getServletContext().getInitParameter("sql.properties");
	         		 java.io.InputStream sql2 = getServletContext().getResourceAsStream(fichero4);
	         		 
	         		 SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
	                 Date fecha_anuncio = objSDF.parse(anuncios.get(i).getFecha_de_publicacion()); 
	                 Date actual = new Date();
	                 String strDate = objSDF.format(actual);
	                 if (fecha_anuncio.compareTo(objSDF.parse(strDate)) <= 0)
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
	           </th>
        	</tr>
		<% } %>
        </table>
      </div>
	</div>
     
</body>
</html>