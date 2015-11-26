<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/miaplicacion_base.css" rel="stylesheet" type="text/css" />
<link href="css/register.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

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
  							<li class="active"><a href="register.jsp">Registro</a></li>
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
			<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3 well well-sm">
				<form name="register" method="post" action="/MiAplicacion/RegisterUsuario.do">
					<h2>Registro <small>¡Es gratis!</small></h2>
					<hr class="graph">

					<!-- Nombre -->
					<div class="form-group">
						<input type="text" name="name" class="form-control input-lg" placeholder="Nombre">
					</div>
					<!-- /Nombre -->
					<!-- Correo electrónico -->
					<div class="form-group">
						<input type="email" name="email" class="form-control input-lg" placeholder="Correo electrónico" tabindex="4">
					</div>
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

					<!-- /Correo electrónico -->
					<!-- Contraseñas -->
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<input type="password" name="password" class="form-control input-lg" placeholder="Contraseña"tabindex="5">
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<input type="password" name="password_confirmation" class="form-control input-lg"placeholder="Confirmar contraseña" tabindex="6">
							</div>
						</div>
					</div>
					<!-- Errores de contraseña -->
					<c:forEach var="entry" items="${errores}">
						<c:choose>
							<c:when test="${entry.key eq 'Clave' || entry.key eq 'ReClave'}">
								<div class="alert alert-danger">
									<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
									<b>Error!</b> ${entry.value}
								</div>
							</c:when>
						</c:choose>
					</c:forEach>
					<!-- /Errores de contraseña -->
					<!-- /Contraseñas -->
					<!-- Fecha de nacimiento -->
					<div class="form-group">
						<input type="date" name="bday" step="1" min="1900-01-01" max="2020-01-01" class="form-control input-lg" placeholder="Fecha de nacimiento (dd/mm/aaaa)">
					</div>
					<!-- /Fecha de nacimiento -->
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
					<!-- Dirección -->
					<div class="form-group">
						<input type="text" name="address" class="form-control input-lg" placeholder="Dirección" />
					</div>
					<!-- /Direcciónn -->
					<!-- Teléfono -->
					<div class="form-group">
						<input type="tel" name="tel" class="form-control input-lg" placeholder="Teléfono" />
					</div>
					<!-- /Teléfono -->
					<!-- Sexo -->
					<div class="form-group">
						<fieldset class="genre">
							<legend class="sex">Sexo</legend>
							<input id="IdHombre" type="radio" name="sex" value="H" /><label for="IdHombre">Hombre</label> 
							<input id="IdMujer" type="radio"name="sex" value="M" /><label for="IdMujer">Mujer</label>
							<input id="IdUndef" type="radio" name="sex" value="U" /><label for="IdUndef">Indefinido</label>
						</fieldset>
					</div>
					<!-- /Sexo -->

					<hr class="graph">
					<!-- Botones -->
					<div class="row">
						<div class="col-xs-12 col-md-6">
							<input type="submit" value="Registrarse"class="btn btn-primary btn-block btn-lg">
						</div>
						<div class="col-xs-12 col-md-6">
							<a href="login.jsp" class="btn btn-success btn-block btn-lg">Iniciar sesión</a>
						</div>
					</div>
					<!-- /Botones -->
				</form>
			</div>
		</div>
	</div>

</body>
</html>