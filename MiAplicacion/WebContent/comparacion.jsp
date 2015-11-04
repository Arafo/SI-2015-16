<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/comparacion.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-bs.css" rel="stylesheet" type="text/css" />

<title>Insert title here</title>
</head>
<body>

	<!-- Comparacion -->
	<div class="container">
		<div class="row">

			<!-- Obra1 -->
			<div class="col-md-6 text-center">
				<div class="panel panel-info panel-pricing">
					<div class="panel-heading">
						<i class="fa fa-desktop"></i>
						<h3>${obra1}</h3>
					</div>
					<!--  Imagen -->
					<div class="panel-body img-responsive text-left">
						<img src="images/matrix.jpg" alt="Matrix" />
						<p><strong>Estreno:</strong> 1 de Enero, 1999</p>
						<p><strong>Duración:</strong> 132 min</p>
						<p><strong>Pais:</strong> USA</p>
					</div>
					<!-- /Imagen -->
					<div class="panel-body text-center">
					</div>
					<ul class="list-group text-center">
						<li class="list-group-item">
							<div class="list-group-active"><b>Reparto</b></div></li>
						<li class="list-group-item text-left">Actor1</li>
						<li class="list-group-item text-left">Actor2</li>
						
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
			<!-- /Obra1 -->

			<!-- Obra2 -->
			<div class="col-md-6 text-center">
				<div class="panel panel-success panel-pricing">
					<div class="panel-heading">
						<i class="fa fa-desktop"></i>
						<h3>${obra2}</h3>
					</div>
					<div class="panel-body img-responsive">
						<img src="images/AliGIndahouse_2002.jpg" alt="Matrix" />
					</div>
					</div>
					<div class="panel-footer">
						<a class="btn btn-lg btn-block btn-success" href="#"></a>
					</div>
				</div>
			</div>
			<!-- /Obra2 -->

		</div>
	</div>
	<!-- /Comparacion -->
	
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
    </div>
    <!-- /.container -->

</body>
</html>