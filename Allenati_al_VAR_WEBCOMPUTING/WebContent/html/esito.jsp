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
	<link rel="stylesheet" href="../css/esito.css" type="text/css">
	<link rel="stylesheet" href="../css/home.css" type="text/css">
	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Esito Prova</title>
	
</head>

<body >
	
	<%@include file="header_default.jsp" %>
	<div id="filtri"></div>
	
	<div class="container" id="risposte">
	
		<h1 style="text-align: center;" ><span class="badge badge-dark">ESITO PROVA AUTOVALUTAZIONE</span></h1>
		<br>
		<br>
		<ul class="list-group">
		
			<c:forEach items="${esito}" var="v">
				<c:if test="${v.risposte.rispostaUtente==true}">
					<li class="list-group-item list-group-item-success">
				</c:if>
				<c:if test="${v.risposte.rispostaUtente==false}">
					<li class="list-group-item list-group-item-danger">
				</c:if>
					<div class="row">
					
						<div class="column col-sm-5">
				    	
							<iframe class="embed-responsive-item" src="${v.url}"></iframe>
							
							
						</div>
						
						<div class="column col-sm-7">
							<p>${v.nome}</p>
							<p>${v.categoria[0].nome}</p>
							<p>${v.difficolta}</p>
							<p>${v.risposte.opzioneCorretta}</p>
						</div>	
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	
</body>
</html>