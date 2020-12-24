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
<!DOCTYPE html>
<html>
<head>
 <meta charset="ISO-8859-1">
 <link rel="stylesheet" href="css/index.css">
 <link rel="stylesheet" href="css/header.css">
 <link rel="stylesheet" href="css/footer.css">
<title>Home</title>
</head>
<body>

<div class="container">
    <div class="header">
      <img src="./../../../img/log4.png" alt="logo" style="height:120px;width:150px;float:left">
      <div class="nav">
        <a class="active" href="home.jsp">HOME</a>
        <a href="./mvc/view/perfilView.jsp">PERFIL</a>
        <!-- Mostramos la informacion del usuario que ha iniciado sesion -->
        <div style="float:right">
        	<%
	        session = request.getSession();
	        CustomerBean cb = (CustomerBean)session.getAttribute("customerBean");
	        out.println(cb.getNombre() + " " + cb.getApellidos());
	        %>
	        <form method="GET" action="Logout">
	        	<button type="submit" id="cerrar_sesion">CERRAR SESIÓN</button>
	        </form>
        </div>
      </div>
      <form method="GET" action="" style="float:left">
        <input type="search" id="busq" placeholder="Search..." name="search">
      </form>
    </div>
    <div>
	    <div class="muro">
	      <table>
	        <tr>
	           <%
	            String fichero = getServletContext().getInitParameter("config.properties");
		   		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		   		String fichero2 = getServletContext().getInitParameter("sql.properties");
		   		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		   		
	            LinkedList<Anuncio> anuncios = AnuncioDAO.mostrarTodosAnuncios (conf,sql) ;
	   			for(int i=0; i<anuncios.size(); i++){%>
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
		         
	           <%} %>
	        </tr>
	      </table>
	    </div>
	</div>
</div>
   

</body>
</html>