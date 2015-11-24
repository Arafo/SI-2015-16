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

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<title>Title</title>
</head>
<body>

	<div class="container">
		<div class="row">

			<!-- Columna derecha -->
			<div class="col-md-8 col-md-push-4">
				<div class="panel panel-default">
					
					<h3 class="panel-heading">
						<a href="#collapse" class="panel-title collapsed" data-toggle="collapse" ><b>Esta es mi casa (${param.u})</b></a>
					</h3>

					<div id="collapse" class="panel-body panel-collapse collapse in">
						<div class="table-responsive">
							<!-- Tabla de acciones del usuario -->
							<table class="table">
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
						<h3 class="panel-title">Nombre usuario</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-11 col-lg-11">
								<!-- Tabla de informacion de usuario -->
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Department:</td>
											<td>Programming</td>
										</tr>
										<tr>
											<td>Date of Birth</td>
											<td>01/24/1988</td>
										</tr>
										<tr>
											<td>Gender</td>
											<td>Male</td>
										</tr>
										<tr>
											<td>Home Address</td>
											<td>Metro Manila,Philippines</td>
										</tr>
										<tr>
											<td>Email</td>
											<td><a href="mailto:info@support.com">info@support.com</a></td>
										</tr>
										<tr>
											<td>Phone Number</td>
											<td>123-4567-890(Landline)<br> <br>555-4567-890(Mobile)
											</td>
										</tr>
									</tbody>
								</table>
								<!-- /Tabla de informacion de usuario -->
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