<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="es.uco.pw.data.dao.UserDAO" %>
<%@ page import="es.uco.pw.data.dao.AnuncioDAO" %>
<%@ page import="es.uco.pw.business.anuncio.Anuncio" %>
<%@ page import="es.uco.pw.business.user.User" %>
<%@ page import="es.uco.pw.display.javabean.CustomerBean" %>
<%@ page import="es.uco.pw.to.Type" %>
<%@ page import="es.uco.pw.to.Estado" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="ISO-8859-1">
 <link rel="stylesheet" href="../../css/index.css">
 <link rel="stylesheet" href="../../css/header.css">
 <link rel="stylesheet" href="../../css/footer.css">
<title>Home</title>
</head>
<body>

<div class="container">
    <%@ include file="../../includes/header.jsp" %>
    <div>
    <button name="crear_anuncio" onclick="window.location.assign('./crearAnuncioView.jsp');">Crear Anuncio</button>
	    <div class="anuncios">
	      <table>
	        <tr>
	           <%
	           if(cad == null || cad.isEmpty() || cad.length()==0){
	            String fichero = getServletContext().getInitParameter("config.properties");
		   		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		   		String fichero2 = getServletContext().getInitParameter("sql.properties");
		   		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		   		
		   		
	            LinkedList<Anuncio> anuncios = AnuncioDAO.mostrarTodosAnuncios (conf,sql) ;
	   			for(int i=0; i<anuncios.size(); i++){
	   				if(anuncios.get(i).getEstado().equals(Estado.publicado) && 
	   						((anuncios.get(i).getType().equals(Type.individualizado) && anuncios.get(i).getUsuarios_destinatarios().equals(cb.getEmailUser())) ||
	   						anuncios.get(i).getType().equals(Type.general) || anuncios.get(i).getType().equals(Type.flash) || 
	   						(anuncios.get(i).getType().equals(Type.tematico) && anuncios.get(i).getTema().equals(cb.getInteres())) ||
	   						anuncios.get(i).getUsuario_propietario().equals(cb.getEmailUser()))) {%>
		   			 <ul>
			            <li>IDENTIFICADOR: <%out.println(anuncios.get(i).getIdentificador());%></li>
			            <li>FECHA DE PUBLICACION: <%out.println(anuncios.get(i).getFecha_de_publicacion());%></li>
			            <%if (anuncios.get(i).getType().equals(Type.flash)) {%>
			            	<li>FECHA FIN: <%out.println(anuncios.get(i).getFecha_fin());%></li>
			            <%} %>
			            <li>TITULO: <%out.println(anuncios.get(i).getTitulo());%></li>
			            <li>CUERPO: <%out.println(anuncios.get(i).getCuerpo());%></li>
			            <li>USUARIO PROPIETARIO: <%out.println(anuncios.get(i).getUsuario_propietario());%></li>
			            <li>TIPO: <%out.println(anuncios.get(i).getType());%></li>
			            <li>ESTADO: <%out.println(anuncios.get(i).getEstado());%></li>
			            <% if (anuncios.get(i).getType().equals(Type.tematico)){ %>
		              	<li>TEMA: <%out.println(anuncios.get(i).getTema()); %></li>
		              	<%} %>
			         </ul>   
	           <%   }
	             } 
	           }%>
	        </tr>
	      </table>
	    </div>
	</div>
</div>
   

</body>
</html>