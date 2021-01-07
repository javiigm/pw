<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="es.uco.pw.display.javabean.CustomerBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Practica 3 Servlets -- Index</title>
<link rel="stylesheet" href="css/index.css">
<!-- Load icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">

</head>
<body>
<%
session = request.getSession();
CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("")) {
	// Usuario no logado -> Se invoca al controlador de la funcionalidad
%>

<div class="topnav">
  <a href="/P3/index.jsp">Inicio</a>
  <a href="/P3/mvc/view/loginView.jsp">Acceder</a>
  <a href="/P3/mvc/view/registerView.jsp">Registrarse</a>
  <% } else { 
	response.sendRedirect("mvc/view/home.jsp");
   } %>
  <a href="/P3/mvc/view/aboutView.jsp">Sobre Nosotros</a>
  <div class="search-container">
    <form action="/action_page.php">
      <input type="text" placeholder="Search.." name="search">
      <button type="submit"><i class="fa fa-search"></i></button>
    </form>
  </div>
</div>

<!-- Slide Show -->
<section>
  <img class="mySlides" src="mvc/view/Fondo_1.jpg" style="width:100%">
  <img class="mySlides" src="mvc/view/Fondo_2.jpg" style="width:100%">
</section>
<script>
// Automatic Slideshow - change image every 3 seconds
var myIndex = 0;
carousel();

function carousel() {
  var i;
  var x = document.getElementsByClassName("mySlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  myIndex++;
  if (myIndex > x.length) {myIndex = 1}
  x[myIndex-1].style.display = "block";
  setTimeout(carousel, 3000);
}
</script>

</body>
</html>