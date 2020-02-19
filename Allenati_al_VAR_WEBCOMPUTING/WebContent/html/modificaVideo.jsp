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
	
	<link rel="stylesheet" href="../css/aggiungi_video.css" type="text/css">
	<link rel="stylesheet" href="../css/home.css" type="text/css">
	
	<script type="text/javascript" src="../js/pagina_video.js"></script>
	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Modifica Video</title>
</head>

<body>
	<%@include file="header_default.jsp" %>
	<div id="filtri"></div>

<div class="container">

  			<h1><span class="badge">Modifica video</span></h1>	
  			
  			<div class="row">
  			<div class="column left">
  			
  			
  			<form id="formContent">
	      	
	      	
	      	<div id="formModifica">
	      	<input type="text" id="nome" class="fadeIn second" name="nome" placeholder="nome" value="${nome}" required/>
				<div id="nomeErrato" class="inactive">
	      				  <div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci un nome!</strong></div>	
	      			
	      		</div>				
				
				
			<div class="fadeIn second" id="categoria" >
				<select id="cat" name="categoria" required>
					<option value="" disabled selected>${categoria}</option>		
					<option value="RIGORE">RIGORE</option>
					<option value="ROSSO">ESPULSIONE</option>
					<option value="AMMONIZIONE">AMMONIZIONE</option>
					<option value="FUORIGIOCO">FUORIGIOCO</option>					
					<option value="FALLO DI MANO">FALLO DI MANO</option>
					<option value="GOAL">GOAL</option>
				
				</select>
			</div>	
			<div id="controlloCategoria" class="inactive">
				  <div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Seleziona una categoria!</strong></div>	
			
			</div>
			
			<div class="fadeIn second" id="grado di difficoltà" >
				<select id="sel" name="grado di difficoltà" required>
					<option value="" disabled selected>${difficolta}</option>
					<option value="FACILE">FACILE</option>
					<option value="NORMALE">NORMALE</option>
					<option value="DIFFICILE">DIFFICILE</option>					
				</select>
			</div>
			<div id="controlloDifficolta" class="inactive">
				  <div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Seleziona un grado di difficolt&agrave;!</strong></div>	
			
			</div>
			
			<input type="text" id="opzioneRispostaCorretta" class="fadeIn second" name="opzioneRispostaCorretta" placeholder="opzione di risposta corretta" value="${rispostaCorretta}" required/>
			<div id="controlloOpzioneUno" class="inactive">
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci l'opzione di risposta corretta!</strong></div>	
	      	</div>
	      	
			<input type="text" id="opzioneRispostaErrata" class="fadeIn second" name="opzioneRispostaErrata" placeholder="opzione di risposta errata" value="${rispostaErrata}" required/>
			<div id="controlloOpzioneDue" class="inactive">
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci l'opzione di risposta errata!</strong></div>	
	      	</div>
			
			<textarea class="fadeIn second" id="descrizione"  name="descrizione" placeholder="descrizione" required>${descrizione}</textarea>
			<div id="controlloDescrizione" class="inactive">
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci una descrizione per il video!</strong></div>	
	      	</div>
	      	<div id="controlloLunghezzaDescrizione" class="inactive">
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>La descrizione che hai inserito è troppo lunga!</strong></div>	
	      	</div>
			
			<input type="text" id="durata" class="fadeIn second" name="durata" placeholder="Durata" required/>
			<div id="controlloDurata" class="inactive">
	      		<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci la durata!</strong></div>	
	      	</div>
			<input type="text" id="squadraA" class="fadeIn second" name="squadraA" placeholder="Squadra A" required/>
			<div id="controlloSquadraA" class="inactive">
	      		<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci la squadra A!</strong></div>	
	      	</div>
			<input type="text" id="squadraB" class="fadeIn second" name="squadraB" placeholder="Squadra B" required/>
			<div id="controlloSquadraB" class="inactive">
	      		<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci la squadra B!</strong></div>	
	      	</div>
					  
			<div class="btn-group-toggle" data-toggle="buttons">
				<a class="btn btn-secondary" id="Indietro" href="pagina_video?url=${url}" type="submit">Indietro</a>
	      		<a class="btn btn-primary" id="Aggiungi" onclick="javascript:controllaCaratteristiche('<c:out value="${url}"/>')" href="javascript:void(0)" type="submit">Salva</a>
	      		
	      			      		

			</div>
			</div>		
	      	
	    	</form>
	    </div>
	    <div id="anteprimaVideo" class="column right">
				<iframe width='90%' id='video' src='${url}' height='56%'></iframe>	
	    	</div>
			</div> 
	    	
</div>

	<script>
			
		function controllaCaratteristiche(url){
			
			var url = url;
			
			var $temp= $("form");
			var nome = $("#nome").val();
			var descrizione = $("#descrizione").val();
			var opzioneUno = $("#opzioneRispostaCorretta").val();
			var opzioneDue = $("#opzioneRispostaErrata").val();
			var durata = $("#durata").val();
			var squadraA = $("#squadraA").val();
			var squadraB = $("#squadraB").val();
			var selectorDifficolta = document.getElementById('sel');
		    var valueDifficolta = selectorDifficolta[selectorDifficolta.selectedIndex].value;
		    var selectorCategoria = document.getElementById('cat');
		    var valueCategoria = selectorCategoria[selectorCategoria.selectedIndex].value;
		   	var lunghezzaDescrizione = descrizione.length;

		   	var bool = true;
		   	
		   	if(nome=="")
		   	{
				$temp.find("#nomeErrato").removeClass("inactive");
				bool=false;
		   	}
		   	else
		   	{
				$temp.find("#nomeErrato").addClass("inactive");
		   	}
		   	
			if(valueDifficolta=="")
			{
				$temp.find("#controlloDifficolta").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloDifficolta").addClass("inactive");
			}
			
			
			if(valueCategoria=="")
			{
				$temp.find("#controlloCategoria").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloCategoria").addClass("inactive");

			}

			if(descrizione == "")
			{
				$temp.find("#controlloDescrizione").removeClass("inactive");
				$temp.find("#controlloLunghezzaDescrizione").addClass("inactive");
				bool=false;

			}
			else if(lunghezzaDescrizione>100)
			{
				$temp.find("#controlloLunghezzaDescrizione").removeClass("inactive");
				$temp.find("#controlloDescrizione").addClass("inactive");
				bool=false;

			}
			else
			{
				$temp.find("#controlloDescrizione").addClass("inactive");
				$temp.find("#controlloLunghezzaDescrizione").addClass("inactive");

			}
			
			if(opzioneUno == "")
			{
				$temp.find("#controlloOpzioneUno").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloOpzioneUno").addClass("inactive");
			}
			
			if(opzioneDue == "")
			{
				$temp.find("#controlloOpzioneDue").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloOpzioneDue").addClass("inactive");
			}
			
			if(durata == "")
			{
				$temp.find("#controlloDurata").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloDurata").addClass("inactive");
			}
			
			if(squadraA == "")
			{
				$temp.find("#controlloSquadraA").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloSquadraA").addClass("inactive");
			}
			
			if(squadraB == "")
			{
				$temp.find("#controlloSquadraB").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloSquadraB").addClass("inactive");
			}

			if(bool==true){
			
				$.ajax({
					type: "GET",
					url: "gestoreVideo?link="+url+"&&nome="+nome+"&&desc="+descrizione+"&&diff="+valueDifficolta+"&&cat="+valueCategoria+"&&opC="+opzioneUno+"&&opE="+opzioneDue+"&&squadraA="+squadraA+"&&squadraB="+squadraB+"&&durata="+durata,
					data: {modificaNome: nome},
					success: function(info){
						$("document").ready(function(){
								if(info.match("tuttoAPosto"))
								{
									window.location.href="gestoreVideo?videoModificato=true&&link="+url+"&&nome="+nome+"&&desc="+descrizione+"&&diff="+valueDifficolta+"&&cat="+valueCategoria+"&&opC="+opzioneUno+"&&opE="+opzioneDue+"&&squadraA="+squadraA+"&&squadraB="+squadraB+"&&durata="+durata;
								}
								else
								{
									var $temp= $("form");
									$temp.find("#nomeErrato").removeClass("inactive");
									$("#nomeErrato").html(info);
								}
							
							})
						}
					});
				}
		}
		
	</script>
</body>
</html>