$("document").ready(function(){
	
	$(".buttonRipetiProva").off('click').click(function(e){

		var id_prova = $(this).parent().parent().attr("id");

		var c = {
				azione : "ripeti_prova",
				id_esito: id_prova,
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
});