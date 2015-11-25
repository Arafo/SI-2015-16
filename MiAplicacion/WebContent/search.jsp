<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- <link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" /> -->
<link href="css/miaplicacion_base.css" rel="stylesheet" type="text/css" />
<link href="css/search.css" rel="stylesheet" type="text/css" />


<title>Busqueda</title>
</head>
<body>

	<!-- Static navbar -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">Mi Aplicacion</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<!-- Enlaces -->
				<ul class="nav navbar-nav">
					<li><a href="home.html">Home</a></li>
					<li><a href="JesusBailongo.html">Sorprendeme</a></li>
				</ul>
				<!-- /Enlaces -->
				<!-- Busqueda -->
				<div class="col-sm-3 col-md-3">
        			<form class="navbar-form" action="search.html" method="get">
        				<div class="input-group">
            				<input type="text" class="form-control" placeholder="Buscar" name="q">
            				<div class="input-group-btn">
                				<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            				</div>
        				</div>
        			</form>
    			</div>
    			<!-- /Busqueda -->
    			
    			<!-- Registro e inicio de sesión -->
				<ul class="nav navbar-nav navbar-right">
					<c:choose> 
  						<c:when test="${empty cookie['loginUsuario'].value}">
  							<li><a href="register.jsp">Registro</a></li>
  							<!-- Inicio de sesión -->
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Iniciar sesión <span class="caret"></span></a>
								<div class="dropdown-menu">
									<form name="login" method="post" action="/MiAplicacion/LoginUsuario.do">
										<div class="form-group">
											<label>Correo electrónico</label> <input type="email" placeholder="Correo electrónico" name="email" class="form-control username-input input-sm" required>
										</div>
										<div class="form-group">
											<label>Contraseña</label> <input type="password" placeholder="Contraseña" name="password" class="form-control password-input input-sm" required>
										</div>
										<div class="form-group">
											<label><input type="checkbox" id="remember" name="remember"> Recuérdame</label>
										</div>
										<div class="form-group">
											<input type="submit" value="Iniciar sesión" name="submit" class="btn btn-primary">
										</div>
									</form>
								</div>
							</li>
							<!-- /Inicio de sesión -->
						</c:when>
  						<c:otherwise>
  							<!-- TODO Cambiar nombre por id (o email) -->
  							<li><a href="member.html?u=${nombre}">${nombre}</a></li>
  							<li><a href="/MiAplicacion/LogoutUsuario.do">[Logout]</a></li>
 		 				</c:otherwise>
					</c:choose>
					
				</ul>
    			<!-- /Registro e inicio de sesión -->

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<div class="container">
		<div class="row">
			
			<!-- Resultados -->
			<div class="col-md-9 col-md-push-3">
				<!-- Grupo encabezado -->
				<hgroup>				
					<h1>Search Results</h1>
					<!-- Formato tiempo de ejecución -->
					<fmt:formatNumber var="time" value="${time}" maxFractionDigits="2" />					<!-- Formato tiempo de ejecución -->
					<!-- /Formato tiempo de ejecución -->
					<h2 class="lead"><strong class="text-danger">${obrasListSize}</strong> resultados encontrados para la búsqueda 
					<strong class="text-danger">${query}</strong><span class="time">(${time} segundos)</span></h2>
				</hgroup>							
				<!-- /Grupo encabezado -->
				
				<c:forEach var="obra" items="${obrasList}">				
					<!-- Resultado -->			
					<div class="search-result row">
						<!-- Parte izquierda -->				
						<div class="col-xs-12 col-sm-12 col-md-5">
							<!-- Imagen -->
							<a href="obra.html?id=${obra.id}" title="${obra.nombre}" class="thumbnail"><img src="${obra.ruta_imagen}" alt="${obra.nombre}"/></a>
							<!-- /Imagen -->
							<!-- Datos -->							
							<div class="obra-list">
								<ul class="meta-search">
									<li><span><strong>Estreno:</strong> 
									<fmt:formatDate pattern="dd MMMM, yyyy" value="${obra.fecha_emision}"/>
									</span></li>
									<li><span><strong>Duración:</strong> ${obra.duracion} min</span></li>
									<li><span><strong>Pais:</strong> ${obra.nacionalidad}</span></li>
								</ul>
							</div>
							<!-- /Datos -->
						</div>
						<!-- /Parte izquierda -->
						<!-- Parte derecha -->
						<div class="col-xs-12 col-sm-12 col-md-7">
							<h3><a href="obra.html?id=${obra.id}" title="">${obra.nombre}</a></h3>
							<p>${obra.plot}</p>						
						</div>
						<!-- /Parte derecha -->
						<span class="clearfix borda"></span>
					</div>
					<!-- /Resultado -->
				</c:forEach>
			</div>
			<!-- /Resultados -->			
			
			<!-- Columna izquierda -->
			<div class="col-md-3 col-md-pull-9">
				<!-- Obras mejor puntuadas -->
				<p class="lead">Mejor puntuación</p>
				<div class="list-group">
					<c:forEach var="obra" items="${mejor_puntuadas}">
						<a href="obra.html?id=${obra.id}" class="list-group-item">${obra.nombre} (${obra.avg_puntuacion})</a> 
					</c:forEach>
				</div>
				<!-- /Obras mejor puntuadas -->		
				<!-- Obras mas comentadas -->		
				<p class="lead">Más comentadas</p>
				<div class="list-group">
					<c:forEach var="obra" items="${mas_comentadas}">
						<a href="obra.html?id=${obra.id}" class="list-group-item">${obra.nombre} (${obra.num_comentarios})</a> 
					</c:forEach>
				</div>
				<!-- /Obras mas comentadas -->	
			</div>
			<!-- /Columna izquierda -->
		</div>
	</div>
	
	<div class="container">
		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; 2015</p>
				</div>
			</div>
		</footer>
	</div>
			

</body>
</html>