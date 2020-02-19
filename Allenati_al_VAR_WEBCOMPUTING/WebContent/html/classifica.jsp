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
	<link rel="stylesheet" href="../css/classifica.css" type="text/css">
	<script type="text/javascript" src="../js/home.js"></script>
	<script type="text/javascript" src="../js/risultatoRicerca.js"></script>

	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Classifica</title>
</head>

<body>
	<%@include file="header_default.jsp" %>
	
	<div class="container">

  			<div class="list-group">
  				<div class="list-group-item">
  					<div class="row">
			    		<div class="column col-sm-1">
   			    			<p>POSIZIONE</p>	                            	
						</div>
	   			    	<div class="column col-sm-4">                           	
							<p>NOME</p>
						</div>
						<div class="column col-sm-4">                           	
							<p>COGNOME</p>
						</div>
						<div class="column col-sm-2">
	  			    		<p>PUNTEGGIO</p>
						</div>
							
					</div>
				</div>
 			   		<c:set var = "cont" scope = "session" value ="${1}"/>
  			    	<c:forEach items="${utenti}" var="u">
  			    		<a href="gestorePagine?pagina=statistiche_utente&&utente=${u.email}" class="list-group-item list-group-item-action">
	  			    		 <div class="row">
	  			    			<div class="column col-sm-1">
			   			    		<p>${cont}</p>	                            	
								</div>
			   			    	<div class="column col-sm-4">                           	
									<p>${u.nome}</p>
								</div>
								<div class="column col-sm-4">                           	
									<p>${u.cognome}</p>
								</div>
								<div class="column col-sm-2">
		   			    			<p>${u.punteggio}</p>
								</div>
								<c:if test="${cont == 1}">
									<div class="column col-sm-1">
			   			    			<img src="../img/icona_primo.png">	                            	
									</div>
								</c:if>
								<c:if test="${cont == 2}">
									<div class="column col-sm-1">
			   			    			<img src="../img/icona_secondo.png">	                            	
									</div>
								</c:if>
								<c:if test="${cont == 3}">
									<div class="column col-sm-1">
			   			    			<img src="../img/icona_terzo.png">	                            	
									</div>
								</c:if>
							</div>
						</a>
						<c:set var = "cont" scope = "session" value ="${cont + 1}" />
					</c:forEach>
				

  			</div>

		</div>
</body>
</html>