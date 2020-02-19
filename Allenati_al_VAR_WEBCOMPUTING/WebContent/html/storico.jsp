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

	<script type="text/javascript" src="../js/risultatoRicerca.js"></script>
	<script type="text/javascript" src="../js/storico.js"></script>
	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Storico</title>
</head>

<body>

	<%@include file="header_default.jsp" %>
	
	<div class="container" id="risposte">	
		<ul class="list-group">
		
			<c:forEach items="${storico}" var="s">
				
				<c:if test="${s.risultato==true}">
					<li class="list-group-item list-group-item-success">
						<div id="${s.id}" class="row">
							<a class="column col-sm-7" href="esito?data=${s.data}&&id_esito=${s.id}">
								<div>
								<p class="badge ">Prova di autovalutazione del ${s.data}</p>
								</div>
							</a>
							<div class="column col-sm-4">
								<p class="badge badge-success">Esito: POSITIVO</p>
							</div>
							<div class="column col-sm-1">
								<img class="buttonRipetiProva" src="../img/icona_ripeti.png" data-toggle='modal' data-target='#myModalRipeti'>
							</div>	
						</div>
					</li>
				</c:if>
				
				<c:if test="${s.risultato==false}">
					<li class="list-group-item list-group-item-danger">
						<div id="${s.id}" class="row">
							<a class="column col-sm-7" href="esito?data=${s.data}&&id_esito=${s.id}">
								<div>
									<p class="badge">Prova di autovalutazione del ${s.data}</p>	
								</div>
							</a>
							<div class="column col-sm-4">
								<p class="badge badge-danger">Esito: NEGATIVO</p>
							</div>
							<div class="column col-sm-1">
								<img class="buttonRipetiProva" src="../img/icona_ripeti.png" data-toggle='modal' data-target='#myModalRipeti'>
							</div>		
						</div>	
					</li>
				</c:if>
				
			</c:forEach>
		</ul>
	</div>
	
		<!-- INIZIO CUSTOM ALERT -->
	<div class="modal fade" id="myModalRipeti">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	      
	        <!-- Modal Header -->
	       <div class="modal-header">
	         <h4 class="modal-title">Sei sicuro?</h4>
	         <button type="button" class="close" data-dismiss="modal">&times;</button>
	       </div>
	       
	       <!-- Modal body -->
	       <div class="modal-body">
	         Stai per iniziare una prova di autovalutazione.
	       </div>
	       
	       <!-- Modal footer -->
	       <div class="modal-footer">
	         <button type="button" class="btn btn-danger" data-dismiss="modal">Annulla</button>
	         <a class="btn btn-success" href="prova_autovalutazione?standard=true">Inizia</a>
	       </div>
	       
	     </div>
	   </div>
	</div>
		<!-- FINE CUSTOM ALERT -->
</body>
</html>