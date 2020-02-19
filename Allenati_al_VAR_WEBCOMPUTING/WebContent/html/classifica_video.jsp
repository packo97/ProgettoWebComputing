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
	
	<link rel="stylesheet" href="../css/classifica.css" type="text/css">
	<script type="text/javascript" src="../js/risultatoRicerca.js"></script>
	<script type="text/javascript" src="../js/classifica_video.js"></script>
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Classifica video</title>
</head>
<body>
	<%@include file="header_default.jsp" %>
	
	<div class="container">
	
  			<div class="row">
  			
  				<div id="rigaButton" align="center" class="column col-sm-12">  					
  					<button id="buttonGiusti" type="button" class="btn btn-outline-secondary">Più giusti</button>
  					<button id="buttonSbagliati" type="button" class="btn btn-outline-secondary">Più sbagliati</button>
  				</div>
  			</div>
  			<div class="row">
  				<div id="classifica_video" class="column col-sm-12">
  				</div>
  			</div>
  	</div>
</body>
</html>