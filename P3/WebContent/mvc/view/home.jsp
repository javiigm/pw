<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="java.text.ParseException" %>
<%@ page import ="java.util.Date" %>
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
 <title>Home</title>
</head>
<body>

<%@ include file="../../includes/header.jsp" %>
<div class="container">
    <div>
    <button name="crear_anuncio" onclick="window.location.assign('./crearAnuncioView.jsp');">Crear Anuncio</button>
	    <div class="anuncios">
	      <table>
	        <tr>
	           <%
	            String filtros_tablon = (String)session.getAttribute("filtro");
	           //Comprobamos si hay una busqueda realizada sobre el buscador interno(la barra de busqueda)
	           if(cad == null || cad.isEmpty() || cad.length()==0){
	            String fichero = getServletContext().getInitParameter("config.properties");
		   		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		   		String fichero2 = getServletContext().getInitParameter("sql.properties");
		   		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		   		
		   		
	            LinkedList<Anuncio> anuncios = AnuncioDAO.mostrarTodosAnuncios (conf,sql) ;
	   			for(int i=0; i<anuncios.size(); i++){
	   				//Actualizamos el estado si este se encuentra en espera y ya ha pasado la fecha de publicacion
	   				if (anuncios.get(i).getEstado().equals(Estado.en_espera)) {
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
		               }
	   				//Comprobamos los tiempos para el tipo flash
	   				if (anuncios.get(i).getType().equals(Type.flash)) {
		            	 String fichero3 = getServletContext().getInitParameter("config.properties");
		         		 java.io.InputStream conf2 = getServletContext().getResourceAsStream(fichero3);
		         		 String fichero4 = getServletContext().getInitParameter("sql.properties");
		         		 java.io.InputStream sql2 = getServletContext().getResourceAsStream(fichero4);
		         		 
		         		 SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd"); 
		                 Date fecha_fin_anuncio = objSDF.parse(anuncios.get(i).getFecha_fin()); 
		                 Date actual = new Date();
		                 String strDate = objSDF.format(actual);
		                 if (fecha_fin_anuncio.compareTo(objSDF.parse(strDate)) <= 0)
		                	 AnuncioDAO.update(anuncios.get(i).getIdentificador(), "archivado", conf2, sql2); 
		               }
	   				
	   				//Comprobamos que no se haya realizado el filtrado(desplegable filtrar) de los anuncios visibles en el tablon(publicados, destinados o propios)
	   				if((filtros_tablon == null || filtros_tablon.isEmpty()) && anuncios.get(i).getEstado().equals(Estado.publicado) && 
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
			            <%if (anuncios.get(i).getUsuarios_destinatarios().equals(cb.getEmailUser())){ %>
			            <li>USUARIO DESTINATARIO: <%out.println(anuncios.get(i).getUsuarios_destinatarios()); %></li>
			            <%} %>
			            <li>TIPO: <%out.println(anuncios.get(i).getType());%></li>
			            <li>ESTADO: <%out.println(anuncios.get(i).getEstado());%></li>
			            <% if (anuncios.get(i).getType().equals(Type.tematico)){ %>
		              	<li>TEMA: <%out.println(anuncios.get(i).getTema()); %></li>
		              	<%} %>
			         </ul>   
	           <%   }
	   				//Caso contrario. Si se ha realizado el filtrado
	   				if(filtros_tablon != null && !filtros_tablon.isEmpty() && anuncios.get(i).getEstado().equals(Estado.publicado) && 
	   						((anuncios.get(i).getType().equals(Type.individualizado) && anuncios.get(i).getUsuarios_destinatarios().equals(cb.getEmailUser())) ||
	   						anuncios.get(i).getType().equals(Type.general) || anuncios.get(i).getType().equals(Type.flash) || 
	   						(anuncios.get(i).getType().equals(Type.tematico) && anuncios.get(i).getTema().equals(cb.getInteres())) ||
	   						anuncios.get(i).getUsuario_propietario().equals(cb.getEmailUser()))) {
	   					if(anuncios.get(i).getType().toString().equals(filtros_tablon)){%>
	   						<ul>
					            <li>IDENTIFICADOR: <%out.println(anuncios.get(i).getIdentificador());%></li>
					            <li>FECHA DE PUBLICACION: <%out.println(anuncios.get(i).getFecha_de_publicacion());%></li>
					            <%if (anuncios.get(i).getType().equals(Type.flash)) {%>
					            	<li>FECHA FIN: <%out.println(anuncios.get(i).getFecha_fin());%></li>
					            <%} %>
					            <li>TITULO: <%out.println(anuncios.get(i).getTitulo());%></li>
					            <li>CUERPO: <%out.println(anuncios.get(i).getCuerpo());%></li>
					            <li>USUARIO PROPIETARIO: <%out.println(anuncios.get(i).getUsuario_propietario());%></li>
					            <%if (anuncios.get(i).getUsuarios_destinatarios().equals(cb.getEmailUser())){ %>
					            <li>USUARIO DESTINATARIO: <%out.println(anuncios.get(i).getUsuarios_destinatarios()); %></li>
					            <%} %>
					            <li>TIPO: <%out.println(anuncios.get(i).getType());%></li>
					            <li>ESTADO: <%out.println(anuncios.get(i).getEstado());%></li>
					            <% if (anuncios.get(i).getType().equals(Type.tematico)){ %>
				              	<li>TEMA: <%out.println(anuncios.get(i).getTema()); %></li>
				              	<%} %>
					        </ul>  
	   					<%}
	   					if(anuncios.get(i).getTema().equals(filtros_tablon)){%>
   						<ul>
				            <li>IDENTIFICADOR: <%out.println(anuncios.get(i).getIdentificador());%></li>
				            <li>FECHA DE PUBLICACION: <%out.println(anuncios.get(i).getFecha_de_publicacion());%></li>
				            <%if (anuncios.get(i).getType().equals(Type.flash)) {%>
				            	<li>FECHA FIN: <%out.println(anuncios.get(i).getFecha_fin());%></li>
				            <%} %>
				            <li>TITULO: <%out.println(anuncios.get(i).getTitulo());%></li>
				            <li>CUERPO: <%out.println(anuncios.get(i).getCuerpo());%></li>
				            <li>USUARIO PROPIETARIO: <%out.println(anuncios.get(i).getUsuario_propietario());%></li>
				            <%if (anuncios.get(i).getUsuarios_destinatarios().equals(cb.getEmailUser())){ %>
				            <li>USUARIO DESTINATARIO: <%out.println(anuncios.get(i).getUsuarios_destinatarios()); %></li>
				            <%} %>
				            <li>TIPO: <%out.println(anuncios.get(i).getType());%></li>
				            <li>ESTADO: <%out.println(anuncios.get(i).getEstado());%></li>
				            <% if (anuncios.get(i).getType().equals(Type.tematico)){ %>
			              	<li>TEMA: <%out.println(anuncios.get(i).getTema()); %></li>
			              	<%} %>
				        </ul>  
   					<%}
	   				}
	             } 
	           } else {//Se ha realizado una busqueda interna. Los resultados se muestran en el home, obviando los que salen por primera vez
	           	LinkedList<Anuncio> anuncios = null;
	           	String fichero = getServletContext().getInitParameter("config.properties");
		   		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		   		String fichero2 = getServletContext().getInitParameter("sql.properties");
		   		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
	           	//Comprobamos el tipo de filtro aplicado
				if (filtro_interno.equals("fecha_de_publicacion"))
					anuncios = AnuncioDAO.buscarFecha(cad, conf, sql);
				else if (filtro_interno.equals("usuario_propietario"))
					anuncios = AnuncioDAO.buscarUsuarioPropietario(cad, conf, sql);
				else if (filtro_interno.equals("usuario_destinatario"))
					anuncios = AnuncioDAO.buscarUsuarioDestinatario(cad, conf, sql);
				else if(filtro_interno.equals("tematica"))
					anuncios = AnuncioDAO.buscarTemas(cad, conf, sql);
				
				if (anuncios.size() == 0)
					out.print("No existen coincidencias para esta busqueda");
				else { //En caso de que existan anuncios los muestra %>
					<div class="anuncios">
				  	<%for(int i=0; i<anuncios.size(); i++){%>
				  		<ul>
				            <li>IDENTIFICADOR: <%out.println(anuncios.get(i).getIdentificador());%></li>
				            <li>FECHA DE PUBLICACION: <%out.println(anuncios.get(i).getFecha_de_publicacion());%></li>
				            <%if (anuncios.get(i).getType().equals(Type.flash)) {%>
				            	<li>FECHA FIN: <%out.println(anuncios.get(i).getFecha_fin());%></li>
				            <%} %>
				            <li>TITULO: <%out.println(anuncios.get(i).getTitulo());%></li>
				            <li>CUERPO: <%out.println(anuncios.get(i).getCuerpo());%></li>
				            <li>USUARIO PROPIETARIO: <%out.println(anuncios.get(i).getUsuario_propietario());%></li>
				            <%if (anuncios.get(i).getUsuarios_destinatarios().equals(cb.getEmailUser())){ %>
				            <li>USUARIO DESTINATARIO: <%out.println(anuncios.get(i).getUsuarios_destinatarios()); %></li>
				            <%} %>
				            <li>TIPO: <%out.println(anuncios.get(i).getType());%></li>
				            <li>ESTADO: <%out.println(anuncios.get(i).getEstado());%></li>
				            <% if (anuncios.get(i).getType().equals(Type.tematico)){ %>
			              	<li>TEMA: <%out.println(anuncios.get(i).getTema()); %></li>
			              	<%} %>
				         </ul>   
				         <%}
				  		}
				  	}%>
	        </tr>
	      </table>
	    </div>
	</div>
</div>
   

</body>
</html>