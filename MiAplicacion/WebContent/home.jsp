<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- <link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" /> -->
<link href="css/miaplicacion_base.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-bs.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ajax-autocomplete-servlet.js"></script>

<title>Home</title>
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
					<li class="active"><a href="home.html">Home</a></li>
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
  						<c:when test="${empty nombre}">
  							<li><a href="register.jsp">Registro</a></li>
  							<!-- Inicio de sesión -->
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Iniciar sesión <span class="caret"></span></a>
								<div class="dropdown-menu">
									<form name="login" method="post" action="/MiAplicacion/LoginUsuario.do">
										<div class="form-group">
											<!-- TODO Cambiar input type a email -->
											<label>Correo electrónico</label> <input type="text" placeholder="Correo electrónico" name="email" class="form-control username-input input-sm" required>
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
		
			<!-- Columna izquierda -->
			<div class="col-md-3">
				<p class="lead">Género</p>
				<div class="list-group">
					<a href="#" class="list-group-item">Categoria 1</a> 
					<a href="#" class="list-group-item">Categoria 2</a> 
					<a href="#" class="list-group-item">Categoria 3</a>
					<a href="#" class="list-group-item">Categoria 4</a>
					<a href="#" class="list-group-item">Categoria 5</a>
				</div>
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

			<div class="col-md-9">
				<!-- Zona de la comparacion -->
				<div class="row well">
            		<div class="col-xs-12 col-sm-8 col-md-8 col-centered center-block">
            			<div class="title text-center">
            				<h2>Comparar obras</h2>
            				<h6>Alguna frase de explicacion por aqui</h6>
            			</div>
            			<form action="comparacion.html" method="get">
            				<div class="row">
								<div class="form-group col-xs-12 col-md-6"><input type="text" id="autocomplete1" class="form-control input-lg" placeholder="Obra1"></div>			
								<div class="form-group col-xs-12 col-md-6"><input type="text" id="autocomplete2" class="form-control input-lg" placeholder="Obra2"></div>
								<input type="hidden" id="id_obra1" name="obra1">
								<input type="hidden" id="id_obra2" name="obra2">
							</div>
							<div class="submit">
								<input type="submit" value="COMPARAR" onclick="#" class="btn btn-primary center-block">
							</div>
						</form>
                	</div>
            	</div>
				<!-- /Zona de la comparacion -->

				<div class="row">
					<c:forEach var="obra" items="${obrasList}">
						<div class="col-xs-6 col-lg-4 col-md-4">
							<!-- Obra -->
							<div class="thumbnail">
								<a href="obra.html?id=${obra.id}"><img src="${obra.ruta_imagen}" alt="${obra.nombre}"></a>
								<!-- Datos de la obra -->
								<div class="caption">
									<!--  <h4 class="pull-right">Lorem</h4> -->
									<h4><a href="obra.html?id=${obra.id}">${obra.nombre}</a></h4>
									<p>${obra.plot}</p>
								</div>
								<!-- /Datos de la obra -->	
								<!-- Puntuacion y numero de comentarios -->															
								<div class="ratings">
									<p class="pull-right">${obra.num_comentarios} comentarios</p>
									<p>
										<!-- Puntuacion de la obra -->
                       					<c:forEach var="rating" begin="1" end="${obra.avg_puntuacion}" >
                       						<span class="glyphicon glyphicon-star"></span>
                       					</c:forEach>
                       					<c:forEach var="rating" begin="${obra.avg_puntuacion + 1}" end="5" >
                            				<span class="glyphicon glyphicon-star-empty"></span>
                       					</c:forEach>
										<!-- /Puntuacion de la obra -->                    					
									</p>
								</div>
								<!-- /Puntuacion y numero de comentarios -->															
							</div>
							<!-- /Obra -->							
						</div>
			        </c:forEach>					
				</div>
				
				<!-- Indices de la paginacion -->
				<div class="text-center">
       				<ul class="pagination">
       				
       					<!-- Boton anterior y primera página -->
       					<c:choose>
  							<c:when test="${currentPage != 1}">
  							  	<li><a href="home.html?page=1"><span class="glyphicon glyphicon-backward"></span></a></li>
  								<li><a href="home.html?page=${currentPage - 1}"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
  							</c:when>
  							<c:otherwise>
  								<li><span class="glyphicon glyphicon-backward"></span></li>  							
  							  	<li><span class="glyphicon glyphicon-chevron-left"></span></li>
							</c:otherwise>
						</c:choose>
						
						<!-- Numeros de pagina -->
          				<c:set var="p" value="${currentPage}" /> <%-- current page --%>
						<c:set var="l" value="5" /> <%-- amount of page links to be displayed --%>
						<c:set var="r" value="2" /> <%-- minimum link range ahead/behind --%>
						<c:set var="t" value="${pages}" /> <%-- total amount of pages --%>

						<c:set var="begin" value="${t > l ? ((p - r) > 1 ? (p - r): 1): 1}"/>
						<c:set var="end" value="${t > l ? ((p + r) > t ? t: (p + r)): t}"/>
						
						<c:forEach begin="${begin}" end="${end}" var="i">
                			<c:choose>
                    			<c:when test="${currentPage eq i}">
                    			    <li class="active"><a href="#">${i}</a></li>
                    			</c:when>
                    			<c:otherwise>
                        			<li><a href="home.html?page=${i}">${i}</a></li>
                    			</c:otherwise>
                			</c:choose>
           				</c:forEach>
						<!-- /Numeros de pagina -->
          				
          				<!-- Boton siguiente y ultima pagina -->
       					<c:choose>
  							<c:when test="${currentPage lt pages}">
  								<li><a href="home.html?page=${currentPage + 1}"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
  								<li><a href="home.html?page=${pages}"><span class="glyphicon glyphicon-forward"></span></a></li>
  							</c:when>
  							<c:otherwise>
  								<li><span class="glyphicon glyphicon-chevron-right"></span></li>
  								 <li><span class="glyphicon glyphicon-forward"></span></li>
							</c:otherwise>
						</c:choose>
          				<!-- /Boton siguiente y ultima pagina -->						
        			</ul>
				</div>
				<!-- /Indices de la paginacion -->
        		
			</div>
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