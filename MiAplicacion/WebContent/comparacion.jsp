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

</head>

<body>
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
							<li class="list-group-item text-left">Director1</li>
							<li class="list-group-item text-left">Director2</li>							
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
							<li class="list-group-item">
								<img class="img-responsive img-metascore" src="images/metascore.png" alt="">
								<label class="btn btn-success">${obra1.metascore}</label>
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
				<div class="panel panel-default panel-obra">
					<div class="panel-heading">
						<h3>${obra2.nombre}</h3>
					</div>
					<!--  Imagen -->
					<div class="panel-body img-responsive text-left">
					    <img class="img-responsive" src="${obra2.ruta_imagen}" alt="">
						<p><strong>Estreno:</strong> 
							<fmt:formatDate pattern="dd MMMM, yyyy" value="${obra2.fecha_emision}"/>
						</p>
						<p><strong>Duración:</strong> ${obra2.duracion} min</p>
						<p><strong>Pais:</strong> ${obra2.nacionalidad}</p>
					</div>
					<!-- /Imagen -->
					<div class="panel-body text-center">
					</div>
					<ul class="list-group text-center">
											
						<!-- Actores -->
						<li class="list-group-item"><div class="list-group-active"><b>Reparto</b></div></li>						
							<c:forEach var="persona" items="${personasObra2}">
								<a href="#collapse01${persona.id}" class="list-group-item text-left accordion-toggle collapsed" data-toggle="collapse" >
								${persona.nombre}</a>
								<div id="collapse01${persona.id}" class="panel-collapse collapse text-left">
      								<ul class="list-group">
    						    		<li class="list-group-item"><b>Fecha de nacimiento:</b> 
    						    			<fmt:formatDate pattern="yyyy" value="${persona.nacimiento}"/>
    						    		</li>
       									<li class="list-group-item"><b>Sexo:</b> ${persona.sexo}</li>
       									<li class="list-group-item"><b>Nacionalidad:</b> ${persona.nacionalidad}</li>       							
      								</ul>
    							</div>
							</c:forEach>
						<!-- /Actores -->
												
						<li class="list-group-item">
							<div class="list-group-active"><b>Director</b></div></li>
						<li class="list-group-item text-left">Director1</li>
						<li class="list-group-item">
							<div class="list-group-active"><b>Puntuaciones</b></div></li>
						<li class="list-group-item text-left">IMDB Rating</li>
						<li class="list-group-item text-left">Metascore</li>
					</ul>
					<div class="panel-footer">
						<a class="btn btn-lg btn-block btn-info" href="#">TEST!</a>
					</div>
				</div>
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