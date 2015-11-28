<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- <link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" /> -->
<link href="css/miaplicacion_base.css" rel="stylesheet" type="text/css" />
<link href="css/member.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/sortable-theme-bootstrap.css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/grid.js"></script>
<script type="text/javascript" src="js/modify-user-data.js"></script>
<script type="text/javascript" src="js/sortable.min.js"></script>


<title>Mi casa</title>
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
  							<li class="active"><a href="member.html?u=${nombre}">${nombre}</a></li>
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

			<!-- Columna derecha -->
			<div class="col-md-8 col-md-push-4">
				<div class="panel panel-default">
					
					<h3 class="panel-heading">
						<a href="#collapse" class="panel-title collapsed" data-toggle="collapse" >Mis acciones</a>
					</h3>

					<div id="collapse" class="panel-body panel-collapse collapse in">
						<div class="table-responsive">
							<!-- Tabla de acciones del usuario -->
							<table class="table sortable-theme-bootstrap" data-sortable>
								<!-- Encabezado de la tabla -->
								<thead>
									<tr>
										<th>#</th>
										<th>Nombre</th>
										<th>Fecha</th>
										<th>User ID</th>
									</tr>
								</thead>
								<!-- /Encabezado de la tabla -->
								<!-- Cuerpo de la tabla -->
								<tbody>
									<c:forEach var="accion" items="${acciones}">
										<tr>
											<td>${accion.id}</td>
											<td>${accion.nombre}</td>
											<td>${accion.fecha}</td>
											<td>${accion.id_usuario}</td>
										</tr>
									</c:forEach>
								</tbody>
								<!-- /Cuerpo de la tabla -->
							</table>
							<!-- /Tabla de acciones del usuario -->							
						</div>
					</div>
				</div>
			</div>
			<!-- Columna derecha -->

			<!-- Columna izquierda -->
			<div class="col-md-4 col-md-pull-8">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Mis datos</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-lg-12">
								<form name="modify-user-data" method="post" action="/MiAplicacion/ModifyUserData.do">
								<!-- Tabla de informacion de usuario -->
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Nombre de usuario</td>
											<td><input class="form-control" type="text" id="name" name="name" value="${datos.nombre}" disabled></td>
										</tr>
										<tr>
											<td>Email</td>
											<td><a href="mailto:${datos.email}"><input class="form-control" type="email" id="email" name="email" value="${datos.email}" disabled></a>
											<!-- Errores de correo electrónico -->
											<c:forEach var="entry" items="${errores}">
												<c:choose>
													<c:when test="${entry.key eq 'Email'}">
														<div class="alert alert-danger">
															<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
															<b>Error!</b> ${entry.value}
														</div>
													</c:when>
												</c:choose>
											</c:forEach>
											<!-- /Errores de correo electrónico -->		
											</td>									
										</tr>
										<tr>
											<td>Fecha de nacimiento</td>
											<td><input class="form-control" type="text" id="bday" name="bday" value="${datos.nacimiento}" disabled>
											<!-- Errores de fecha -->
											<c:forEach var="entry" items="${errores}">
												<c:choose>
													<c:when test="${entry.key eq 'Fecha'}">
														<div class="alert alert-danger">
															<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
															<b>Error!</b> ${entry.value}
														</div>
													</c:when>
												</c:choose>
											</c:forEach>
											<!-- /Errores de fecha -->		
											</td>
										</tr>
										<tr>
											<td>Género</td>
											<c:choose>
  												<c:when test="${datos.sexo eq 'H'}">
  													<td><input class="form-control" type="text" id="sex" name="sex" value="Hombre" disabled>
  														<!-- Errores de genero -->
														<c:forEach var="entry" items="${errores}">
															<c:choose>
																<c:when test="${entry.key eq 'Sexo'}">
																	<div class="alert alert-danger">
																		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
																		<b>Error!</b> ${entry.value}
																	</div>
																</c:when>
															</c:choose>
														</c:forEach>
														<!-- /Errores de genero -->		
  													</td>									
  												</c:when>
  												<c:when test="${datos.sexo eq 'M'}">
  													<td><input class="form-control" type="text" id="sex" name="sex" value="Mujer" disabled>
  													  	<!-- Errores de genero -->
														<c:forEach var="entry" items="${errores}">
															<c:choose>
																<c:when test="${entry.key eq 'Sexo'}">
																	<div class="alert alert-danger">
																		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
																		<b>Error!</b> ${entry.value}
																	</div>
																</c:when>
															</c:choose>
														</c:forEach>
														<!-- /Errores de genero -->	
  													</td>									
  												</c:when>
  												<c:otherwise>
  													<td><input class="form-control" type="text" id="sex" name="sex" value="Indefinido" disabled>
  													  	<!-- Errores de genero -->
														<c:forEach var="entry" items="${errores}">
															<c:choose>
																<c:when test="${entry.key eq 'Sexo'}">
																	<div class="alert alert-danger">
																		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
																		<b>Error!</b> ${entry.value}
																	</div>
																</c:when>
															</c:choose>
														</c:forEach>
														<!-- /Errores de genero -->	
  													</td>									  													
  												</c:otherwise>
  											</c:choose>
										</tr>
										<tr>
											<td>Dirección</td>
											<td><input class="form-control" type="text" id="address" name="address" value="${datos.address}" disabled></td>
										</tr>
										<tr>
											<td>Teléfono</td>
											<td><input class="form-control" type="text" id="phone" name="phone" value="${datos.telefono}" disabled>
											<!-- Errores de correo electrónico -->
											<c:forEach var="entry" items="${errores}">
												<c:choose>
													<c:when test="${entry.key eq 'Telefono'}">
														<div class="alert alert-danger">
															<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
															<b>Error!</b> ${entry.value}
														</div>
													</c:when>
												</c:choose>
											</c:forEach>
											<!-- /Errores de correo electrónico -->	
											</td>
										</tr>
									</tbody>
								</table>
								<!-- /Tabla de informacion de usuario -->
								<input type="hidden" name="old_email" value="${param.u}">
								<input class="btn btn-primary" id="send_button" type="hidden" value="Enviar">
								<input class="btn btn-success" id="edit_button" type="button" value="Editar">
								</form>
								<form name="delete-user-data" method="post" action="/MiAplicacion/DeleteUserData.do">
										<input type="hidden" name="email" value="${param.u}">
										<input type="hidden" name="id" value="${datos.id}">
										<input class="btn btn-danger" id="delete_button" type="submit" value="Borrar cuenta">
								</form>
							</div>
						</div>
					</div>
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