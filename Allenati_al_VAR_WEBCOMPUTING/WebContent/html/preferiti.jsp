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
	
	<link rel="stylesheet" href="../css/preferiti.css" type="text/css">
	<link rel="stylesheet" href="../css/home.css" type="text/css">

	<script type="text/javascript" src="../js/risultatoRicerca.js"></script>
	<script type="text/javascript" src="../js/preferiti.js"></script>

	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Preferiti</title>
</head>
<body>

	<%@include file="header_default.jsp" %>

	<div class="container">
		<div align="center" class="list-group">			
	    	
	    	<c:forEach items="${video_preferiti}" var="v">
	    		<div url="${v.url}" class="row">
	    			<div class="list-group-item list-group-item-action">
	    				<a href="pagina_video?url=${v.url}">
	  			    		<div id="colonnaIframe" class="column col-sm-5">
								<iframe class="embed-responsive-item" src="${v.url}"></iframe>		
							</div>
							<div class="column col-sm-6">
	   			    			<p>${v.nome}</p>
	  			    		</div>
 			    		</a>
						<div class="column col-sm-1">
  			    			<img class="buttonRemovePreferiti" src="../img/icona_rimuovi.png">
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>		
</body>
</html>