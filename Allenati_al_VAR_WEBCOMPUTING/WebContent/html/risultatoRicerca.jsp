<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="../css/home.css" type="text/css">
	<link rel="stylesheet" href="../css/risultatoRicerca.css" type="text/css">
	<script type="text/javascript" src="../js/risultatoRicerca.js"></script>
	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Pagina di ricerca</title>
</head>
<body>

<%@include file="header_default.jsp" %>

	<div id="jumbo1" class="jumbotron">
		<div class="row">
			<div class="column col-sm-9">
				<!--Carousel Wrapper-->
				<div id="video-carousel-piu_visti" class="carousel slide carousel-fade" data-ride="carousel">
				  <!--Slides-->
				  <div class="carousel-inner" role="listbox" id="columnCenterRicerca">
				    
					    <c:set var = "first" scope = "session" value = "${true}"/>
					    <c:set var = "cont" scope = "request" value ="${0}" />
					    
					    <c:forEach items="${risultatoRicerca}" var="r">
				       	
					    	<c:if test="${first == true && cont % 5 == 0}">
					        	<div class="carousel-item active">
					       	</c:if>
							
							<c:if test="${first == false && cont % 5 == 0 }">
								<div class="carousel-item">
							</c:if>
							
							<div id="contenutoCarousel" class="inline" align="center">
								<div class=rowUp>
									<iframe id="single_video" src="${r.url}" class="video-fluid"></iframe>
								</div>
								<div class="rowDown">
									<a class="badge badge-light"  id="textNomeVideo" href="pagina_video?url=${r.url}">${r.nome}</a> 
								</div>
							</div>
							
							<c:set var = "cont" scope = "request" value ="${cont + 1}" />
							<c:set var = "first" scope = "session" value = "${false}"/>
							
							<c:if test="${cont % 5 == 0}">	
								</div>
							</c:if>
						</c:forEach>
						
						<c:if test="${cont % 5 != 0}">
							</div>
						</c:if>
					</div>
				</div>
				  <!--/.Slides-->
			</div>
			
			<div class="column col-sm-3">
					<div id="filtri"></div>
				</div>
			</div> 
				<!--Carousel Wrapper-->	
		</div>
	</div>		
</body>
</html>