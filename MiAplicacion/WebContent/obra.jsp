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

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ajax-autocomplete-servlet.js"></script>
<script src="js/ratings.js"></script>

</head>

<body>
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
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                            4.0 estrellas
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
                            	<span class="glyphicon glyphicon-star"></span>
                            	<span class="glyphicon glyphicon-star"></span>
                            	<span class="glyphicon glyphicon-star"></span>
                            	<span class="glyphicon glyphicon-star"></span>
                            	<span class="glyphicon glyphicon-star-empty"></span>
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
                            	<div class="row">
                        			<div class="col-md-12">
                        				<textarea disabled style="border: none; overflow: hidden; resize:none; background-color: #F5F5F5;" cols="110">${comentario.comentario}
                            			</textarea>
                            			<div class="action-buttons">
                            				<a class="btn btn-warning btn-sm" href=""><span class="glyphicon glyphicon-pencil"></span></a>
     										<a class="btn btn-danger btn-sm" href="DeleteComment.do?comment_id=${comentario.id}&id=${obra.id}"><span class="glyphicon glyphicon-trash"></span></a>
     									</div>
                            		</div>
                            	</div>
                        	</div>
                    	</div>
                    	<hr>
                    	<!-- /Comentario -->
                    </c:forEach>
                    
              		<!-- Dejar comentario -->
              		<form action="AddComment.do" method="get">
                    	<div class="row">
                        	<div class="col-md-12">
                            	<textarea class="form-control message" rows="4" name="comment"></textarea>
                            	<input type="hidden" class="rating check"/>
                        	</div>
                        	<div class="form-group">
								<div class="col-sm-10">
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