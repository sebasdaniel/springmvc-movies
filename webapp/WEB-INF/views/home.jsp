<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido a Cineapp</title>
</head>
<body>
	<h1>Lista de Peliculas</h1>
	
	<ol>
		<c:forEach items="${ peliculas }" var="pelicula">
			<li>${ pelicula }</li>
		</c:forEach>
	</ol>
	
	<h1>Tabla de peliculas</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Titulo</th>
				<th>Duraci&oacute;n</th>
				<th>Clasificaci&oacute;n</th>
				<th>Genero</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ peliculas }" var="pelicula">
				<tr>
					<td>${ pelicula.id }</td>
					<td>${ pelicula.titulo }</td>
					<td>${ pelicula.duracion } min.</td>
					<td>${ pelicula.clasificacion }</td>
					<td>${ pelicula.genero }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>