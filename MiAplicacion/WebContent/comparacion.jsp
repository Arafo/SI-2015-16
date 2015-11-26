<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>

<title>${obra1.nombre} VS ${obra2.nombre}</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/comparacion.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-bs.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ajax-autocomplete-servlet.js"></script>
<script type="text/javascript" src="js/metacritic.js"></script>

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

	<!-- Zona de la comparacion -->
	<div class="row">
    	<div class="col-centered col-xs-12 col-sm-8 col-md-6 center-block">
            <div class="title text-center">
            	<h2>Comparador de películas</h2>
            	<h6>Compara películas, cara a cara</h6>
            </div>
            <form action="comparacion.html" method="get">
            	<div class="row">
					<div class="form-group col-xs-12 col-md-6"><input type="text" id="autocomplete1" class="form-control input-lg" value="${obra1.nombre}"></div>
					<div class="form-group col-xs-12 col-md-6"><input type="text" id="autocomplete2" class="form-control input-lg" value="${obra2.nombre}"></div>
					<input type="hidden" id="id_obra1" name="obra1" value="${param.obra1}" maxlength="100">
					<input type="hidden" id="id_obra2" name="obra2" value="${param.obra2}" maxlength="100">
				</div>
				<div class="submit">
					<input type="submit" value="COMPARAR" onclick="#" class="btn btn-primary center-block">
				</div>
			</form>
		</div>
	</div>
	<!-- /Zona de la comparacion -->

	<!-- Comparacion -->
	<div class="container">
		<div class="row">
			<!-- Obra1 -->
			<div class="col-md-6 text-center">
				<!-- Primer panel -->
				<div class="panel panel-default panel-obra">
					<!-- Titulo -->
					<a href="obra.html?id=${obra1.id}">
						<div class="panel-heading">
							<h3>${obra1.nombre}</h3>
						</div>
					</a>
					<!-- /Titulo -->
					<!--  Imagen y datos -->
					<div class="panel-body img-responsive text-left">
					    <img class="img-responsive" src="${obra1.ruta_imagen}" alt="">
						<p>
							<strong>Estreno:</strong> 
							<fmt:formatDate pattern="dd MMMM, yyyy" value="${obra1.fecha_emision}"/>
						</p>
						<p><strong>Duración:</strong> ${obra1.duracion} min</p>
						<p><strong>Pais:</strong> ${obra1.nacionalidad}</p>
						<p><strong>Sinopsis:</strong> ${obra1.plot}</p>				
						<p><strong>Premios:</strong> ${obra1.awards}</p>														
					</div>
					<!-- /Imagen y datos-->
					<div class="panel-body text-center">
					</div>
				</div>
				<!-- /Primer panel -->
				<!-- Panel de actores -->
				<div class="panel-obra">
					<ul class="list-group text-center">						
						<!-- Actores -->
						<a href="#collapseActors" class="list-group-item panel-title collapsed " data-toggle="collapse" ><b>Reparto</b></a>
						<div id="collapseActors" class="panel-collapse collapse in">
							<c:forEach var="persona" items="${personasObra1}">
								<c:choose>
									<c:when test="${persona.rol eq 'Actor'}">
										<a href="#collapse01${persona.id}" class="list-group-item text-left accordion-toggle collapsed" data-toggle="collapse" >${persona.nombre}</a>
										<div id="collapse01${persona.id}" class="panel-collapse collapse text-left">
      										<ul class="list-group">
    					  						<li class="list-group-item"><b>Fecha de nacimiento:</b> 
    					   							<fmt:formatDate pattern="yyyy" value="${persona.nacimiento}"/>
    					   						</li>
       											<li class="list-group-item"><b>Sexo:</b> ${persona.sexo}</li>
       											<li class="list-group-item"><b>Nacionalidad:</b> ${persona.nacionalidad}</li>       							
      										</ul>
    									</div>
									</c:when>
								</c:choose>
			

							</c:forEach>
						</div>
						<!-- /Actores -->
					</ul>
				</div>
				<!-- /Panel de actores -->
				<!-- Panel de directores -->
				<div class="panel-obra">
					<ul class="list-group text-center">									
						<!-- Directores -->	
						<a href="#collapseDirector" class="list-group-item panel-title collapsed " data-toggle="collapse" ><b>Director</b></a>
						<div id="collapseDirector" class="panel-collapse collapse in">	
							<c:forEach var="persona" items="${personasObra1}">		
								<c:choose>
									<c:when test="${persona.rol eq 'Director'}">
										<li class="list-group-item text-left">${persona.nombre}</li>
									</c:when>
								</c:choose>	
							</c:forEach>			
						</div>
						<!-- /Directores -->
					</ul>
				</div>
				<!-- /Panel de directores -->
				<!-- Panel de puntuaciones -->		
				<div class="panel-obra">
					<ul class="list-group text-center">									
						<!-- Puntuaciones -->
						<a href="#collapseScore" class="list-group-item panel-title collapsed " data-toggle="collapse" ><b>Puntuaciones</b></a>
						<div id="collapseScore" class="panel-collapse collapse in">
							<li class="list-group-item">
								<img class="img-responsive img-imdb" src="images/imdb.png" alt="">						
								<span class="imdb_score">${obra1.imdb_rating}<span class="imdb_slash">/</span><span class="imdb_base">10</span></span>
								<img class="img-responsive img-star" src="images/imdb_star.png" alt="">													
							</li>
							<c:choose>
								<c:when test="${obra1.metascore ne -1}">
									<li class="list-group-item">
										<img class="img-responsive img-metascore" src="images/metascore.png" alt="">
										<label class="btn btn-success" id="metascore1">${obra1.metascore}</label>
									</li>
								</c:when>
							</c:choose>
							<li class="list-group-item">
								<span>Puntuación de los usuarios: </span>
                       			<c:forEach var="rating" begin="1" end="${avg_obra1}" >
                       				<span class="glyphicon glyphicon-star"></span>
                       			</c:forEach>
                       			<c:forEach var="rating" begin="${avg_obra1 + 1}" end="5" >
                            		<span class="glyphicon glyphicon-star-empty"></span>
                       			</c:forEach>
							</li>							
						</div>
						<!-- /Puntuaciones -->
					</ul>
				</div>
				<!-- /Panel de puntuaciones -->
			</div>
			<!-- /Obra1 -->

			<!-- Obra2 -->
			<div class="col-md-6 text-center">
				<!-- Primer panel -->
				<div class="panel panel-default panel-obra">
					<!-- Titulo -->
					<a href="obra.html?id=${obra2.id}">
						<div class="panel-heading">
							<h3>${obra2.nombre}</h3>
						</div>
					</a>
					<!-- /Titulo -->
					<!--  Imagen y datos -->
					<div class="panel-body img-responsive text-left">
					    <img class="img-responsive" src="${obra2.ruta_imagen}" alt="">
						<p>
							<strong>Estreno:</strong> 
							<fmt:formatDate pattern="dd MMMM, yyyy" value="${obra2.fecha_emision}"/>
						</p>
						<p><strong>Duración:</strong> ${obra2.duracion} min</p>
						<p><strong>Pais:</strong> ${obra2.nacionalidad}</p>
						<p><strong>Sinopsis:</strong> ${obra2.plot}</p>				
						<p><strong>Premios:</strong> ${obra2.awards}</p>														
					</div>
					<!-- /Imagen y datos-->
					<div class="panel-body text-center">
					</div>
				</div>
				<!-- /Primer panel -->
				<!-- Panel de actores -->
				<div class="panel-obra">
					<ul class="list-group text-center">						
						<!-- Actores -->
						<a href="#collapseActors2" class="list-group-item panel-title collapsed " data-toggle="collapse" ><b>Reparto</b></a>
						<div id="collapseActors2" class="panel-collapse collapse in">
							<c:forEach var="persona" items="${personasObra2}">
								<c:choose>
									<c:when test="${persona.rol eq 'Actor'}">
										<a href="#collapse02${persona.id}" class="list-group-item text-left accordion-toggle collapsed" data-toggle="collapse" >${persona.nombre}</a>
										<div id="collapse02${persona.id}" class="panel-collapse collapse text-left">
      										<ul class="list-group">
    					  						<li class="list-group-item"><b>Fecha de nacimiento:</b> 
    					   							<fmt:formatDate pattern="yyyy" value="${persona.nacimiento}"/>
    					   						</li>
       											<li class="list-group-item"><b>Sexo:</b> ${persona.sexo}</li>
       											<li class="list-group-item"><b>Nacionalidad:</b> ${persona.nacionalidad}</li>       							
      										</ul>
    									</div>
    								</c:when>
    							</c:choose>
							</c:forEach>
						</div>
						<!-- /Actores -->
					</ul>
				</div>
				<!-- /Panel de actores -->
				<!-- Panel de directores -->
				<div class="panel-obra">
					<ul class="list-group text-center">									
						<!-- Directores -->	
						<a href="#collapseDirector2" class="list-group-item panel-title collapsed " data-toggle="collapse" ><b>Director</b></a>
						<div id="collapseDirector2" class="panel-collapse collapse in">					
							<c:forEach var="persona" items="${personasObra2}">		
								<c:choose>
									<c:when test="${persona.rol eq 'Director'}">
										<li class="list-group-item text-left">${persona.nombre}</li>
									</c:when>
								</c:choose>	
							</c:forEach>								
						</div>
						<!-- /Directores -->
					</ul>
				</div>
				<!-- /Panel de directores -->
				<!-- Panel de puntuaciones -->		
				<div class="panel-obra">
					<ul class="list-group text-center">									
						<!-- Puntuaciones -->
						<a href="#collapseScore2" class="list-group-item panel-title collapsed " data-toggle="collapse" ><b>Puntuaciones</b></a>
						<div id="collapseScore2" class="panel-collapse collapse in">
							<li class="list-group-item">
								<img class="img-responsive img-imdb" src="images/imdb.png" alt="">						
								<span class="imdb_score">${obra2.imdb_rating}<span class="imdb_slash">/</span><span class="imdb_base">10</span></span>
								<img class="img-responsive img-star" src="images/imdb_star.png" alt="">													
							</li>
							<c:choose>
								<c:when test="${obra2.metascore ne -1}">
									<li class="list-group-item">
										<img class="img-responsive img-metascore" src="images/metascore.png" alt="">
										<label class="btn btn-success" id="metascore2">${obra2.metascore}</label>
									</li>
								</c:when>
							</c:choose>
							<li class="list-group-item">
								<span>Puntuación de los usuarios: </span>
                       			<c:forEach var="rating" begin="1" end="${avg_obra2}" >
                       				<span class="glyphicon glyphicon-star"></span>
                       			</c:forEach>
                       			<c:forEach var="rating" begin="${avg_obra2 + 1}" end="5" >
                            		<span class="glyphicon glyphicon-star-empty"></span>
                       			</c:forEach>
							</li>							
						</div>
						<!-- /Puntuaciones -->
					</ul>
				</div>
				<!-- /Panel de puntuaciones -->
			</div>
			<!-- /Obra2 -->
		</div>
	</div>
	<!-- /Comparacion -->
	
	<div class="container">
		<hr>
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