$("document").ready(loadAtStart);

function loadAtStart(){
	
	$(".div_video").draggable({
	    revert: true
	  });

	  $(".contenitore").droppable({
	    accept: '.div_video',
	    drop: function(event, ui) {
	    
	    if($("#buttonStorico").attr("selezionato")=="false")
	    	$(this).append($(ui.draggable));
	   
	      
	      var c = {
					azione : "aggiungi_droppati",
					url_droppato: ui.draggable.children(".rowUp").children("iframe").attr("src"),
					
			};

			$.ajax({
				type: "POST",
				url: "prova_autovalutazione",		
				datatype : "json",
				data: JSON.stringify(c),
				success: function(data){
					var c = JSON.parse(data);
					$("#buttonInizia").remove();
				}	
			});	
	    }
	  });
	  
	  
	  
	  
	  $('.div_video').off('click').click(function(event) {
		  	
		    if($(this).children("div").children("iframe").attr("selezionato") == "false"){
		    	$(this).children("div").children("iframe").attr("selezionato","true");
		    	$(this).children("div").children("iframe").css("border", "solid 3px #007bff");
		    }
		    else{
		    	$(this).children("div").children("iframe").attr("selezionato","false");
		    	$(this).children("div").children("iframe").css("border", "");
		    }
		});
	  
	  $('.rigaVideoEsito').off('click').click(function(event) {
		  	
		    if($(this).attr("selezionato") == "false"){
		    	$(this).attr("selezionato","true");
		    	$(this).attr("class", "rigaVideoEsito row badge badge-primary");
		    }
		    else{
		    	$(this).attr("selezionato","false");
		    	$(this).attr("class", "rigaVideoEsito row badge badge-light");
		    }
		});
	  
	  
	  
	  $("#buttonAggiungiSelezionati, #buttonAggiungiTutti").off('click').click(function(){
		 
		  var array_selezionati = [];
		  var button_cliccato = this.id;
		  
			$('#jumbotronSx').children('.div_video').children('.rowUp').children('iframe').each(function(){
				if(button_cliccato == "buttonAggiungiSelezionati" && $(this).attr("selezionato") == "true"){
					array_selezionati.push($(this).attr("src"));
					$(this).parent(".rowUp").parent(".div_video").remove();
				}
				else if(button_cliccato == "buttonAggiungiTutti"){
					array_selezionati.push($(this).attr("src"));
					$(this).parent(".rowUp").parent(".div_video").remove();
				}
					
					
			});
			
			$("#jumbotronSx").children("#lista_esiti").children(".list-group-item").each(function(){
				$(this).children(".rigaVideoEsito").each(function(){
					if(button_cliccato == "buttonAggiungiSelezionati" && $(this).attr("selezionato") == "true"){
						array_selezionati.push($(this).attr("url"));
					}
					else if(button_cliccato == "buttonAggiungiTutti"){
						if($(this).parent().length){
							array_selezionati.push($(this).attr("url"));
						}
						
					}
				})
			});
			
			
			var c = {
					azione : "aggiungi_selezionati",
					url_selezionati: array_selezionati,
					
			};

			$.ajax({
				type: "POST",
				url: "prova_autovalutazione",		
				datatype : "json",
				data: JSON.stringify(c),
				success: function(data){
					var c = JSON.parse(data);
					var selezionati = c;
					$("#jumbotronDx").children('.div_video').remove(); 
					for(s in selezionati){		
						
						$('#jumbotronDx').append("<div class='div_video'>" +
													"<div class='rowUp'>"+
														"<iframe src='"+ selezionati[s].url +"' selezionato='false'></iframe>" +
													"</div>" +
													"<div class='rowDown'>" +
														"<a class='badge badge-light'  id='textNomeVideo'>" + selezionati[s].nome + "</a>"+
													"</div>"+
												"</div>");
						
					}
					
					loadAtStart();
					
				}	
			});	
		  
		  
	  });
	  
	  $("#buttonRimuoviSelezionati, #buttonRimuoviTutti").off('click').click(function(){
			 
		 
		  var array_selezionati = [];
		  var button_cliccato = this.id;
		  
			$('#jumbotronDx').children('.div_video').children('.rowUp').children('iframe').each(function(){
				if(button_cliccato == "buttonRimuoviSelezionati" && $(this).attr("selezionato") == "true"){
					$("#comandiDX").children("#buttonInizia").remove();
					array_selezionati.push($(this).attr("src"));
					if($("#buttonStorico").attr("selezionato")=="false")
						$('#jumbotronSx').append("<div class='div_video'>" +
								"<div class='rowUp'>"+
									"<iframe src='"+ $(this).attr("src") +"' selezionato='false'></iframe>" +
								"</div>" +
								"<div class='rowDown'>" +
									"<a class='badge badge-light'  id='textNomeVideo'>" + $(this).parent(".rowUp").siblings(".rowDown").children("#textNomeVideo").html() + "</a>"+
								"</div>"+
							"</div>");
				}
				else if(button_cliccato == "buttonRimuoviTutti"){
					$("#comandiDX").children("#buttonInizia").remove();
					array_selezionati.push($(this).attr("src"));
					if($("#buttonStorico").attr("selezionato")=="false")
						$('#jumbotronSx').append("<div class='div_video'>" +
								"<div class='rowUp'>"+
									"<iframe src='"+ $(this).attr("src") +"' selezionato='false'></iframe>" +
								"</div>" +
								"<div class='rowDown'>" +
									"<a class='badge badge-light'  id='textNomeVideo'>" + $(this).parent(".rowUp").siblings(".rowDown").children("#textNomeVideo").html() + "</a>"+
								"</div>"+
							"</div>");
				}
					
					
			});
			
			
			var c = {
					azione : "rimuovi_selezionati",
					url_selezionati: array_selezionati,
					
			};

			$.ajax({
				type: "POST",
				url: "prova_autovalutazione",		
				datatype : "json",
				data: JSON.stringify(c),
				success: function(data){
					var c = JSON.parse(data);
					var selezionati = c;
					$("#jumbotronDx").children('.div_video').remove();
				
					if(array_selezionati.length>0)
						$("#buttonInizia").remove();
					
					for(s in selezionati){		
						
						$('#jumbotronDx').append("<div class='div_video'>" +
													"<div class='rowUp'>"+
														"<iframe src='"+ selezionati[s].url +"' selezionato='false'></iframe>" +
													"</div>" +
													"<div class='rowDown'>" +
														"<a class='badge badge-light'  id='textNomeVideo'>" + selezionati[s].nome + "</a>"+
													"</div>"+
												"</div>");
						
					}
					
					loadAtStart();
					
				}	
			});	
		  
		  
	  });
	  
	  $("#buttonSearch").off('click').click(function(){
			 
		  	$('#jumbotronSx').find("#lista_esiti").remove();
		  	$("#buttonStorico").attr("selezionato",false);
		  	var array_tmp_prova_autovalutazione = [];
			
		  
			$('#jumbotronDx').children('.div_video').children('.rowUp').children('iframe').each(function(){
				
				array_tmp_prova_autovalutazione.push($(this).attr("src"));			
			});
		  
			
			var c = {
					azione : "cerca",
					testoRicerca : $("#inputTextTestoRicerca").val(),
					url_esclusi_ricerca : array_tmp_prova_autovalutazione,
			};

			$.ajax({
				type: "POST",
				url: "prova_autovalutazione",		
				datatype : "json",
				data: JSON.stringify(c),
				success: function(data){
					var cercati = JSON.parse(data);
					
					$('#jumbotronSx').find(".div_video").remove();
					for(c in cercati){		
						
						$('#jumbotronSx').append(	"<div class='div_video'>"+
														"<div class='rowUp'>"+
															"<iframe src='"+cercati[c].url+"' selezionato='false'></iframe>"+
														"</div>"+
														"<div class='rowDown'>"+
															"<a class='badge badge-light'  id='textNomeVideo'>"+cercati[c].nome+"</a>"+
														"</div>"+
													"</div>");	
					}
					
					loadAtStart();
					
				}	
			});	
		  
		  
	  });
	  
	  $("#buttonStorico").off('click').click(function(){
			 
		  
			
			var c = {
					azione : "mostraStorico",
			};

			$.ajax({
				type: "POST",
				url: "prova_autovalutazione",		
				datatype : "json",
				data: JSON.stringify(c),
				success: function(data){
					var storico = JSON.parse(data);
					
					if($("ul[id='lista_esiti']").length>0){
						$('#jumbotronSx').find("#lista_esiti").remove();
						$("#buttonStorico").attr("selezionato",false);
						return;
					}
					
					$("#buttonStorico").attr("selezionato",true);
					$('#jumbotronSx').find(".div_video").remove();
					
					$('#jumbotronSx').append("<ul id='lista_esiti' class='list-group'></ul>");
					for(s in storico){		
						
						if(storico[s].risultato == true)
							$('#lista_esiti').append(
															"<li class='list-group-item list-group-item-success'>"+
																
																	"<div class='row rigaEsito "+storico[s].id+"'>"+
																	
																		"<div id='dati' class='column col-sm-6'>"+
																			"<p id='"+ storico[s].id +"' class='badge badge-light'>Prova di autovalutazione del "+ storico[s].data +"</p>"+
																		"</div>" +
																		"<div class='column col-sm-4'>"+
																			"<p class='badge badge-success'>Esito: POSITIVO</p>"+
																		"</div>" +
																		"<div class='column col-sm-2'>"+
																			"<a class='buttonRipetiProva "+ storico[s].id + "' data-toggle='modal' data-target='#myModalRipeti'><img src='../img/icona_ripeti.png'></a>"+
																		"</div>" +
																	"</div>"+
																
															"</li>");
						else if(storico[s].risultato == false)
							$('#lista_esiti').append(	
															"<li class='list-group-item list-group-item-danger'>"+
																
																"<div class='row rigaEsito "+storico[s].id+"'>"+
																
																	"<div id='dati' class='column col-sm-6'>"+
																		"<p id='"+ storico[s].id +"' class='badge badge-light'>Prova di autovalutazione del "+ storico[s].data +"</p>"+
																	"</div>" +
																	"<div class='column col-sm-4'>"+
																		"<p class='badge badge-danger'>Esito: NEGATIVO</p>"+
																	"</div>" +
																	"<div class='column col-sm-2'>"+
																		"<a class='buttonRipetiProva "+ storico[s].id + "' data-toggle='modal' data-target='#myModalRipeti'><img src='../img/icona_ripeti.png'></a>"+
																	"</div>" +
																"</div>"+
																
															"</li>");
					}
					
					loadAtStart();
				}	
			});	
		  
		  
	  });
	  
	  $(".rigaEsito").off('click').click(function(e){
			 
		  	var id = $(this).attr("class").split(' ')[2];
		  	
			var c = {
					azione : "mostra_video_esito",
					id_esito : id,
					
			};

			$.ajax({
				type: "POST",
				url: "prova_autovalutazione",		
				datatype : "json",
				data: JSON.stringify(c),
				success: function(data){
					var video = JSON.parse(data);
					
					if($("ul[id='lista_esiti']").children().eq(id-1).children(".rigaVideoEsito").length>0){
						$("ul[id='lista_esiti']").children().eq(id-1).children(".rigaVideoEsito").remove();
						return;
					}
					
					for(v in video){
						$("ul[id='lista_esiti']").children().eq(id-1).append("<div class='rigaVideoEsito row badge badge-light' selezionato='false' url='" + video[v].url + "'>"+
													"<div class='column col-sm-6'>"+
														"<p>"+ video[v].nome +"</p>"+	
													"</div>"+
													"<div class='column col-sm-6'>"+
														"<p>"+ video[v].difficolta +"</p>"+	
													"</div>"+
												"</div>");
					}
					
					loadAtStart();
				}	
			});	
		  
		  
	  });
	  
	  
	  
	  $(".buttonRipetiProva").off('click').click(function(e){
			 
		  var id = $(this).attr("class").split(' ')[1];
		  
			var c = {
					azione : "ripeti_prova",
					id_esito : id,
					
			};

			$.ajax({
				type: "POST",
				url: "prova_autovalutazione",		
				datatype : "json",
				data: JSON.stringify(c),
				success: function(data){
					var c = JSON.parse(data);
					
					
				}	
			});	
		  
		  
	  });
	  
	  $("#buttonUndo").off('click').click(function(e){
			 
			var c = {
					azione : "undo",	
			};

			$.ajax({
				type: "POST",
				url: "prova_autovalutazione",		
				datatype : "json",
				data: JSON.stringify(c),
				success: function(data){
					var video = JSON.parse(data);
					$("#jumbotronSx").children('.div_video').remove(); 
					$("#jumbotronDx").children('.div_video').remove(); 
					for(v in video){		
						
						$('#jumbotronDx').append("<div class='div_video'>" +
													"<div class='rowUp'>"+
														"<iframe src='"+ video[v].url +"' selezionato='false'></iframe>" +
													"</div>" +
													"<div class='rowDown'>" +
														"<a class='badge badge-light'  id='textNomeVideo'>" + video[v].nome + "</a>"+
													"</div>"+
												"</div>");
						
					}
					
					loadAtStart();
				}	
			});	
	  });
	  
	  $("#buttonRedo").off('click').click(function(e){
			 
			var c = {
					azione : "redo",	
			};

			$.ajax({
				type: "POST",
				url: "prova_autovalutazione",		
				datatype : "json",
				data: JSON.stringify(c),
				success: function(data){
					var video = JSON.parse(data);
					$("#jumbotronSx").children('.div_video').remove(); 
					$("#jumbotronDx").children('.div_video').remove(); 
					for(v in video){		
						
						$('#jumbotronDx').append("<div class='div_video'>" +
													"<div class='rowUp'>"+
														"<iframe src='"+ video[v].url +"' selezionato='false'></iframe>" +
													"</div>" +
													"<div class='rowDown'>" +
														"<a class='badge badge-light'  id='textNomeVideo'>" + video[v].nome + "</a>"+
													"</div>"+
												"</div>");
						
					}
					
					loadAtStart();
				}	
			});	
	  });
}


function creaProvaAutovalutazione(){
	
	var array_url = [];
	
	$('#jumbotronDx').children('.div_video').children('.rowUp').children('iframe').each(function(){
		array_url.push($(this).attr("src"));
	});
	
	
	var c = {
			azione : "crea_prova_autovalutazione",
			url: array_url,
			
	};

	$.ajax({
		type: "POST",
		url: "prova_autovalutazione",		
		datatype : "json",
		data: JSON.stringify(c),
		success: function(data){
			var c = JSON.parse(data);
			
			if($("#buttonInizia").length == 0)
				$("#comandiDX2").append("<a id='buttonInizia'class='btn btn-success' href='prova_autovalutazione?editata=true'>INIZIA</a>");
		}	
	});	
}