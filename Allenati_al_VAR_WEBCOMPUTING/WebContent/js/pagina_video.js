$(document).ready(function(){
	$("#buttonFiltro").remove();
});


window.addEventListener("load", function() {
    document.getElementById("rispostaErrata").addEventListener("click", checkButtonWrong);
    document.getElementById("rispostaCorretta").addEventListener("click", checkButtonCorrect);    

	document.getElementById("rispostaErrata").addEventListener("mouseover", changeColor1);
	document.getElementById("rispostaCorretta").addEventListener("mouseover", changeColor2);
	document.getElementById("rispostaErrata").addEventListener("mouseleave", function(){
		if(document.getElementById("rispostaErrata").className == "btn btn-warning")
			document.getElementById("rispostaErrata").className = "btn btn-dark";
	});
	document.getElementById("rispostaCorretta").addEventListener("mouseleave", function(){
		if(document.getElementById("rispostaCorretta").className == "btn btn-warning")
			document.getElementById("rispostaCorretta").className = "btn btn-dark";
	});
    
});


function checkButtonCorrect() {
    document.getElementById("rispostaCorretta").className = "btn btn-success";
    document.getElementById("rispostaErrata").className = "btn btn-dark";
    	
 }
function checkButtonWrong() {
	document.getElementById("rispostaErrata").className = "btn btn-danger";
	document.getElementById("rispostaCorretta").className = "btn btn-dark";
    
}

function changeColor1(){
	if(document.getElementById("rispostaErrata").className != "btn btn-danger")
	document.getElementById("rispostaErrata").className = "btn btn-warning";

}

function changeColor2(){
	if(document.getElementById("rispostaCorretta").className != "btn btn-success")
		document.getElementById("rispostaCorretta").className = "btn btn-warning";

	
}

function inserisciPreferiti(url){

	var dati = {			
			azione : "preferiti",
			url_video : url
	};
	
	$.ajax({
		type: "POST",
		url: "pagina_video",		
		datatype : "json",
		data: JSON.stringify(dati),
		success: function(data){
			var c = JSON.parse(data);
			if($('#addPreferiti').text() == "Preferiti")
				$('#addPreferiti').text('Rimuovi');
			else
				$('#addPreferiti').text('Preferiti');

			tempAlert(2000,c.isPreferito);
		}	
	});
}

function tempAlert(duration,pref){
	
     var el = document.createElement("div");

     if(pref == "true"){
    	
    	 el.className = "alert alert-success" ;
         el.innerHTML ="Il video &egrave; stato aggiunto alla sezione Preferiti";
     }
     else if(pref == "false"){
    	 
    	 el.className = "alert alert-danger" ;
         el.innerHTML = "Il video &egrave; stato rimosso dalla sezione Preferiti";
     }
    
     setTimeout(function(){
	      el.parentNode.removeChild(el);
	     },duration);
     
     document.getElementById("colonnaSx").appendChild(el); 
}

function inserisciCommento(url){

	var c = {
			azione : "commento",
			testo : $("#textCommento").val(),
			url_video : url
			
	};

	$.ajax({
		type: "POST",
		url: "pagina_video",		
		datatype : "json",
		data: JSON.stringify(c),
		success: function(data){
			var c = JSON.parse(data);
			$("#lista_commenti").append("<div class='container mt-3'>" +
										"<div class='media border p-3'>" +
											"<img id='img_referee' src='../img/referee.png' alt='referee' class='mr-3 mt-3 rounded-circle'> " +      
											"<div class='media-body'>" +
												"<h4>" + c.nome + " " + c.cognome + "</h4>" +
												"<small><i id='data'>Posted on " + c.data + "</i></small>" +
												"<p id='commento'>" + c.testo + "</p>" +   
											"</div>" +
										"</div>" +
										"</div>");
			 
			$("#textCommento").val("");
		}	
	});	
}

function inserisciRisposta(url,risposta){
	
	var c = {
			azione : "risposta",
			valore : risposta,
			url_video : url		
	};

	$.ajax({
		type: "POST",
		url: "pagina_video",		
		datatype : "json",
		data: JSON.stringify(c),
		success: function(data){
			
		}	
	});	
}
