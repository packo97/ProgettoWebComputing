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
	<script type="text/javascript" src="../js/home.js"></script>
	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Home page</title>
</head>

<body>
<%@include file="header_default.jsp" %>
<div class="row">
		<div class="column col-sm-9">
			<div id="ricerca"></div>
		</div>
		<div class="column col-sm-3">
			<div id="filtri"></div>
		</div>
</div>		

<c:if test="${eliminaVideo != null}">
						
					<div class="alert alert-danger alert-dismissible fade show" role="alert">
					  <strong>Video rimosso correttamente!</strong>
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>
</c:if>



<div id="jumbo1" class="jumbotron">

	<h1><span class="badge">Più visti su Allenati al VAR</span></h1>
	
	<!--Carousel Wrapper-->
	<div id="video-carousel-piu_visti" class="carousel slide carousel-fade" data-ride="carousel">
	  
	  <!--Indicators-->
	  <ol class="carousel-indicators">
	    <li data-target="#video-carousel-piu_visti" data-slide-to="0" class="active"></li>
	    <li data-target="#video-carousel-piu_visti" data-slide-to="1"></li>
	    <li data-target="#video-carousel-piu_visti" data-slide-to="2"></li>
	  </ol>
	  <!--/.Indicators-->
	  
	  <!--Slides-->
	  <div class="carousel-inner" role="listbox" id="columnCenter">
	    
	    <c:set var = "first" scope = "session" value = "${true}"/>
	    <c:set var = "cont" scope = "session" value ="${0}" />
	    <c:forEach items="${video_piu_visti}" var="v">
       	
	    	<c:if test="${first == true && cont % 5 == 0}">
	        	<div class="carousel-item active">
	       	</c:if>
			<c:if test="${first == false && cont % 5 == 0 }">
				<div class="carousel-item">
			</c:if>
			<div class="inline" align="center">
				<div class="rowUp">
					
						<iframe id="single_video" src="${v.url}" class="video-fluid"></iframe>
					
				</div>
				<div class="rowDown">
					<a class="badge badge-light"  id="textNomeVideo" href="pagina_video?url=${v.url}">${v.nome}</a> 
				</div>
			</div>
			<c:set var = "cont" scope = "session" value ="${cont + 1}" />
			<c:set var = "first" scope = "session" value = "${false}"/>
			<c:if test="${cont % 5 == 0}">	
				</div>
			</c:if>
		</c:forEach>
		<c:if test="${cont % 5 != 0}">
			</div>
		</c:if>
	  </div>
	  <!--/.Slides-->
	  
	  <!--Controls-->
	  
	  <a id="freccia" class="carousel-control-prev" href="#video-carousel-piu_visti" role="button" data-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	 
	  <a id="freccia" class="carousel-control-next" href="#video-carousel-piu_visti" role="button" data-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	 
	  <!--/.Controls-->
	</div>
	</div> 
	<!--Carousel Wrapper-->
</div>

<div id="jumbo2" class="jumbotron">
	<h1><span class="badge">Recenti</span></h1>
	
	<!--Carousel Wrapper-->
	<div id="video-carousel-recenti" class="carousel slide carousel-fade" data-ride="carousel">
	  <!--Indicators-->
	  <ol class="carousel-indicators">
	    <li data-target="#video-carousel-recenti" data-slide-to="0" class="active"></li>
	    <li data-target="#video-carousel-recenti" data-slide-to="1"></li>
	    <li data-target="#video-carousel-recenti" data-slide-to="2"></li>
	  </ol>
	  <!--/.Indicators-->
	  
	  <!--Slides-->
	  <div class="carousel-inner" role="listbox" id="columnCenter">
	    
	    <c:set var = "first" scope = "session" value = "${true}"/>
	    <c:set var = "cont" scope = "request" value ="${0}" />
	    <c:forEach items="${video_recenti}" var="v">
       	
	    	<c:if test="${first == true && cont % 5 == 0}">
	        	<div class="carousel-item active">
	       	</c:if>
			<c:if test="${first == false && cont % 5 == 0 }">
				<div class="carousel-item">
			</c:if>
			<div class="inline" align="center">
				<div class=rowUp>
				
						<iframe id="single_video" src="${v.url}" class="video-fluid"></iframe>
	
				</div>
				<div class="rowDown">
				 <a class="badge badge-light"  id="textNomeVideo" href="pagina_video?url=${v.url}">${v.nome}</a> 
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
	  <!--/.Slides-->
	  
	  <!--Controls-->
	  
	  <a id="freccia" class="carousel-control-prev" href="#video-carousel-recenti" role="button" data-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	 
	  <a id="freccia" class="carousel-control-next" href="#video-carousel-recenti" role="button" data-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	 
	  <!--/.Controls-->
	</div>
	</div> 
	<!--Carousel Wrapper-->
</div>	

<div id="jumbo3"  class="jumbotron">
	<h1><span class="badge">Originali di Allenati al VAR</span></h1>
	
	<!--Carousel Wrapper-->
	<div id="video-carousel-tutti" class="carousel slide carousel-fade" data-ride="carousel">
	  <!--Indicators-->
	  <ol class="carousel-indicators">
	    <li data-target="#video-carousel-tutti" data-slide-to="0" class="active"></li>
	    <li data-target="#video-carousel-tutti" data-slide-to="1"></li>
	    <li data-target="#video-carousel-tutti" data-slide-to="2"></li>
	  </ol>
	  <!--/.Indicators-->
	  
	  <!--Slides-->
	  <div class="carousel-inner" role="listbox" id="columnCenter">
	    
	    <c:set var = "first" scope = "session" value = "${true}"/>
	    <c:set var = "cont" scope = "request" value ="${0}" />
	    <c:forEach items="${video}" var="v">
       	
	    	<c:if test="${first == true && cont % 5 == 0}">
	        	<div class="carousel-item active">
	       	</c:if>
			<c:if test="${first == false && cont % 5 == 0 }">
				<div class="carousel-item">
			</c:if>
			<div class="inline" align="center">
				<div class=rowUp>	
					<iframe id="single_video" src="${v.url}" class="video-fluid"></iframe>
				</div>
				<div class="rowDown">
				 	<a class="badge badge-light"  id="textNomeVideo" href="pagina_video?url=${v.url}">${v.nome}</a> 
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
	  
	  <!--/.Slides-->
	  
	  <!--Controls-->
	  
	  <a id="freccia" class="carousel-control-prev" href="#video-carousel-tutti" role="button" data-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	 
	  <a id="freccia" class="carousel-control-next" href="#video-carousel-tutti" role="button" data-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	 
	  <!--/.Controls-->
	</div>
	</div> 
	<!--Carousel Wrapper-->
</div>
</body>
</html>