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
  	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	
	<link rel="stylesheet" href="../css/statistiche_utente.css" type="text/css">
	<script type="text/javascript" src="../js/risultatoRicerca.js"></script>
	<script type="text/javascript" src="../js/statistiche_utente.js"></script>
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Statistiche</title>
</head>
<body>
	<%@include file="header_default.jsp" %>

	<div class="container">

  			<div id="${utente.email}"class="row">
  				
  				<div class="column col-sm-1">
  					<img id="img_referee" src="../img/referee.png">
  				</div>
  				<div class="column col-sm-3">
  					<p>${utente.nome} ${utente.cognome}</p>					 
  				</div>
  				<div class="column col-sm-1">
  						<img id="img_referee" src="../img/icona_calendario.png">
  					</div>
  					<div class="column col-sm-3">
  						<p>${utente.dataRegistrazione}</p>  
  					</div>
  				<div class="column col-sm-1">
  					<img id="img_referee" src="../img/icona_bersaglio.png">
  				</div>
  				<div class="column col-sm-3">
  					<p>${utente.punteggio}</p>			 
  				</div>
  			</div>
  					
  			<div class="row"> 			
  				<div class="column col-sm-6">
  					<canvas id="graficoHorizontalBarCategorie"></canvas>
  				</div>
  				<div class="column col-sm-6">
  				</div>
  			</div>
  			<div class="row">
  				<div class="column col-sm-6">
  				</div>
  				<div class="column col-sm-6">
  					<canvas id="graficoPieChartCategorie"></canvas>
  				</div>
  			</div>
  			
  			<div class="row">
  				<div class="column col-sm-6">
  					<canvas id="graficoPieChartProveAutovalutazione"></canvas>
  				</div>
  				<div class="column col-sm-6">
  				</div>
  			</div>
  			
  			<div class="row">
  				<div class="column col-sm-12">
  					<canvas id="graficoLineChartCategorie"></canvas>
  				</div>
  			</div>	
  	</div>

</body>
</html>