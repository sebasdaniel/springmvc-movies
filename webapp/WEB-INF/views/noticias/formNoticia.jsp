<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Creacion de Noticias</title>
	
	<spring:url value="/resources" var="urlPublic"></spring:url>
	<spring:url value="/noticias/save" var="urlForm"></spring:url>
	<link href="${ urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
</head>

<body>
	<%-- Se incluye el navbar --%>
	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<h3 class="blog-title">
			<span class="label label-success">Datos de la Noticia</span>
		</h3>

		<spring:hasBindErrors name="noticia">
			<div class='alert alert-danger' role='alert'>
				Por favor corrija los siguientes errores:
				<ul>
					<c:forEach items="${ errors.allErrors }" var="error">
						<li><spring:message message="${ error }"></spring:message></li>
					</c:forEach>
				</ul>
			</div>
		</spring:hasBindErrors>
		
		<form:form action="${ urlForm }" method="post" modelAttribute="noticia">
			<form:hidden path="id"/>
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label for="titulo">Titulo</label>
						<form:input type="text" class="form-control" path="titulo" id="titulo" required="required" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="estatus">Estatus</label>
						<form:select id="estatus" path="estatus" class="form-control">
							<form:option value="Activa">Activa</form:option>
							<form:option value="Inactiva">Inactiva</form:option>
						</form:select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<label for="detalle">Detalles</label>
						<form:textarea class="form-control" path="detalle" id="detalle" rows="10"></form:textarea>
					</div>
				</div>
			</div>

			<button type="submit" class="btn btn-danger">Guardar</button>
		</form:form>

		<hr class="featurette-divider">

		<%-- se incluye el footer --%>
		<jsp:include page="../includes/footer.jsp"></jsp:include>
	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${ urlPublic }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ urlPublic }/tinymce/tinymce.min.js"></script>
	<script>
      tinymce.init({
          selector: '#detalle',
          plugins: "textcolor, table lists code",
          toolbar: " undo redo | bold italic | alignleft aligncenter alignright alignjustify \n\
                    | bullist numlist outdent indent | forecolor backcolor table code"
      });
    </script>
</body>
</html>
