$(document).ready(function(){
	
	if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=storico")
		$("#buttonFiltro").remove();
	if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=preferiti")
		$("#buttonFiltro").remove();
	if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=info")
		$("#buttonFiltro").remove();
	if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=aggiungiVideo")
		$("#buttonFiltro").remove();
	if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=modificaVideo")
		$("#buttonFiltro").remove();
	if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=esito")
		$("#buttonFiltro").remove();
	if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=classifica")
		$("#buttonFiltro").remove();
	if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=classifica_video")
		$("#buttonFiltro").remove();
	if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=statistiche_utente")
		$("#buttonFiltro").remove();
	

	
});






function mostraDurataMin(){
	var slider = document.getElementById("durataMin");
	var output = document.getElementById("valoreDurataMin");
	output.innerHTML = slider.value;
}

function mostraDurataMax(){
	var slider = document.getElementById("durataMax");
	var output = document.getElementById("valoreDurataMax");
	output.innerHTML = slider.value;
}

function mostraCommentiMin(){
	var slider = document.getElementById("numeroCommentiMin");
	var output = document.getElementById("valoreCommentiMin");
	output.innerHTML = slider.value;
}

function mostraCommentiMax(){
	var slider = document.getElementById("numeroCommentiMax");
	var output = document.getElementById("valoreCommentiMax");
	output.innerHTML = slider.value;
}

function mostraFiltri(){

	if($('#filtri').html().length>0)
		$('#rigaFiltri').remove();
	else if($('#filtri').html().length<=0){
		$('#filtri').append("<div id='rigaFiltri'></div");
		if(window.location.href == "http://localhost:8080/ProgettoIngSW-WebComputing/html/gestorePagine?pagina=editor_prova_autovalutazione")
			$('#rigaFiltri').load('filtri_editor.jsp');
		else
			$('#rigaFiltri').load('filtri.jsp');
	}
		

}

function uncheckOtherButton1(){
	
	$("#piuSbagliati").prop("checked",false)
}

function uncheckOtherButton2(){

	$("#piuGiusti").prop("checked",false)
}


function cercaConFiltri(){

	//////////////////CODICE CHE SERVE ALL'EDITOR PROVA AUTOVALUTAZIONE//////////////////////////////////
	var array_tmp_prova_autovalutazione = [];
	
	  
		$('#jumbotronDx').children('.div_video').children('.rowUp').children('iframe').each(function(){
			
			array_tmp_prova_autovalutazione.push($(this).attr("src"));			
		});
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	var filtri = {
			azione : "filtri",
			categoria : $("#categoria").val(),
			durataMinima : $("#durataMin").val(),
			durataMassima : $("#durataMax").val(),
			periodo : $("#periodo").val(),
			difficolta : $("#difficolta").val(),
			squadraA : $("#squadraA").val(),
			squadraB : $("#squadraB").val(),
			cercaIn : $("#cercaIn").val(),
			piuGiusti : $("#piuGiusti").prop("checked"),
			piuSbagliati : $("#piuSbagliati").prop("checked"),
			numeroCommentiMin: $("#numeroCommentiMin").val(),
			numeroCommentiMax: $("#numeroCommentiMax").val(),
			
			url_esclusi_ricerca : array_tmp_prova_autovalutazione,
			
	};
	
	
	$.ajax({
		type: "POST",
		url: "risultatoRicerca",		
		datatype : "json",
		data: JSON.stringify(filtri),
		success: function(data){
			var filtri = JSON.parse(data);
			var risultato = filtri;
			
			
			///////////////////////////////////RICERCA IN HOME E PAGINE RICERCA/////////////////////////////////////////////////////////////////////
			if($('#jumboRicerca').length==0)
				$('#ricerca').append("	<div id='jumboRicerca' class='jumbotron'> " +
						"<h1><span class='badge'>Risultato ricerca</span></h1>"+
						"<div id='video-carousel-ricerca' class='carousel slide carousel-fade' data-ride='carousel'>"+
						"<div class='carousel-inner' role='listbox' id='columnCenterRicerca'>"+
						"</div></div></div>");
			
			$('#columnCenterRicerca').html("");
			for(r in risultato){		
				
				$('#columnCenterRicerca').append("<div class='inline' align='center'><div class=rowUp>"+
						
						"<iframe id='single_video' src='"+risultato[r].url+"' class='video-fluid'></iframe>"+

					"</div>"+
					"<div class='rowDown'>"+
						"<a class='badge badge-light'id='textNomeVideo' href='pagina_video?url="+risultato[r].url+"'>"+risultato[r].nome+"</a>"+
					"</div></div>");
				
			}
			//////////////////////////////FINE RICERCA IN HOME E PAGINE RICERCA/////////////////////////////////////////////////////////////////////
			
			///////////////////////////////////RICERCA IN EDITOR PROVA AUTOVALUTAZIONE/////////////////////////////////////////////////////////////////////
			$('#jumbotronSx').find(".div_video").remove();
			for(r in risultato){		
				
				$('#jumbotronSx').children().children("#filtri_editor").append(	"<div class='div_video'>"+
												"<div class='rowUp'>"+
													"<iframe src='"+risultato[r].url+"' selezionato='false'></iframe>"+
												"</div>"+
												"<div class='rowDown'>"+
													"<a class='badge badge-light'  id='textNomeVideo'>"+risultato[r].nome+"</a>"+
												"</div>"+
											"</div>");	
			}
			loadAtStart();
			//////////////////////////////FINE RICERCA IN EDITOR PROVA AUTOVALUTAZIONE/////////////////////////////////////////////////////////////////////
			
		}	
	});	
}