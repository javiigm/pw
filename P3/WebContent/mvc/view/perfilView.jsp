<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.LinkedList" %>
<%@ page import ="es.uco.pw.data.dao.AnuncioDAO" %>
<%@ page import ="es.uco.pw.display.javabean.CustomerBean" %>
<%@ page import ="es.uco.pw.business.anuncio.Anuncio" %>
<%@ page import ="javax.servlet.http.HttpSession" %>
<%@ page import ="es.uco.pw.to.Type" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mi perfil</title>
<link rel="stylesheet" href="css/perfil.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
</head>
<body>
	<div class="container3">
      <div class="header">
        <img src="img/log4.png" alt="logo" style="height:120px;width:150px;float:left">
        <div class="nav">
          <a href="../../home.jsp">HOME</a>
          <a class="active" href="perfilView.jsp">PERFIL</a>
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
        <form method="GET" action="" style="float:left;">
          <input type="search" id="busq" placeholder="Search.." name="search">
        </form>
      </div>

      <div class="info-perfil">
        <h1>MI PERFIL</h1>
        <table align=center>
          <tr align=left>
           <th><img src="https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14046.jpg" alt="persona1" width="200" height="200"> </th>
           <th align="left">
             <ul>
              <li>NOMBRE: <%=cb.getNombre() %></li>
              <li>APELLIDOS: <%=cb.getApellidos() %></li>
              <li>FECHA DE NACIMIENTO: <%=cb.getFecha_de_nacimiento() %></li>
              <li>CORREO: <%=cb.getEmailUser() %></li>
              <li>INTERES: <%=cb.getInteres() %></li>
              <li>EDAD: <%=cb.getEdad() %></li>
             </ul>
            <th id="botones">
            	<button onclick="window.location.assign('./editarPerfilView.jsp');">EDITAR PERFIL</button>
           </th>
          </tr>
        </table>
      </div>
      

      <div class="mis_anuncios"><h2>Anuncios</h2>
        <%
	    String fichero = getServletContext().getInitParameter("config.properties");
		java.io.InputStream conf = getServletContext().getResourceAsStream(fichero);
		String fichero2 = getServletContext().getInitParameter("sql.properties");
		java.io.InputStream sql = getServletContext().getResourceAsStream(fichero2);
		   		
	    LinkedList<Anuncio> anuncios = AnuncioDAO.buscarUsuarioPropietario(cb.getEmailUser(),conf,sql);
	   	for(int i=0; i<anuncios.size(); i++){
	   	%>
        <table align=left>
		    <tr align=left>
           <th><img align="right" src="https://image.freepik.com/vector-gratis/perfil-avatar-hombre-icono-redondo_24640-14044.jpg" alt="persona2" width="100" height="100"></th>
	           <th align="left">
	             <ul>
	              <li>TITULO: <%out.println(anuncios.get(i).getTitulo());%></li>
	              <li>CUERPO: <%out.println(anuncios.get(i).getCuerpo());%></li>
	              <li>FECHA DE PUBLICACION: <%out.println(anuncios.get(i).getFecha_de_publicacion());%></li>
	              <li>TIPO: <%out.println(anuncios.get(i).getType()); %></li>
	              <li>ESTADO: <%out.println(anuncios.get(i).getEstado()); %></li>
	              <% if (anuncios.get(i).getType().equals(Type.tematico)){ %>
	              <li>TEMA: <%out.println(anuncios.get(i).getTema()); %></li>
	              	<%} %>
	             </ul>
	           </th>
        	</tr>
		<% } %>
        </table>
      </div>

     </div>
</body>
</html>