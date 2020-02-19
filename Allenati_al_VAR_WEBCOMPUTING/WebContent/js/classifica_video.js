$("document").ready(loadClassifica);

function loadClassifica(){
	
	$('#buttonGiusti, #buttonSbagliati').off('click').click(function(event) {
		
		var valore;
		if($(this).attr("id") == "buttonGiusti")
			valore = "true";
		else if($(this).attr("id") == "buttonSbagliati")
			valore = "false";
		
		var c = {
				azione : "classifica",
				filtro : valore,
		};

		$.ajax({
			type: "POST",
			url: "gestorePagine",		
			datatype : "json",
			data: JSON.stringify(c),
			success: function(data){
				var classifica_video = JSON.parse(data);
				
				$("#classifica_video").children(".list-group").remove();
				
				$("#classifica_video").append("<div class='list-group'>"+
													"<a class='list-group-item list-group-item-action'>"+
														"<div class='row'>"+
		  													"<div class='column col-sm-1'>Posizione</div>"+
		  													"<div class='column col-sm-5'></div>"+
		  													"<div class='column col-sm-4'>Nome</div>"+
		  													"<div class='column col-sm-2'>Numero Risposte</div>"+
			  											"</div>" +
		  											"</a>" +
		  										"</div>");
				var posizione = 1;
				
				for(s in classifica_video.video){
					
					$(".list-group").append("<div class='list-group-item'>"+
												"<a href=pagina_video?url="+classifica_video.video[s].url+" class='list-group-item list-group-item-action'>"+
	  												"<div class='row'>"+
		  												
		  													"<div class='column col-sm-1'>"+
		  														posizione +
		  													"</div>"+
		  													"<div class='column col-sm-5'>"+
	  															"<iframe src='"+classifica_video.video[s].url+"'></iframe>" +
	  														"</div>"+
		  													"<div class='column col-sm-4'>"+
		  														classifica_video.video[s].nome + 
		  													"</div>"+
		  													"<div class='column col-sm-2'>"+
		  														classifica_video.risposteCorrette[s] +
		  													"</div>"+
		  												"</div>"+
		  											"</a>"+
  											"</div>");
					posizione= posizione + 1;
				}
			}	
		});	
		
	});
	
	

}