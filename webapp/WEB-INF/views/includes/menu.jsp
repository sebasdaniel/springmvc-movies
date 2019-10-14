<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<spring:url value="/" var="urlRoot" />
<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
				aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<sec:authorize access="isAnonymous()">
				<a class="navbar-brand" href="${ urlRoot }">My CineSite</a>
			</sec:authorize>
			<sec:authorize access="hasAnyAuthority('EDITOR', 'GERENTE')">
				<a class="navbar-brand" href="${ urlRoot }admin/index">My CineSite | Administraci&oacute;n</a>
			</sec:authorize>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<%-- usuario anonimo --%>
				<sec:authorize access="isAnonymous()">
					<li><a href="${ urlRoot }about">Acerca</a></li>
					<li><a href="${ urlRoot }admin/formLogin">Ingresar</a></li>
				</sec:authorize>
				
				<%-- usuario editor --%>
				<sec:authorize access="hasAnyAuthority('EDITOR')">
					<li><a href="${ urlRoot }peliculas/indexPaginate?page=0">Peliculas</a></li>
					<li><a href="${ urlRoot }horarios/index?page=0">Horarios</a></li>
					<li><a href="${ urlRoot }noticias/index?page=0">Noticias</a></li>
					<li><a href="${ urlRoot }admin/logout">Salir</a></li>
				</sec:authorize>
				
				<%-- usuario gerente --%>
				<sec:authorize access="hasAnyAuthority('GERENTE')">
					<li><a href="${ urlRoot }peliculas/indexPaginate?page=0">Peliculas</a></li>
					<li><a href="${ urlRoot }horarios/index?page=0">Horarios</a></li>
					<li><a href="${ urlRoot }noticias/index?page=0">Noticias</a></li>
					<li><a href="${ urlRoot }banners/index">Banners</a></li>
					<li><a href="${ urlRoot }admin/logout">Salir</a></li>
				</sec:authorize>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>