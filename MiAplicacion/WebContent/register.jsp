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

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3 well well-sm">
				<form name="register" method="post" action="/MiAplicacion/RegisterUsuario.do">
					<h2>Registro <small>Alguna cosa</small></h2>
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