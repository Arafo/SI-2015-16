<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet">

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
	<div class="row">
    	<div class="col-xs-10 col-sm-6 col-md-4 col-sm-offset-4 col-md-offset-4">
			<form role="form">
				<h2>Iniciar sesión</h2>
				<hr class="graph">
				<div class="form-group">
					<input type="email" id="inputEmail" class="form-control input-lg" placeholder="Correo electrónico" required autofocus>
				</div>
				<div>
			    	<input type="password" id="inputPassword" class="form-control input-lg" placeholder="Contraseña" required>
				</div>
				
				<div class="checkbox">
        			<label>
            			<input type="checkbox" value="remember-me"> Recuérdame
        			</label>
        		</div>
   				<hr class="graph">     		
				<button class="btn btn-primary btn-block btn-lg" type="submit">Iniciar sesión</button>
				<a href="register.html" class="btn btn-success btn-block btn-lg">Registrarse</a>				
			</form>
		</div>
	</div>		
</div>

</body>
</html>