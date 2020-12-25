<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
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
		   		
	    LinkedList<Anuncio> anuncios = AnuncioDAO.buscarUsuarioPropietario(cb.getEmailUser(),conf,sql);
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