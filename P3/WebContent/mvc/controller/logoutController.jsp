<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="es.uco.pw.business.user.User,es.uco.pw.data.dao.UserDAO"%>
<jsp:useBean id="customerBean" scope="session"
	class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede de la vista 
		b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
	*/
//Caso 1: Por defecto, vuelve al index
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean == null || customerBean.getEmailUser().equals("") || customerBean.getPassword().equals("")) {
	nextPage = "../view/logoutView.jsp";
	mensajeNextPage = "El usuario se ha desconectado correctamente";
}
else{
	request.getSession().removeAttribute("customerBean");
	nextPage = "../view/logoutView.jsp";
	mensajeNextPage = "El usuario se ha desconectado correctamente";
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message" />
</jsp:forward>