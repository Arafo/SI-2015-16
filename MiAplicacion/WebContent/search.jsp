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
				<p class="lead">MiAplicacion</p>
				<div class="list-group">
					<a href="#" class="list-group-item">Categoria 1</a> 
					<a href="#" class="list-group-item">Categoria 2</a> 
					<a href="#" class="list-group-item">Categoria 3</a>
					<a href="#" class="list-group-item">Categoria 4</a>
					<a href="#" class="list-group-item">Categoria 5</a>
				</div>
				<p class="lead">MasMiAplicacion</p>
				<div class="list-group">
					<a href="#" class="list-group-item">Mas categoria 1</a> 
					<a href="#" class="list-group-item">Mas Categoria 2</a> 
					<a href="#" class="list-group-item">Mas Categoria 3</a>
				</div>
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