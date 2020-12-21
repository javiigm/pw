<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.LinkedList" %>
<%@ page import ="es.uco.pw.data.dao.UserDAO" %>
<%@ page import ="es.uco.pw.data.dao.AnuncioDAO" %>
<%@ page import ="es.uco.pw.business.anuncio.Anuncio" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

<div class="container">
    <div class="header">
      <img src="./../../../img/log4.png" alt="logo" style="height:120px;width:150px;float:left">
      <div class="nav">
        <a class="active" href="home.jsp">HOME</a>
        <a href="perfil">PERFIL</a>
        <form method="GET" action="logout">
        	<button type="submit" id="cerrar_sesion">CERRAR SESIÓN</button>
        </form>
      </div>
      <form method="GET" action="" style="float:left;">
        <input type="search" id="busq" placeholder="Search.." name="search">
      </form>
    </div>
    <div class="informacion">
      <h3>INFORMACIÓN PERSONAL</h3>
      <table>
        <tr>
          <img src="https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14046.jpg" alt="persona1" width="100" height="100"><br>
        </tr>
        <tr>
          <input type="button" value="EDITAR PERFIL" onclick="window.open('editar_perfil', '_self');"/>
        </tr>
        <tr>
           <ul class="info">
           <!-- Mostramos la informacion del usuario que ha iniciado sesion -->
           <%
            String fichero = getServletContext().getInitParameter("config.properties");
	   		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
	   		String fichero2 = getServletContext().getInitParameter("sql.properties");
	   		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
	   		
            LinkedList<Anuncio> anuncios = AnuncioDAO.mostrarTodosAnuncios (conf,sql) ;
   			for(int i=0; i<anuncios.size(); i++){%>
            <li>IDENTIFICADOR: <%out.println(anuncios.get(i).getIdentificador());%></li>
            <li>FECHA DE PUBLICACION: <%out.println(anuncios.get(i).getFecha_de_publicacion());%></li>
            <li>TITULO: <%out.println(anuncios.get(i).getTitulo());%></li>
            <li>CUERPO: <%out.println(anuncios.get(i).getCuerpo());%></li>
            <li>USUARIO PROPIETARIO: <%out.println(anuncios.get(i).getUsuario_propietario());%></li>
            <li>TIPO: <%out.println(anuncios.get(i).getType());%></li>
            <li>ESTADO: <%out.println(anuncios.get(i).getEstado());%></li>
            <li>TEMA: <%out.println(anuncios.get(i).getTema());%></li>
            <li> </li>
           </ul>
           <%} %>
        </tr>
      </table>
    </div>
    
   

</body>
</html>