<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import = "java.util.Map" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/miaplicacion_base.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>

<body>

<%
	String valorEncriptado = "No";  
	String loginError = "";
	String claveError = ""; 
	String loginValor = "";
	String claveValor = ""; 

	Map<String, String> e = (Map <String, String>) request.getAttribute("errores"); 
	
	if (e != null) { 
    	String cabecera = "<div class=\"alert alert-danger\">" +
    		"<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>" +
    		"<b>Error! </b>"; 
    	String fin = "</div>"; 
    	if (e.containsKey("Login")) loginError = cabecera + e.get("Login") + fin;  
    	if (e.containsKey("Clave")) claveError = cabecera + e.get("Clave") + fin; 
    	loginValor = request.getParameter("email"); 
    	claveValor = request.getParameter("password"); 
    }
	else { // Manejo de cookies
		Cookie[] cookies = request.getCookies(); 
		for (int i = 0; i < cookies.length; i++) {      
			String  nombreCookieI = cookies[i].getName(); 
			if (nombreCookieI.equals("loginUsuario"))  
				loginValor = cookies[i].getValue();
			if (nombreCookieI.equals("claveUsuario")){  
				claveValor = cookies[i].getValue();
				valorEncriptado = "Si";             	     
			}
		}       
	}  
%>     



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
  							<li><a href="">${nombre}</a></li>
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
    		<div class="col-xs-10 col-sm-6 col-md-4 col-sm-offset-4 col-md-offset-4">
				<form name="login" method="post" action="/MiAplicacion/LoginUsuario.do">
					<h2>Iniciar sesión</h2>
					<hr class="graph">
					<div class="form-group">
						<input type="text" class="form-control input-lg" placeholder="Correo electrónico" value="<%=loginValor%>" name="email" required autofocus>
						<%=loginError%>
					</div>
					<div>
			    		<input type="password" class="form-control input-lg" placeholder="Contraseña" value="<%=claveValor%>" name="password" required>				
						<%=claveError%>
					</div>
					<div class="checkbox">
        				<label>
            				<input type="checkbox" name="remember"> Recuérdame
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