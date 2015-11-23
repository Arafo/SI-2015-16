<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${obra.nombre}</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/miaplicacion_base.css" rel="stylesheet" type="text/css" />
<link href="css/obra.css" rel="stylesheet">
<link href="css/jquery-ui-bs.css" rel="stylesheet" type="text/css" />
<link href="css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ajax-autocomplete-servlet.js"></script>
<script type="text/javascript" src="js/modify-comment.js"></script>
<script type="text/javascript" src="js/star-rating.min.js"></script>

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
  							<li><a href="register.html">Registro</a></li>
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
  							<li><a href="member.jsp?u=${nombre}">${nombre}</a></li>
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

	<!-- Page Content -->
    <div class="container">

        <div class="row">

            <div class="col-md-3">
                <p class="lead">Mi Aplicacion</p>
                <div class="list-group">
                    <a href="#" class="list-group-item active">Categoria1</a>
                    <a href="#" class="list-group-item">Categoria2 </a>
                    <a href="#" class="list-group-item">Categoria2</a>
                </div>
                
                <hr>
                          
                <!-- Comparacion -->
                <form action="comparacion.html" method="get">                
                <div class="row">
            		<div class="col-centered center-block">
            			<p class="lead">Comparar</p>
            			<div class="row">
							<div class="form-group col-xs-12 col-md-12"><input type="text" id="autocomplete1" class="form-control input-lg" name="obra1" value="${obra.nombre}"></div>
						</div>
						<div class="row">
							<div class="form-group col-xs-12 col-md-12"><input type="text" id="autocomplete2" class="form-control input-lg" name="obra2" placeholder="Obra2"></div>
						</div>
						<div class="submit">
							<input type="submit" value="COMPARAR" onclick="#" class="btn btn-primary center-block">
						</div>
                	</div>
            	</div>
               	</form>          	
            	<!-- /Comparacion -->
            	
            	<hr>
            	
            	<!-- Obras mejor puntuadas -->
				<p class="lead">Mejor puntuación</p>
				<div class="list-group">
					<c:forEach var="mejor" items="${mejor_puntuadas}">
						<c:choose>
  							<c:when test="${obra.id eq mejor.id}">
  								<a href="obra.html?id=${mejor.id}" class="list-group-item"><b>${mejor.nombre} (${mejor.avg_puntuacion})</b></a> 	
  							</c:when>
  							<c:otherwise>
  								<a href="obra.html?id=${mejor.id}" class="list-group-item">${mejor.nombre} (${mejor.avg_puntuacion})</a> 
  							</c:otherwise>
  						</c:choose>
					</c:forEach>
				</div>
				<!-- /Obras mejor puntuadas -->		
				<!-- Obras mas comentadas -->		
				<p class="lead">Más comentadas</p>
				<div class="list-group">
					<c:forEach var="mas" items="${mas_comentadas}">
						<c:choose>
  							<c:when test="${mas.id eq obra.id}">
								<a href="obra.html?id=${mas.id}" class="list-group-item"><b>${mas.nombre} (${mas.num_comentarios})</b></a> 
  							</c:when>
  							<c:otherwise>
								<a href="obra.html?id=${mas.id}" class="list-group-item">${mas.nombre} (${mas.num_comentarios})</a> 	
  							</c:otherwise>
  						</c:choose>
					</c:forEach>
				</div>
				<!-- /Obras mas comentadas -->	
                
            </div>

            <div class="col-md-9">

                <div class="thumbnail">                
                    <img class="img-responsive" src="${obra.ruta_imagen}" alt="">
                    <div class="caption-full">
                        <div class="title">
                        	<h3><a href="#">${obra.nombre}</a></h3><hr>
                        </div>

                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut nisl lacus, dictum ut odio et, auctor mattis neque. Vivamus malesuada hendrerit libero sollicitudin fermentum</p>
                       	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut nisl lacus, dictum ut odio et, auctor mattis neque. Vivamus malesuada hendrerit libero sollicitudin fermentum</p> 
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
                    </div>
                    <div class="ratings">
                        <p class="pull-right">${comentariosSize} comentarios</p>
                        <p>
                        	<!-- Puntuacion de la obra -->
                       		<c:forEach var="rating" begin="1" end="${obra.puntuacion}" >
                       			<span class="glyphicon glyphicon-star"></span>
                       		</c:forEach>
                       		<c:forEach var="rating" begin="${obra.puntuacion + 1}" end="5" >
                            	<span class="glyphicon glyphicon-star-empty"></span>
                       		</c:forEach>
                            ${obra.puntuacion}.0 estrellas
                            <!-- /Puntuacion de la obra -->      
                        </p>
                    </div>
                </div>

				<!-- Zona de comentarios -->
                <div class="well">
                    <div class="text-right">
                        <a class="btn btn-success">Dejar un comentario</a>
                    </div>
                    <hr>
                    
                   	<c:forEach var="comentario" items="${comentarios}">
                    	<!-- Comentario -->
                    	<div class="row">
                       		<div class="col-md-12">
                       			<!-- Puntuacion del comentario -->
                       			<c:forEach var="rating" begin="1" end="${comentario.puntuacion}" >
                       				<span class="glyphicon glyphicon-star"></span>
                       			</c:forEach>
                       			<c:forEach var="rating" begin="${comentario.puntuacion + 1}" end="5" >
                            		<span class="glyphicon glyphicon-star-empty"></span>
                       			</c:forEach>
                       			<!-- /Puntuacion del comentario -->
                       			                       		
                            	${comentario.nombre}
                            	
                            	<!-- Fecha del comentario -->
    							<c:set value="${(date_today.time-comentario.fecha.time)/(1000*60*60*24)}" var="dateDiff"/>
                            	<c:choose>
  									<c:when test="${dateDiff lt 1}">
  										<span class="pull-right">Hoy mismo</span>
			  						</c:when>
  									<c:otherwise>
  										<fmt:parseNumber var="finalDate" integerOnly="true" type="number" value="${(dateDiff-(dateDiff%1))/10}" />
  										<span class="pull-right">Hace ${finalDate} días</span>	
  									</c:otherwise>
								</c:choose>
								<!-- /Fecha del comentario -->
								<form action="ModifyComment.do" method="get">							
                            		<div class="row" id="comentariofila_${comentario.id}">
                        				<div class="col-md-12">
                        					<textarea class="form-control comment-textarea" disabled id="comentario_${comentario.id}" name="new_comment">${comentario.comentario}
                            				</textarea>
                            				<div class="action-buttons">
                            					<c:choose>
                            						<c:when test="${comentario.email eq cookie['loginUsuario'].value}">
                            							<a class="btn btn-warning btn-sm" id="edit_${comentario.id}"><span class="glyphicon glyphicon-pencil"></span></a>
     													<a class="btn btn-danger btn-sm" href="DeleteComment.do?comment_id=${comentario.id}&id=${obra.id}"><span class="glyphicon glyphicon-trash"></span></a>
                            						</c:when>
                            					</c:choose>
                            				</div>
                            			</div>
                            			<input type="hidden" name="comment_id" value="${comentario.id}" /> 
                            			<input type="hidden" name="obra_id" value="${obra.id}" /> 
                            		
                            			<input type="hidden" class="btn btn-success" id="sendb_${comentario.id}" value="Enviar "style="margin: 4px 0 0 16px"/>
                            			<input type="hidden" class="btn btn-primary" id="cancelb_${comentario.id}" value="Cancelar" style="margin: 4px 0 0 6px"/>
                            		</div>
                            	</form>
                        	</div>
                    	</div>
                    	<hr>
                    	<!-- /Comentario -->
                    </c:forEach>
                    
              		<!-- Dejar comentario -->
              		<form action="AddComment.do" method="get">
                    	<div class="row">
                        	<div class="col-md-12">
                            	<textarea class="form-control message" rows="4" name="comment" placeholder="Escribe un comentario..."></textarea>
                        	</div>                        	
                        	<div class="form-group">
								<div class="col-sm-10">
                            		<input id="input-1" name="rating" class="rating" data-size="sm" data-min="0" data-max="5" data-step="1">
								  	<input type="hidden" name="id" value="${obra.id}" /> 
									<input id="submit" name="submit" type="submit" value="Enviar" onclick="#" class="btn btn-primary">
								</div>
							</div>
                    	</div>
					</form>
              		<!-- /Dejar comentario -->                    
                </div>
				<!-- /Zona de comentarios -->

            </div>
        </div>
    </div>
    <!-- /.container -->

    <div class="container">
        <hr>
        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; 2015</p>
                </div>
            </div>
        </footer>
        <!-- /Footer -->
    </div>
    <!-- /.container -->
	
	

</body>
</html>