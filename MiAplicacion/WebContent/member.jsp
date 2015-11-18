<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- <link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" /> -->
<link href="bootstrap/css/miaplicacion_base.css" rel="stylesheet" type="text/css" />

<title>Title</title>
</head>
<body>

	<div class="container">
		<div class="row">
			
			<!-- Columna derecha -->
			<div class="col-md-9 col-md-push-3">
				
				<p>Esta es mi casa (${param.u})</p>
				
			</div>
			<!-- Columna derecha -->			
			
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