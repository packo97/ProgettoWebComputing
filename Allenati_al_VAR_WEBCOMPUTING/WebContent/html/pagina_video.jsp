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
	<link rel="stylesheet" href="../css/pagina_video.css" type="text/css">
	<script type="text/javascript" src="../js/pagina_video.js"></script>
	
	<script src="https://www.youtube.com/iframe_api" ></script>
	
	<script>
	
		var player;
	
		function onYouTubeIframeAPIReady() {
		    player = new YT.Player('video-placeholder', {
		        width: 600,
		        height: 400,
		        videoId: '${id}',
		        playerVars: {
		            color: 'white'
		        },
		        events: {
		            onReady: initialize
		        }
		    });
		}

		function initialize(){

		    // Update the controls on load
		    updateTimerDisplay();
		    updateProgressBar();

		    // Clear any old interval.
		    clearInterval(time_update_interval);

		    // Start interval to update elapsed time display and
		    // the elapsed part of the progress bar every second.
		    time_update_interval = setInterval(function () {
		        updateTimerDisplay();
		        updateProgressBar();
		    }, 1000);


		    $('#volume-input').val(Math.round(player.getVolume()));
		}
		
	</script>
	
	<meta charset="UTF-8">
	
	<title>ALLENATI AL VAR - Pagina video</title>
	
</head>
<body>

	<%@include file="header_default.jsp" %>
	
	<div id="cornice" class="container">
	
		<c:if test="${link != null}">
				
				<div class="alert alert-success alert-dismissible fade show" role="alert">
				  <strong>Video aggiunto correttamente!</strong>
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
				</div>
 		</c:if>
		 		
 		<c:if test="${modificato != null}">
				
				<div class="alert alert-success alert-dismissible fade show" role="alert">
				  <strong>Video modificato correttamente!</strong>
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
				</div>
 		</c:if>

	 	<div  class="row">
		 	<div id="colonnaSx" class="column col-sm-7">
				<div id= "rigaButton" class="row">
					<div align="right" class="column col-sm-12">
						<div id="div_button_gestione" class="btn-group btn-group-toggle" data-toggle="buttons">
							
							<c:set var = "isPreferito" scope = "session" value = "${isPreferito}"/>
							
							<c:if test="${isPreferito == false}">
								<a class="btn btn-success" id="addPreferiti" type="submit" onclick="inserisciPreferiti('${url}')">Preferiti</a>
							</c:if>	
							
							<c:if test="${isPreferito == true}">
								<a class="btn btn-success" id="addPreferiti" type="submit" onclick="inserisciPreferiti('${url}')">Rimuovi</a>
							</c:if>	
							
							<c:if test="${amministratore == true}">
								  <a class="btn btn-primary" id="button_modifica" type="submit" href="gestorePagine?pagina=modificaVideo&&url=${url}">Modifica</a>
								  <a class="btn btn-danger" id="button_elimina" href="#" data-toggle="modal" data-target="#Eliminazione">Elimina</a>
							</c:if>
						</div>
					</div>
				</div>
		 		
				<div id="video-placeholder"></div>
				<div id="controls"> </div>
				
				<div id="dati_video">
					<div id="primaRiga" class="row">
						<p class="badge badge-dark column col-sm-9" id="nome_video"> ${nome}</p>	
						<p class="badge badge-light column col-sm-3" id="visualizzazioni"> ${visualizzazioni} visualizzazioni</p>		
					</div>
					<div class="row"> 
						<p class="badge badge-light column col-sm-12" id="descrizione"> ${descrizione}</p>
					</div>
					<div class="row"> 
						<p class="badge badge-light column col-sm-12" id="categoria"> Categoria: ${categoria} </p>
					</div>
					<div class="row"> 
						<p class="badge badge-light column col-sm-12"id="difficolta"> ${difficolta} </p>
					</div>		
				</div>
			</div>
		
			<div align="right" id="colonnaDx" class="column col-sm-5">
				<div  id="lista_commenti" class="up" >				
					<c:forEach items="${lista_commenti}" var="c">     	                            	
						<div class="container mt-3">
							 <div class="media border p-3">
						  		<img id="img_referee" src="../img/referee.png" alt="referee" class="mr-3 mt-3 rounded-circle" >       
						    	<div class="media-body">
						      		<h4>${c.autore.nome} ${c.autore.cognome}</h4>
						      		<small><i id="data">Posted on ${c.data}</i></small>
						      		<p id="commento"> ${c.testo} </p>      
						    	</div>
						  	</div>
						</div>				
					</c:forEach>
				</div>
			
				<div  id="underPanel" class="down">
					<textarea class="form-control" id="textCommento"  name="commento" placeholder="Scrivi un commento..."></textarea>
					<input  id="button_invia" class="form-control" type="submit" value="Invia" onclick="inserisciCommento('${url}')"/>
				</div>
			
				<div align="center" id="risposte" class="three"> 
					
					<c:if test="${ordineRisposte==0}">
						<button class="btn btn-dark" id="rispostaCorretta" onclick="inserisciRisposta('${url}','${true}')"> ${rispostaCorretta} </button>
						<button class="btn btn-dark" id="rispostaErrata" onclick="inserisciRisposta('${url}','${false}')"> ${rispostaErrata} </button>
					</c:if>
					
					<c:if test="${ordineRisposte==1}">
						<button class="btn btn-dark" id="rispostaErrata" onclick="inserisciRisposta('${url}','${false}')"> ${rispostaErrata} </button>
						<button class="btn btn-dark" id="rispostaCorretta" onclick="inserisciRisposta('${url}','${true}')"> ${rispostaCorretta} </button>
					</c:if>
					
				</div>
			</div>	
		</div>	
	</div>
	
		<!-- INIZIO CUSTOM ALERT -->
		
	<div class="modal fade" id="Eliminazione">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	      
	        <!-- Modal Header -->
	       <div class="modal-header">
	         <h4 class="modal-title">Sei sicuro?</h4>
	         <button type="button" class="close" data-dismiss="modal">&times;</button>
	       </div>
	       
	       <!-- Modal body -->
	       <div class="modal-body">
	         Vuoi eliminare davvero questo video?
	       </div>
	       
	       <!-- Modal footer -->
	       <div class="modal-footer">
	         <button type="button" class="btn btn-warning" data-dismiss="modal">Annulla</button>
	         <a class="btn btn-danger" href="gestoreVideo?eliminaVideo=${url}">Elimina</a>
	       </div>
	       
	     </div>
	   </div>
	 </div>
	
		<!-- FINE CUSTOM ALERT -->
</body>
</html>